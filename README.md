# Project Information Retrieval

**Title:** Evaluierung der Retrieval-Leistung einer Search Engine am Beispiel einer Media Library  
**Supervisor:** Ruxandra Domenig

- [Lucene](https://lucene.apache.org/)
- [Solr](https://lucene.apache.org/solr/)
- [Tika](http://tika.apache.org/)
- [EasyTAG](https://wiki.gnome.org/Apps/EasyTAG)

`examples` directory cotains minimal working examples for `Tika` and `Lucene`.

## Build Project

```bash
git clone git@github.com:kressi/search-media.git
cd search-media
./gradlew build
```

## Installation Solr

- [Solr in 5 minutes](http://www.solrtutorial.com/solr-in-5-minutes.html)
- [solr mirrors](http://www.apache.org/dyn/closer.lua/lucene/solr/)
- [Getting started](https://cwiki.apache.org/confluence/display/solr/Getting+Started)
- http://www.developer.com/open/searching-indexed-data-with-solr.html
- http://solr.pl/en/2011/03/21/solr-and-tika-integration-part-1-basics/
- http://stackoverflow.com/questions/5896942/nutch-1-2-crawl-or-parse-mp3
- [Solr & tika](http://www.earthmagazine.org/sites/all/modules/search_api_attachments/README.txt)

```bash
wget http://mirror.switch.ch/mirror/apache/dist/lucene/solr/6.0.0/solr-6.0.0.tgz
tar -zxvf solr-6.0.0.tgz
cd solr-6.0.0
```

## Lucene

- [lucene mirrors](http://www.apache.org/dyn/closer.lua/lucene/java/6.0.0)
- [Documentation](https://lucene.apache.org/core/6_0_0/)
- [A Short Introduction to Lucene](http://oak.cs.ucla.edu/cs144/projects/lucene/)
- http://search.cpan.org/~tbusch/Lucene-0.18/lib/Lucene.pm
- https://mkaz.github.io/2011/06/28/how-to-use-scala-and-lucene-to-create-a-basic-search-application/
- https://github.com/zouzias/lib-lucene-sugar
- wiki.apache.org/lucene-java
- https://github.com/macluq/HelloLucene/blob/master/src/main/java/HelloLucene/HelloLucene.java
- https://scottcote.wordpress.com/2015/09/08/update-for-lucenetutorial/
- http://www.developer.com/open/implement-data-indexing-and-search-with-lucene-and-solr.html
- https://lingpipe-blog.com/2014/03/08/lucene-4-essentials-for-text-search-and-indexing/

```bash
wget http://mirror.switch.ch/mirror/apache/dist/lucene/java/6.0.0/lucene-6.0.0.tgz
tar -zxvf lucene-6.0.0.tgz
```

## tika

- [tika-python](https://github.com/chrismattmann/tika-python)
- [Tika & Lucene](https://dzone.com/articles/understanding-information)
- http://tika.apache.org/1.12/gettingstarted.html
- https://github.com/rickcrawford/lucene-example

```bash
wget http://mirror.switch.ch/mirror/apache/dist/tika/tika-app-1.12.jar
```

## Video & Tag Formats

### Matroska

- [Diagram Container](https://www.matroska.org/technical/diagram/index.html)
- [Tagging](https://www.matroska.org/technical/specs/tagging/index.html)

### ID3

- https://en.wikipedia.org/wiki/ID3

## Luke

- https://github.com/tarzanek/luke
- https://github.com/DmitryKey/luke
- http://www.getopt.org/luke/
- https://lucene.apache.org/core/3_5_0/contributions.html
- https://lingpipe-blog.com/2012/07/24/using-luke-the-lucene-index-browser-to-develop-search-queries/
