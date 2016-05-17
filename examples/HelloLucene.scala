import java.io.IOException

import org.apache.lucene.analysis.standard.StandardAnalyzer
import org.apache.lucene.document.{ Document,Field, StringField, TextField }
import org.apache.lucene.index.{ DirectoryReader, IndexReader, IndexWriter, IndexWriterConfig }
import org.apache.lucene.queryparser.classic.{ ParseException, QueryParser }
import org.apache.lucene.search.{ IndexSearcher, Query, ScoreDoc, TopDocs }
import org.apache.lucene.store.{ Directory, RAMDirectory }

object HelloLucene {
    @throws(classOf[IOException])
    @throws(classOf[ParseException])
    def main(args:Array[String]) {
        // 0. Specify the analyzer for tokenizing text.
        //    The same analyzer should be used for indexing and searching
        val analyzer = new StandardAnalyzer()

        // 1. create the index
        val index = new RAMDirectory()

        val config = new IndexWriterConfig(analyzer)

        val w = new IndexWriter(index, config)
        addDoc(w, "Lucene in Action", "193398817")
        addDoc(w, "Lucene for Dummies", "55320055Z")
        addDoc(w, "Managing Gigabytes", "55063554A")
        addDoc(w, "The Art of Computer Science", "9900333X")
        w.close()

        // 2. query
        val querystr = if (args.length > 0) args(0) else "lucene"

        // the "title" arg specifies the default field to use
        // when no field is explicitly specified in the query.
        val q = new QueryParser("title", analyzer).parse(querystr)

        // 3. search
        val hitsPerPage = 10
        val reader = DirectoryReader.open(index)
        val searcher = new IndexSearcher(reader)
        val docs = searcher.search(q, hitsPerPage)
        val hits = docs.scoreDocs

        // 4. display results
        System.out.println("Found " + hits.length + " hits.")
        for (i <- 0 until hits.length) {
            val docId = hits(i).doc
            val d = searcher.doc(docId)
            System.out.println((i + 1) + ". " + d.get("isbn") + "\t" + d.get("title"))
        }

        // reader can only be closed when there
        // is no need to access the documents any more.
        reader.close()
    }

    @throws(classOf[IOException])
    private def addDoc(w:IndexWriter, title:String, isbn:String) {
        val doc = new Document()
        doc.add(new TextField("title", title, Field.Store.YES))

        // use a string field for isbn because we don't want it tokenized
        doc.add(new StringField("isbn", isbn, Field.Store.YES))
        w.addDocument(doc)
    }
}