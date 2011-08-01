Lipsum
======

Lipsum is a convenient way to add filler text to your Java application.

Example Usage
-------------

Randomly select a Lorem Ipsum paragraph::

 String paragraph = Lipsum.lorem().paragraph(new Random().nextLong());

Include a Lorem Ipsum paragraph in a StringTemplate group file::

 import "org/codeswarm/lipsum/lorem.stg"
 paragraph() ::= "<LoremIpsum42()>"

Releases
--------

* `1.0`_

.. _1.0 : http://mvnrepository.com/artifact/org.codeswarm/lipsum/1.0

