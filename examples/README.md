# Lucene examples in Java & Scala

Java source is taken from [Lucene in 5 minutes](http://www.lucenetutorial.com/lucene-in-5-minutes.html).

## Dependencies

Add `jar` files to current working directory and omit classpath option `-cp` or provide classpaths of required `jar` files. Current working directory is always added as default classpath in case nothing else is provided.

```bash
wget http://repo1.maven.org/maven2/org/apache/lucene/lucene-core/6.0.0/lucene-core-6.0.0.jar
wget http://repo1.maven.org/maven2/org/apache/lucene/lucene-analyzers-common/6.0.0/lucene-analyzers-common-6.0.0.jar
wget http://repo1.maven.org/maven2/org/apache/lucene/lucene-queryparser/6.0.0/lucene-queryparser-6.0.0.jar
```

## HelloLucene.java

**Compile**
```java
javac -cp '.:../ext/*' HelloLucene.java
```

**Run**
```java
javac -cp '.:../ext/*' HelloLucene
```

## HelloLucene.scala

**Compile**
```scala
scalac -cp '.:../ext/*' HelloLucene.scala
```

**Run**
```scala
scala -cp '.:../ext/*' HelloLucene
```
