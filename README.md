# Project Information Retrieval

**Title:** Evaluierung der Retrieval-Leistung einer Search Engine am Beispiel einer Media Library  
**Supervisor:** Ruxandra Domenig

- [Lucene](https://lucene.apache.org/)
- [Tika](http://tika.apache.org/)
- [EasyTAG](https://wiki.gnome.org/Apps/EasyTAG)

`examples` directory cotains minimal working examples for `Tika` and `Lucene`.

## Build Project

```bash
git clone git@github.com:kressi/search-media.git
cd search-media
./gradlew build
```

## Lucene

- [lucene mirrors](http://www.apache.org/dyn/closer.lua/lucene/java/6.0.0)
- [Documentation](https://lucene.apache.org/core/6_0_0/)
- [A Short Introduction to Lucene](http://oak.cs.ucla.edu/cs144/projects/lucene/)
- http://search.cpan.org/~tbusch/Lucene-0.18/lib/Lucene.pm
- https://mkaz.github.io/2011/06/28/how-to-use-scala-and-lucene-to-create-a-basic-search-application/
- https://github.com/zouzias/lib-lucene-sugar
- https://wiki.apache.org/lucene-java
- https://github.com/macluq/HelloLucene/blob/master/src/main/java/HelloLucene/HelloLucene.java
- https://scottcote.wordpress.com/2015/09/08/update-for-lucenetutorial/
- http://www.developer.com/open/implement-data-indexing-and-search-with-lucene-and-solr.html
- https://lingpipe-blog.com/2014/03/08/lucene-4-essentials-for-text-search-and-indexing/

```bash
wget http://mirror.switch.ch/mirror/apache/dist/lucene/java/6.4.1/lucene-6.4.1.tgz
tar -zxvf lucene-6.4.1.tgz
cd lucene-6.4.1
```

## tika

- [tika-python](https://github.com/chrismattmann/tika-python)
- [Tika & Lucene](https://dzone.com/articles/understanding-information)
- http://tika.apache.org/1.12/gettingstarted.html
- https://github.com/rickcrawford/lucene-example

```bash
wget http://mirror.switch.ch/mirror/apache/dist/tika/tika-app-1.14.jar
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

**Install Luke**
```bash
git clone https://github.com/DmitryKey/luke.git
cd luke
mvn install
```

**Run Luke with**
```bash
./luke.sh
```
