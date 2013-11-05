Travail en cours suite à des difficultées techniques rencontrées. Sera fini dans la soirée.

RESTful services Cookbook -- Chapter 1
======================================

Utilisation des différentes type de requêtes
--------------------------------------------

* GET : Doit être utilisé pour récupérer la représentation d'une ressource.
Par exemple : récupérer une liste des sandwiches disponibles : GET sur magasin/sandwiches

*PUT : Doit être utilisé pour mettre à jour une ressource.
Par exemple, modifier le priix d'un sandwich

Content-Type: application/xml
{"sandwich":{"description":"Très très bon, avec du jambon et du beurre. Et aussi du pain autour","id":1,"nom":"Jambon beurre","prix":2}}
