RESTful services Cookbook -- Chapter 1
======================================

Bien maintenir la visibilité
----------------------------

L'utilisation correcte des bon types de requêtes http est essentiel pour maintenir une bonne visibilité, i.e. pour être capable de savoir quelle est la nature de l'interaction.

* GET : Doit être utilisé pour récupérer la représentation d'une ressource.
Par exemple : récupérer une liste des sandwiches disponibles : GET sur magasin/sandwiches
http://localhost:8080/restexamples/magasin/sandwiches

* PUT : Doit être utilisé pour mettre à jour une ressource.
Par exemple, modifier le prix d'un sandwich

Content-Type: application/json ---> MIME format pour déclarer le type de ce qu'on envoie
{"sandwich":{"description":"Très très bon, avec du jambon et du beurre. Et aussi du pain autour","id":1,"nom":"Jambon beurre","prix":3}}

* POST : Utilisé pour créer une nouvelle ressource
Par exemple, pour ajouter un nouveau sandwich

Content-Type: application/json
{"sandwich":{"description":"Très très bon, avec du jambon et du beurre. Et aussi du pain autour","id":1,"nom":"Jambon beurre","prix":2}}

* DELETE : comme son nom l'indique, pour supprimer une ressource
Quand une ressource est supprimée une réponse 200 (OK) est renvoyée, et quand la ressource n'existe pas, uen 404 est envoyé.

HTTP est un protocole visible pour les raisons suivantes :
* Les requêtes HTTP sont sans état : n'importe quel intermédiaire peut connaître le sens d'une requête sans avoir d'autres données.
* HTTP utile une interface uniforme : OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE. Chaque méthode n'opère que sur une seule et unique ressource, et leur sens est toujours le même.
* HTTP utilise un format pour envelopper les données, le format MIME. Cela permet de séparer clairement les en-tête du corps du message (par exemple du XML ou du json). Les en-têtes sont visibles, mais le contenu n'est utile que pour les logiciels qui traitent celui-ci, tous les autres peuvent ne pas s'en soucier.


La visibilité passe aussi par l'utilisation des bons code de status pour les réponses, par exemple 200 pour OK, 404 si la ressource n'est pas trouvée, 500 si erreur interne au serveur...

Comment maintenir l'état de l'application
-----------------------------------------

En REST, l'habitude est de garder l'état de l'application au niveau du client. Cela veut dire d'encoder l'état dans l'URI d'appel. Ce cas a déjà été vu lors de l'exercice sur l'AutomateRESTStateless.

Cela peut également se faire par liens au sein du contenu (Cyprien sera ravi d'expliquer commme faire cela).

Comment implémenter des méthodes sûres et idempotentes
------------------------------------------------------

* méthode sûre : méthode qui ne cause pas d'effet de bord. 
GET doit être une méthode sûre
* méthode idempotente : méthode dont la réponse est la même à tous les coups
GET, PUT et DELETE sont des méthodes idempotentes : elles peuvent être répétées (par exemple en cas de problème réseau) sans danger de corruption des données.

La méthode DELETE est à part car théoriquement une ressource qui n'existe plus mais qui existait avant devrait renvoyer un 200, mais en pratique il est souvent difficile voir dangereux pour la sécurité de garder un historique des ressources supprimées, donc la convention actuelle est de renvoyer un 404 quand on essaye de supprimer une ressource qui n'existe plus. Cela contredit l'indempotence de DELETE.


Quand utiliser GET
------------------

La méthode GET doit être utilisée pour une récupération sûre et idempotente d'information, par exemple les caractéristiques d'un sandwich. Dans notre exemple, des appels successifs à magasins/sandwiches donnera toujours la même liste tant qu'elle n'aura pas été modifiée d'une autre manière. De plus, ces appels ne modifieront pas l'état du stock.

Exemples de mauvaises utilisations de GET : Ajouter un produit au panier, envoyer un message ou supprimer une note.

Quand utiliser POST
-------------------

La méthode POST peut être utilisée dans les cas suivants
* Pour créer une nouvelle ressource comme dans l'exemple du sandwich
* Pour modifier une ou plusieurs ressources comme nous pourrons le voir dans le chapitre suivant
* Pour exécuter des requêtes avec beaucoup de données, cf chapitre 8
* Pour exécuter des opérations non sûres et non idempotentes, quand aucune autre méthode n'est appropriée.

Cette méthode générale doit donc être traitée spécialement : 
* Pas de cache des réponses
* Pas d'activations automatiques de requêtes POST par des outils (crawlers)
* Pas de resoumission automatique des requêtes POST


Quand une ressource a été créée, le serveur doit retourner le code 201 Created et redonner la localisation de la nouvelle ressource (cf exemple du create sandwich).

Quand utiliser PUT
------------------------------------------------------

PUT peut être utilisé :
* Pour mettre à jour des ressources existantes
* Pour créer des nouvelles ressources quand le client peut décider de l'URI de la ressource. Dans le cas contraire, c'est POST qui doit être utilisé.

Comment utiliser POST pour traiter des tâche asynchrones
--------------------------------------------------------

Quand des requêtes POST mettent du temps à être traitées par le serveur, il est possible de renvoyer une réponse 202 Accepted en indiquant une adresse à laquelle la tâche peut être consultée (par exemple lors du téléchargement d'une image).
Quand le traitement est fini, le serveur répond à l'adresse de la tâche un 303 See Other avec la localisation de la nouvelle ressource.


Quand utiliser des méthodes HTTP maison
---------------------------------------

Jamais, il faut à tout prix éviter cela qui s'éloigne des standard et casse la compatibilité.

Si jamais un tel besoin se fait ressentir, la bonne solution est d'écrire un contrôleur (voir chapitre suivant) qui abstraira les opérations désirées (comme par exemple CLONE ou MOVE).

Quand et comment utiliser des en-têtes maison
---------------------------------------------

Les en-têtes maison doivent être réservés à titre d'information uniquement, et ne doivent pas être requis par le serveur ou le client et ne doivent pas changer le comportement des méthodes HTTP.

Dans le cas ou les informations transportées dans l'en-tête sont importantes et nécessaires pour le traitement de la requête, il faut alors les mettre dans le corps du message ou dans l'URI.

La convention pour nommer les en-tête est X--{nom de la société}--{nom de l'en-tête}

WordPress utilise par exemple X-Powered-By et X-Pingback, par exemple sur http://www.nicolasjoseph.com/









