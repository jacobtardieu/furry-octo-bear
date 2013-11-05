# REST Cook-book - Chapter 4
========================

This example is based on a familly model. There are families that contains persons. Each of these objects have attributes of their own.

Once you've started the TOMCAT server, all you have to do is test these requests:

  * http://localhost:8080/Chapter4Example/REST/ADAMS
  * http://localhost:8080/Chapter4Example/REST/ADAMS/Fetide
  * http://localhost:8080/Chapter4Example/familly?famillyName=ADAMS

The two first access are programed by the book :

We use clear names for the allocation of the URI paths, they are unique, and the response contains a link so that the URIs can be treated as unique identifiers.

The last access is an example of a redirection, just in case you want to change the URI mapping.
