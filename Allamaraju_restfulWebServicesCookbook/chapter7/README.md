Folder for this book chapter
Pour lancer le projet avec maven, se placer dans le répertoire du projet avec la console et lancer la commande :
mvn clean install tomcat:run-war

Ce projet contient une API ContentNegoService illustrant les mécanismes de content negotation pour :
- Le choix du langage de représentation des données
- Le choix de la langue,
- Le choix de l'encodage des caractères,
- La gestion des Negotiation failures,
- L'agent driven content negotiation

Les tests sont réalisés avec un client Rest simple (plugin firefox)