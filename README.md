# saleForecatstingSoftware

## Version française

### Prérequis
1- Installer la version 3.2.2 de R sur le serveur
2- Lancer R et la commande suivante : install.packages(“rJava”)
3- Toujours dans R lancer la commande : system.file(“jri”, package=”rJava”)

### Paramétrage du fichier run.bat
1- Modifier le chemin de la variable R_HOME vers le dossier R créer lors de l'installation de R (par exemple C:/R)
2- Modifier le chemin de la variable R_LIBS vers le dossier contenant les librairies R (par exemple /R/library). Ce répertoire contient les librairies rJava, DBI et RMySQL entre autres.
3- Modifier le chemin vers le fichier jri.dll (par exemple c:\R\library\rJava\jri\x64) 

NB : Il faudra bien veiller à utiliser ‘ \ ’ au lieu de ‘ / ’ sur un serveur Windows.
Après avoir opéré toutes les modification, il restera plus qu’à relancer le mvn package.

### Paramétrage de la base de données
Pour connecter votre base de données à l'application modifier les données contenues dans le fichier persistence.xml du dossier ressource > META-INF.
La propriété "hibernate.connection.url" doit contenir l'adresse de la base de données suivi du nom de la base.
La propriété "hhibernate.connection.username" doit contenir votre nom d'utilisateur.
La propriété "hibernate.connection.password" doit contenir votre mot de passe d'accès à la base.

### Lancement de l'application
Pour lancer l'application, copier le fichier run.bat que vous avez créé dans le dossier "export" du projet.
Ensuite avec Maven, lancer la commande "mvn package" pour créer un .jar du projet.
Vous pouvez lancer l'application en exécutant le fichier run.bat.

## English version

Import the project in your favorite IDE and convert the project in Maven project.

### Prerequisites
1- Install R 3.2.2
2- Put the command line in R : install.packages(“rJava”)
3- Put the command line in R : system.file(“jri”, package=”rJava”)

### Setting the file run.bat
1- Modified the file run.bat in th
e project for put the correct link for the environment variable R_HOME which is the link to R folder (like C:/R)
2- Modified the file run.bat in the project for put the correct link for the environment variable R_LIBS which is the link to R library folder (like /R/library). This folder contains some library like rJava, BDI and RMySQL.
3- Modified the file run.bat in the project for put the correct link for the file jri.dll (for example c:\R\library\rjava\jri\x64)

NB: We will have to make sure to use '\' instead of '/' on a Windows server.
After making all changes, it will just restart the mvn package.

### Setting the database
To connect your database, we need to modify the data in the file persistence.xml in folder resource > META-INF.
The property "hibernate.connection.url" must contain the address of the database followed by the database name.
The property "hhibernate.connection.username" must contain your username.
The property "hibernate.connection.password" must contain your password to access the database.

### Application Launch
To launch the application, copy the run.bat file that you created in the folder "export" of the project.
Then with Maven, launch the command "mvn package" to create a .jar of the project folder export.
You can start the application by running the run.bat file.
