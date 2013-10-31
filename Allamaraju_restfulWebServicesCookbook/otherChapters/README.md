* Chapter 10 - Conditional Requests

Sur le contrôle optimiste de la concurrence

** Sur HTTP

*** Structure des messages

**** Requête

- Métode HTTP (GET, PUT, etc.)
- URI (propre ou avec requête (?param1=val1&param2=val2...))
- En-têtes
- Contenu (optionnel) du message (payload)

**** Réponse

- Statut (code)
- En-têtes
- Contenu (optionnel) du message (payload)

*** Liste des en-têtes

cf. http://en.wikipedia.org/wiki/List_of_HTTP_header_fields

*** Liste des statuts pour les réponses

cf. http://en.wikipedia.org/wiki/List_of_HTTP_status_codes


** Interactions directes via Poster (plugin de Firefox)

Requête
GET
uri : http://localhost:8080/ResfulWebServicesCookBook/compteur/get

Réponse : 
<?xml version="1.0" encoding="UTF-8" standalone="yes"?><ressource><i>0</i></ressource>

** Problèmes à résoudre : le contrôle de la concurrence

Exemple : un compteur avec une interface get/set
Condition de cohérence : lorsqu'un client réalise un set, il doit avoir
obtenu la valeur courante du compteur par un get. 

En l'absence de cette condition, des "data races" sont possibles :
ainsi, si plusieurs clients concurrents s'exécutent en réalisant des
incrémentations, certaines incrémentations sont perdues.

Idée : contrôle optimiste de la concurrence
On ajoute une version au compteur.
Lorsqu'un client réalise une lecture (get), il reçoit la version en plus de la valeur.
Lorsqu'il réalise une écriture (set), il envoie la dernière version
qu'il a lue. Le serveur compare la version envoyée à celle courante : si
elles sont égales, il réalise l'écriture, sinon il la rejette.

Le contrôle est réalisé entièrement dans la couche http, conformément
aux principes REST.
Il repose sur trois filtres, et sont implémentés en respectant le
standard JAX-RS.

Cf. serveur/src/chap10/infrastructure/
et client/src/chap10/infrastructure/

- RealiserTransactionOptimiste (serveur) et AjouterVersionEnTete (client)
Le premier filtre (sur le serveur et le client) gère les versions, en lecture et en écriture : il
utilise pour stocker les versions les en-têtes "ETag" pour les requêtes GET, et "If-Match"
pour les requêtes  PUT.

- Cacher (serveur) et AjouterVersionEnTete (client)
Côté serveur, il est possible de sauvegarder la réponse dans un cache.
L'effet d'une requête PUT est de vider le cache.
Pour une requête GET, 
si le cache n'est pas vide, la réponse est  faite
immédiatement ; si le cache est vide, une requête n'est pas filtrée mais
permet de remplir le cache avec sa réponse. 

- InteragirAtomiquement (serveur)
Ce filtre garantit que chaque requête est traitée de manière atomique (indivisible),
en utilisant un sémaphore. En l'absence de cette atomicité, des "data
races" surviennent entre les différents filtres et l'implémentation du compteur.


