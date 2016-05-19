package ch.kressi.search_media

import com.typesafe.config.ConfigFactory

object Config {

    // https://danielasfregola.com/2015/06/01/loading-configurations-in-scala/
    val config = ConfigFactory.load()
    val indexPath = config.getString("indexPath")
    val documentsPath = config.getString("documentPath")
}
