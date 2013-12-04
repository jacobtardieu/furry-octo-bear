Les fichiers sont placés dans deux répertoires : serveur et client.
Le répertoire serveur possède deux sous-répertoire : src et WEB-INF.
Le répertoire client possède un seul répertoire : src.


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
uri : http://localhost:8080/ResfulWebServicesCookBook/registre/get

Réponse : 
<?xml version="1.0" encoding="UTF-8" standalone="yes"?><ressource><i>0</i></ressource>

** Problèmes à résoudre : le contrôle de la concurrence

Exemple : un registre avec une interface get/set
Condition de cohérence : lorsqu'un client réalise un set, il doit avoir
obtenu la valeur courante du registre par un get. 

En l'absence de cette condition, des "data races" sont possibles :
ainsi, si plusieurs clients concurrents s'exécutent en réalisant des
incrémentations, certaines incrémentations sont perdues.

Idée : contrôle optimiste de la concurrence
On ajoute une version au registre.
Lorsqu'un client réalise une lecture (get), il reçoit la version en plus de la valeur.
Lorsqu'il réalise une écriture (set), il envoie la dernière version
qu'il a lue. Le serveur compare la version envoyée à celle courante : si
elles sont égales, il réalise l'écriture, sinon il la rejette.

Le contrôle est réalisé entièrement dans la couche http, conformément
aux principes REST.
Il repose sur trois filtres, et sont implémentés en respectant le
standard JAX-RS (cf. chap. 6 de la spécification, version 2.0).

Pour activer les filtres dans cxf, les déclarer
- côté serveur : dans restful-beans.xml
- côté client : à la création du proxy (cf. fonction principale chap10.client.Chap10.main)

Cf. serveur/src/chap10/infrastructure/jaxrs
et client/src/chap10/infrastructure/jaxrs
(Noter aussi l'usage d'annotations (définies dans jaxrs.annotations)
pour établir le lien entre les filtres (définis dans jaxrs) 
et leurs points d'application
(cf. serveur/src:chap10.modele.ServiceRegistre).

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
races" surviennent entre les différents filtres et l'implémentation du registre.

* Chapter 11 - Miscellaneous Writes

Sur la mobilité des canaux
Ce chapitre traite de requêtes POST, utilisées pour modifier l'état des
ressources du serveur. 
Voir le chapitre 1 pour la différence entre PUT et POST
Les différents usages considérés impliquent pour le serveur la création
d'une ressource dont la nouvelle adresse (URI) est renvoyée au client. 
Autrement dit, dans le modèle abstrait chimique présenté en cours, le
serveur crée une nouvelle ressource et renvoie au client un  canal de
communication avec cette ressource. 

Les exemples traités montrent comment manipuler des URI entre un client
et un serveur

** Exemple 1

Service 1 (cf. serveur/src: chap11.modele.Service1) 
Cette interface possède une méthode GET permettant d'obtenir un lien
vers un second service. Elle renvoie une valeur de type
Hyperlien<Service2>.

Service 2 (cf. serveur/src: chap11.modele.Service2) 
Cette interface possède une méthode GET f renvoyant un entier.

La nouvelle classe Hyperlien (cf. serveur/src:
chap11.infrastructure.jaxrs) est une enveloppe d'une URI. Elle est
générique, le type paramètre servant à indiquer au compilateur le type
de la ressource vers laquelle pointe l'URI.

Côté client, une nouvelle classe, ClientRessource (dans client/src:
chap11.infrastructure.jaxrs), elle-aussi générique, permet d'utiliser un
hyperlien. Elle permet d'obtenir un proxy pour la ressource pointée par
l'hyperlien. 

** Exemple 2

Exemple plus complet
- Un catalogue permet d'obtenir une liste d'hyperliens vers des livres.
- Une bibliothèque contient les livres.
- Un livre contient un titre et une URI. Il contient aussi une méthode
  particulière, getRepresentation() , implémentée en renvoyant le livre
  cible (cf. "return this;"). Cette méthode permet d'obtenir une copie
  locale, côté client. 

** Points spécifiques concernant JAXB

Il n'est pas possible d'utiliser JAXB pour des interfaces ou des classes
qui n'ont pas été annotées, comme URI. C'est la raison pour laquelle on
définit des conversions vers des classes reconnues par JAXB dans le
paquet chap11.infrastruture.jaxb.


