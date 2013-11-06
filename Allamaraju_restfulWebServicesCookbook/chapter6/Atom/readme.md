Introduction
-------------

Atom is a syndication protocol resembling RSS, that allows people to subscribe to a URL, and recieve a feed of entries representing whatever resource you want (blog posts,whatever).


What you need
--------------

There's no good schema file for Atom, so it's better to go by a reference implementation. I chose [Apache Abdera](http://abdera.apache.org/). With this you can easily output feeds you need.


Setup
------

You'll need to get the Abdera package from the website (scroll down pretty far to the download page), which includes `abdera-1.xx.jar`. That will be a first dependency.

 In the zip there's also a `lib` folder with some dependencies that you'll also need to pick out. To get the example to work, I needed `axiom-api` and `axiom-impl`.

 As to where to put these dependencies : You'll need to put it in two spots. A first place is in the Java Resources part of your project (in `Libraries`) if you want syntax verification for Eclipse, and you'll also need to put it in `WEB-INF/lib/` (where Tomcat looks for dependencies).


Using Abdera
------------


In the `test.Atom` package, you'll find pretty much all you need to get something working. Checkout out `manualFeedCreation` to see how to use Abdera. No more configuration is necessary to get the system working. Abdera provides a Feed object with serializers, so it will automatically get serialized through JAXRS.



Why no AtomPub implementation?
------------------------------

AtomPub was declared dead by its creater [4 years ago](http://bitworking.org/news/425/atompub-is-a-failure). Other people [agree](http://www.defuze.org/archives/171-atom-and-atompub-were-failed.html). Enough said.

  Also, I couldn't figure out how to get Abdera's service to work. And there's no schema file for AtomPub (there are some unofficial ones for Atom). Only a spec.

AtomPub has a (limited) use as a describer of services at an end-point. It doesn't really offer a worthwhile service apart from that.


Viable alternatives are :
   - creating an Atom feed of available services
   - Returning a JSON object describing services on an HTTP OPTIONS call
   - just having a human-readable webpage with description of services (I don't know of any web service scraper anyways)

An interesting use case for AtomPub would be that a tool could be pointed to the file and then be able to interface with it, but Web Service Discovery protocols are DOA it seems.
