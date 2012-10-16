Lipsum
======

Lipsum is a convenient way to add filler text to your Java application.

Example Usage
-------------

Randomly select a Lorem Ipsum paragraph::

 String paragraph = Lipsum.lorem().paragraph(new Random().nextLong());

Download
--------

Lipsum is available from Maven Central.

The latest version is 2.0.

```xml
<dependency>
  <groupId>org.codeswarm</groupId>
  <artifactId>lipsum2</artifactId>
  <version>2.0</version>
</dependency>
```

Releases
--------

**2.0**
* Rename package from org.codeswarm.lipsum to org.codeswarm.lipsum2
* Remove StringTemplate support
* Remove dependency on Guava
* Bugfix: ArrayIndexOutOfBoundsException when invoking #paragraph(long) with a negative argument

**1.0**
* Lipsum, Bacon Ipsum, and Lorizzle
* List and StringTemplate-based generators
