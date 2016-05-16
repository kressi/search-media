import com.typesafe.config.ConfigFactory

object WriteIndex {
	def main (args:Array[String]) {
		// https://danielasfregola.com/2015/06/01/loading-configurations-in-scala/
		val config = ConfigFactory.load()
		val indexPath = config.getString("indexPath")
		val documentPath = config.getString("documentPath")
		println(s"Index Path:\t$indexPath")
		println(s"Docs Path:\t$documentPath")
	}
}
