package ch.kressi.search_media

import com.typesafe.config.ConfigFactory

object Config {

	// https://danielasfregola.com/2015/06/01/loading-configurations-in-scala/
	val config = ConfigFactory.load()

	def indexPath () = config.getString("indexPath")

	def documentsPath () = config.getString("documentPath")
}
