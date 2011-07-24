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

