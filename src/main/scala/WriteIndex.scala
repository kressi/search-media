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
	def main() {

		val docs = new File(Config.documentsPath())
		val idx = Paths.get(Config.indexPath())

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

			val text = handler.toString()
			val fileName = file.getName();		
			
			val doc = new Document()
			doc.add(new StoredField("file", fileName))
			
			
			for (key <- metadata.names()) {
				val name = key.toLowerCase()
				val value = metadata.get(key)
				
				if (!StringUtils.isBlank(value)) {
					if ("keywords".equalsIgnoreCase(key)) {
						for (keyword <- value.split(",?(\\s+)")) {
							doc.add(new StringField(name, keyword, Store.YES))
						}
					}
					else if ("title".equalsIgnoreCase(key)) {
						doc.add(new TextField(name, value, Store.YES))
					}
					else {
						doc.add(new StringField(name, fileName, Store.YES))
					}
				}
			}
			doc.add(new TextField("text", text, Store.NO))
			writer.addDocument(doc)
			
		}
		
		writer.commit()
		writer.deleteUnusedFiles()
		
		System.out.println(writer.maxDoc() + " documents written")
	}

}