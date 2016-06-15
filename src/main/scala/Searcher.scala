package ch.kressi.search_media

import scala.util.{Try, Success, Failure}
import scala.util.control.Exception.catching

import java.io.IOException
import java.nio.file.Paths

import org.apache.lucene.analysis.standard.StandardAnalyzer
import org.apache.lucene.index.DirectoryReader
import org.apache.lucene.queryparser.classic.{ ParseException, MultiFieldQueryParser }
import org.apache.lucene.search.{ IndexSearcher, TopScoreDocCollector }
import org.apache.lucene.store.FSDirectory

object Searcher {

    @throws(classOf[IOException])
    def apply() {
        
        val indexDir = Paths.get(Config.indexPath)
        val index = FSDirectory.open(indexDir)

        // Build a Query object
        val fields = Array("album", "author", "composer", "album", "title")
        val parser = new MultiFieldQueryParser(fields, new StandardAnalyzer())

        Try(parser.parse("Shpongle")) match {
            case Success(q) => 
                val hitsPerPage = 10
                val reader = DirectoryReader.open(index)
                val searcher = new IndexSearcher(reader)
                val collector = TopScoreDocCollector.create(hitsPerPage)
                searcher.search(q, collector)
                
                println("total hits: " + collector.getTotalHits())   
                
                val hits = collector.topDocs().scoreDocs
                for (hit <- hits) {
                    val doc = reader.document(hit.doc)
                    println(doc.get("file") + "  (" + hit.score + ")")
                }
            case Failure(e) => e.printStackTrace()
        }       
    }
}