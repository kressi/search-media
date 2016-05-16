# Examples in Java & Scala

## Lucene

Java source is taken from [Lucene in 5 minutes](http://www.lucenetutorial.com/lucene-in-5-minutes.html).

### Dependencies

Add `jar` files to current working directory and omit classpath option `-cp` or provide classpaths of required `jar` files. Current working directory is always added as default classpath in case nothing else is provided.

```bash
wget http://repo1.maven.org/maven2/org/apache/lucene/lucene-core/6.0.0/lucene-core-6.0.0.jar
wget http://repo1.maven.org/maven2/org/apache/lucene/lucene-analyzers-common/6.0.0/lucene-analyzers-common-6.0.0.jar
wget http://repo1.maven.org/maven2/org/apache/lucene/lucene-queryparser/6.0.0/lucene-queryparser-6.0.0.jar
```

### HelloLucene.java

**Compile**
```bash
javac -cp '.:../ext/*' HelloLucene.java
```

**Run**
```bash
javac -cp '.:../ext/*' HelloLucene
```

### HelloLucene.scala

**Compile**
```bash
scalac -cp '.:../ext/*' HelloLucene.scala
```

**Run**
```bash
scala -cp '.:../ext/*' HelloLucene
```

## Tika

Java source is taken from [TIKA - Extracting mp3 Files](http://www.tutorialspoint.com/tika/tika_extracting_mp3_files.htm), there are all kinds of useful examples to parse files with Tika.

### Dependencies

```bash
wget http://repo1.maven.org/maven2/org/apache/tika/tika-core/1.9/tika-core-1.9.jar
wget http://repo1.maven.org/maven2/org/apache/tika/tika-parsers/1.9/tika-parsers-1.9.jar
```

### Mp3Parse.java

**Compile**
```bash
javac -cp '.:../ext/*' Mp3Parse.java
```

**Run**
```bash
javac -cp '.:../ext/*' Mp3Parse
```

### Mp3Parse.scala