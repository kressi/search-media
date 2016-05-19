import java.io.{ File, FileInputStream, IOException }

import org.apache.tika.exception.TikaException
import org.apache.tika.metadata.Metadata
import org.apache.tika.parser.ParseContext
import org.apache.tika.parser.mp3.LyricsHandler
import org.apache.tika.parser.mp3.Mp3Parser
import org.apache.tika.sax.BodyContentHandler

import org.xml.sax.SAXException

object Mp3Parse {

   @throws(classOf[Exception])
   @throws(classOf[IOException])
   @throws(classOf[SAXException])
   @throws(classOf[TikaException])
   def main(args:Array[String])  {

      //detecting the file type
      val handler:BodyContentHandler = new BodyContentHandler()
      val metadata:Metadata = new Metadata()
      val inputstream:FileInputStream  = new FileInputStream(new File("../documents/Showtek - FTS.mp3"))
      val pcontext:ParseContext = new ParseContext()
      
      //Mp3 parser
      val Mp3Parser:Mp3Parser = new  Mp3Parser()
      Mp3Parser.parse(inputstream, handler, metadata, pcontext)
      val lyrics:LyricsHandler = new LyricsHandler(inputstream,handler)
      
      while (lyrics.hasLyrics()) {
         System.out.println(lyrics.toString())
      }
      
      System.out.println("Contents of the document:" + handler.toString())
      System.out.println("Metadata of the document:")
      val metadataNames:Array[String] = metadata.names()

      for (name:String <- metadataNames) {
          System.out.println(name + ": " + metadata.get(name))
      }
   }
}
