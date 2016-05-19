package ch.kressi.search_media

import java.io.{ File, FileInputStream, IOException, InputStream }
import java.nio.file.{ Path, Paths }

import org.apache.commons.lang3.StringUtils
import org.apache.lucene.analysis.Analyzer
import org.apache.lucene.analysis.standard.StandardAnalyzer
import org.apache.lucene.document.{ Document, StoredField, StringField, TextField }
import org.apache.lucene.document.Field.Store
import org.apache.lucene.index.{ IndexWriter, IndexWriterConfig }
import org.apache.lucene.store.{ Directory, FSDirectory }
import org.apache.tika.exception.TikaException
import org.apache.tika.metadata.Metadata
import org.apache.tika.parser.{ AutoDetectParser, ParseContext, Parser }
import org.apache.tika.sax.BodyContentHandler
import org.xml.sax.{ ContentHandler, SAXException }

class WriteIndex {

    @throws(classOf[IOException])
    def apply() {

        val docs = new File(Config.documentsPath)
        val idx = Paths.get(Config.indexPath)

        val indexDir = FSDirectory.open(idx)
        
        val analyzer = new StandardAnalyzer()
        val conf = new IndexWriterConfig(analyzer)
        val writer = new IndexWriter(indexDir, conf)
        writer.deleteAll()
        
        for (file <- docs.listFiles()) {

            val metadata = new Metadata()
            val handler = new BodyContentHandler()
            val context = new ParseContext()
            val parser = new AutoDetectParser()
            val stream = new FileInputStream(file)
            try {
                parser.parse(stream, handler, metadata, context)
            } catch {
                case e: TikaException => e.printStackTrace()
                case e: SAXException => e.printStackTrace()
            } finally {
                stream.close()
            }

            val doc = prepareDoc(file.getName(), metadata)            
            writer.addDocument(doc)    
        }
        
        writer.commit()
        writer.deleteUnusedFiles()
        
        System.out.println(writer.maxDoc() + " documents written")
    }

    def prepareDoc (fileName:String, metadata:Metadata) : Document = {
        val doc = new Document()

        // http://stackoverflow.com/questions/9770416/scala-case-match-partial-string
        // https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch01s09.html
        // http://alvinalexander.com/scala/how-to-determine-if-string-contains-regular-expression-in-scala
        val fieldPat = "([a-z0-9]+):(album|releasedate|genre)".r

        //val text = handler.toString()

        doc.add(new StoredField("file", fileName))
        
        for (key <- metadata.names()) {
            val name = key.toLowerCase()
            val value = metadata.get(key)

            if (!StringUtils.isBlank(value)) {
                //System.out.println("Key: "+name+" Value: "+value)
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
        //doc.add(new TextField("text", text, Store.NO))
        doc
    }
}