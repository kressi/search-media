package ch.kressi.search_media

import scala.util.{Try, Success, Failure}
import scala.util.control.Exception.catching

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
    def apply() {
        
        val indexDir = Paths.get(Config.indexPath)
        val index = FSDirectory.open(indexDir)

        // Build a Query object
        val catcher = catching(classOf[ParseException])
        val parser = new QueryParser("text", new StandardAnalyzer())
        val query = catcher.withTry(parser.parse("Shpongle"))

        query match {
            case Success(q) => 
                val hitsPerPage = 10
                val reader = DirectoryReader.open(index)
                val searcher = new IndexSearcher(reader)
                val collector = TopScoreDocCollector.create(hitsPerPage)
                searcher.search(q, collector)
                
                System.out.println("total hits: " + collector.getTotalHits());      
                
                val hits = collector.topDocs().scoreDocs
                for (hit <- hits) {
                    val doc = reader.document(hit.doc)
                    System.out.println(doc.get("file") + "  (" + hit.score + ")")
                }
            case Failure(e) => e.printStackTrace()
        }       
    }
}