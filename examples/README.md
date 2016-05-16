# Examples in Java & Scala

## Complie & Run

**Java**
```bash
javac -cp '.:../ext/*' HelloLucene.java
java -cp '.:../ext/*' HelloLucene
```

**Scala**
```bash
scalac -cp '.:../ext/*' Mp3Parse.scala
scala -cp '.:../ext/*' Mp3Parse
```

## Lucene

Java source is taken from [Lucene in 5 minutes](http://www.lucenetutorial.com/lucene-in-5-minutes.html).

### Dependencies

Add `jar` files to current working directory and omit classpath option `-cp` or provide classpaths of required `jar` files. Current working directory is always added as default classpath in case nothing else is provided.

```bash
wget http://repo1.maven.org/maven2/org/apache/lucene/lucene-core/6.0.0/lucene-core-6.0.0.jar
wget http://repo1.maven.org/maven2/org/apache/lucene/lucene-analyzers-common/6.0.0/lucene-analyzers-common-6.0.0.jar
wget http://repo1.maven.org/maven2/org/apache/lucene/lucene-queryparser/6.0.0/lucene-queryparser-6.0.0.jar
```

## Tika

Java source is taken from [TIKA - Extracting mp3 Files](http://www.tutorialspoint.com/tika/tika_extracting_mp3_files.htm), there are all kinds of useful examples to parse files with Tika.

### Dependencies

```bash
wget http://repo1.maven.org/maven2/org/apache/tika/tika-core/1.9/tika-core-1.9.jar
wget http://repo1.maven.org/maven2/org/apache/tika/tika-parsers/1.9/tika-parsers-1.9.jar
```
