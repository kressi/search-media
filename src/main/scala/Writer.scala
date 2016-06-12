package ch.kressi.search_media

import scala.util.{Try, Success, Failure}
import scala.util.control.Exception.catching

import java.io.{ File, FileInputStream, InputStream }
import java.nio.file.Paths

import org.apache.commons.lang3.StringUtils
import org.apache.lucene.analysis.standard.StandardAnalyzer
import org.apache.lucene.document.{ Document, StoredField, StringField, TextField }
import org.apache.lucene.document.Field.Store
import org.apache.lucene.index.{ IndexWriter, IndexWriterConfig }
import org.apache.lucene.store.FSDirectory
import org.apache.tika.exception.TikaException
import org.apache.tika.metadata.Metadata
import org.apache.tika.parser.{ AutoDetectParser, ParseContext }
import org.apache.tika.sax.BodyContentHandler
import org.xml.sax.SAXException

object Writer {

    def apply(): Unit = {

        val wd = for {
            writer <- mkWriter(Config.indexPath)
            docs   <- Try(new File(Config.documentsPath))
        } yield (writer, docs)

        wd match {
            case Success((w, d)) =>
                w.deleteAll
                addDocuments(w)(d)
                w.commit
                w.deleteUnusedFiles
                println (w.maxDoc + " documents written")
            case Failure(e) => logError(e)
        }
    }

    def mkWriter(indexPath:String): Try[IndexWriter] = {
        val analyzer = new StandardAnalyzer()
        val conf     = new IndexWriterConfig(analyzer)
        for {
            path   <- Try(Paths.get(indexPath))
            dir    <- Try(FSDirectory.open(path))
            writer <- Try(new IndexWriter(dir, conf))
        } yield writer
    }

    def addDocuments(w:IndexWriter) (docs:File): Unit = {
        for (file <- docs.listFiles()) {

            val metadata = new Metadata
            val handler  = new BodyContentHandler
            val context  = new ParseContext
            val parser   = new AutoDetectParser
            val stream   = Try(new FileInputStream(file))

            stream.map( s =>
                catching(classOf[TikaException],
                         classOf[SAXException])
                andFinally(s.close())
                withTry(parser.parse(s, handler, metadata, context))
            ) match {
                case Success(_) =>
                    w addDocument(mkDocment (file getName(), metadata))
                case Failure(e:Throwable) => logError(e)
            }
        }
    }

    def mkDocment (fileName:String, metadata:Metadata): Document = {

        val fieldPat = "([a-z0-9]+):(album|releasedate|genre)".r

        val doc = new Document()
        doc.add(new StoredField("file", fileName))
        
        for (key <- metadata.names()) {
            val name = key.toLowerCase()
            val value = metadata.get(key)

            if (!StringUtils.isBlank(value)) {
                name match {
                    case "keywords" => 
                        for (keyword <- value.split(",?(\\s+)")) {
                            doc.add(new StringField(name, keyword, Store.YES))
                        }
                    case "title" | "author" | "composer" =>
                        doc.add(new TextField(name, value, Store.YES))
                    case fieldPat("xmpdm", attr) =>
                        doc.add(new TextField(attr, value, Store.YES))
                    case _ => ()
                }
            }
        }
        doc
    }

    def logError(e:Throwable): Unit = println(e.toString())
}