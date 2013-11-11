Identification des ressources
======================
* Rest cookbook chapitre 2.
* Hao ZHANG

Aperçu
----------------------
L'identification des ressources, c'est à dire qu'on conçoit un modèle pour présenter des ressources. Il y a 7 sujets à discuter.

La structure d'URI
----------------------
cf : http://fr.wikipedia.org/wiki/Sch%C3%A9ma_d%27URI

Chaque URI est constituée de quatre parties :

    <nom du schéma> : <//[utilisateur@]partie hiérarchique[:porte]> [ ? <requête> ] [ # <fragment> ]

Le nom du schéma est une lettre suivie de n'importe quelle combinaison de lettres, de chiffres, du signe plus (+), du point (.) ou d'un tiret (-) et se termine par deux points (:).

La partie hiérarchique de l'URI est prévue pour contenir les informations d'identification de la ressource, hiérarchique par nature. En général, cette partie commence par un double slash (//), suivi par le domaine puis un chemin optionnel. 
Le domaine est principalement constitué du nom d'hôte ou de l'adresse IP, cette information peut être encadrée par deux autres champs optionnels : avant, les informations concernant l'utilisateur délimitées par un @ ; après, un numéro de port, précédé par un deux-points (:).

Le chemin est une séquence de segments (similaires à des répertoires), séparés par des slash (/). Chaque segment peut contenir des paramètres séparés par des points virgules (;), bien que cela soit rarement utilisé.

La requête est une partie optionnelle séparée par un point d'interrogation qui contient des informations complémentaires qui ne sont pas de nature hiérarchique. Le format de la requête n'est pas défini de manière générale, mais est souvent formée d'une suite de paires <clef>=<valeur> séparées par des points virgules ou par des esperluettes.

Le fragment est une partie optionnelle séparée par un dièse. Il fournit des informations supplémentaires permettant d'accéder à une ressource secondaire. Lorsque la ressource est un document HTML, le fragment est souvent l'id d'un élément et le navigateur fera le nécessaire pour que cet élément soit visible.

Comment identifier les ressources réelles
----------------------
* Problème :

On va identifier des ressources des sénarios.
    
* Solution:

C'est la plus commune ressource à identifier. Ce sont les choses réelles.
Par exemple, photo, étudiant, professeur, fruit, etc.
Vous pouvez les créer, lire, modifier ou supprimer par les méthodes données par HTTP(POST, GET, PUT, DELETE).

Comment choisir la granularité
----------------------
* Problème: 

On souhaite savoir comment choisir la granularité des ressources.

* Solution:

Il n'y a pas de standard pour déterminer la granularité de ressources. ça dépend de l'application, l'efficacité de réseau, la taille de représentation et surtout la convention entre le client et le serveur.
Par exemple, facebook, pour un utilisateur, il y a beaucoup de ressources, liste des amis, messages, mails, photos, etc. Le serveur va les donner ensemble ou respectivement?
Le meilleur moyen est le réfléchir depuis la perspectif de client.
La grosse granularité convient à le client riche, immutable et pas de cache.
La fine granularité s'adapte à le client léger, mutable et cacheable.

Utiliser la collection des ressources
----------------------
* Problème: 

On souhaite présenter des ressources similaires ensemble.

* Solution: 

La collection.
C'est trop utile pour les ressources similaires, par exemple, facebook, la liste des amis. 

    <users> 
        <user>1</user> 
        <user>2</user> 
    <users>


Utiliser la composition des ressources
----------------------
* Problème: 

On souhaite donner la ressource qui contient autres ressources.

* Solution: 

La composition.
Par exemple, les articles, les publicités et les photos dans une page de web.

     <web>
         <article>1</article>
         <photo>p</photo>
    </web>
     
Le défaut: l'invisibilité des ressources concrètes.

Comment identifier les fonctions
----------------------
* Problème: 

On souhaite donner la ressource qui calculer ou valider pour le client.

* Solution: 

Utiliser le nom de fonction comme une ressource et utiliser la méthode de GET de HTTP.
Par exemple, calculer la distances entre deux villes.

    #Request
    GET /distance_calc?lats=10&lngs=10&late=20&lnge=20
    
    #Response
    <result>
	    <distance>30</distance>
	</result>

Utiliser un contrôleur
----------------------
* Problème: 

On souhaite résoudre un problème qui contient quelques comportements. Ce sont pas seulement une méthode, PUT ou POST.

* Solution: 

Le serveur donne une ressource de contrôleur qui exécute tous les comportements. 
Le contrôleur peut réduire le couplage entre le serveur et le client.
Par exemple, on va fusionner l'annuaire dans un portable et dans le serveur. Comment peut-on le faire?
Méthode normale:

    1. GET l'annuaire du serveur.
    2. les fusionner dans le portable.
    3. PUT vers le serveur.
	
Méthode de contrôleur:

    1. POST la demande de fusionner vers le serveur.
    2. Le serveur fusionne les annuaire et retourne le résultat.
