Folder for this book chapter
Pour lancer le projet avec maven, se placer dans le r�pertoire du projet avec la console et lancer la commande :
mvn clean install tomcat:run-war

Ce projet contient une API ContentNegoService illustrant les m�canismes de content negotation pour :
- Le choix du langage de repr�sentation des donn�es
- Le choix de la langue,
- Le choix de l'encodage des caract�res,
- La gestion des Negotiation failures,
- L'agent driven content negotiation

Les tests sont r�alis�s avec un client Rest simple (plugin firefox)