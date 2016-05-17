package ch.kressi.search_media

import scala.util.{Try, Success, Failure}

import java.io.{ File, IOException }
import java.nio.file.{ Path, Paths }

import org.apache.lucene.analysis.standard.StandardAnalyzer
import org.apache.lucene.document.Document
import org.apache.lucene.index.DirectoryReader
import org.apache.lucene.queryparser.classic.{ ParseException, QueryParser }
import org.apache.lucene.search.{ IndexSearcher, Query, ScoreDoc, TopScoreDocCollector }
import org.apache.lucene.store.{ Directory, FSDirectory }

class SearchIndex {

	@throws(classOf[IOException])
	def main() {
		
		val indexDir = Paths.get(Config.indexPath())
		val index = FSDirectory.open(indexDir)

        // Build a Query object
        val query = try {
			new QueryParser("text", new StandardAnalyzer()).parse("Shpongle")
		} catch {
			case e:ParseException => e.printStackTrace()
				                     return
		}

		val hitsPerPage = 10
		val reader = DirectoryReader.open(index)
		val searcher = new IndexSearcher(reader)
		val collector = TopScoreDocCollector.create(hitsPerPage)
		searcher.search(query, collector)
		
		System.out.println("total hits: " + collector.getTotalHits());		
		
		val hits = collector.topDocs().scoreDocs
		for (hit <- hits) {
			val doc = reader.document(hit.doc)
			System.out.println(doc.get("file") + "  (" + hit.score + ")")
		}
		
	}

}