package ch.kressi.search_media

object Application extends App {

    // Parse cli
    // http://stackoverflow.com/questions/2315912/scala-best-way-to-parse-command-line-parameters-cli
    val usage = """
        Usage: search-media [--index path] [--docs path] filename
    """
    println(usage)
}