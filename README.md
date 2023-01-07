# Localisation des pharmacies
Ce projet a pour but de mettre une application mobile qui  permet de visualiser la localisation les pharmacies de garde de jour et de nuit dans une ville choisie. Il utilise Java dans le mobile et pour le backend SpringBoot  et Hibernate pour la logique côté serveur et la gestion de la base de données.

# Fonctionnalités
1.  Chercher une pharmacie par ville et zone
2.  Visualiser les pharmacies dans une Map
3.  Visualiser la position de l’utilisateur dans la map et mentionner la pharmacie la plus proche
4.  Filtrer la recherche selon le type de la garde
5.  Afficher l’itinéraire vers une pharmacie
# Mise en place du projet
Pour mettre en place ce projet, vous aurez besoin des éléments suivants :

1. Un serveur d'application Java avec Spring boot
2. Un SGBD (comme MySQL) cote backend
3. Un éditeur de code (comme Android Studio )
4. Ajouter de implementation dans build.gradle(Module) comme :
        implementation 'io.github.florent37:shapeofview:1.4.7'
        implementation 'de.hdodenhof:circleimageview:3.1.0'
        implementation 'com.github.bumptech.glide:glide:4.14.2'
    pour le style.
    et:
        implementation 'com.google.code.gson:gson:2.8.7'
        implementation 'com.google.android.gms:play-services-maps:11.4.2'
        implementation 'com.google.android.gms:play-services-location:11.4.2'
        implementation 'com.google.android.gms:play-services:11.4.2'
     pour la map.
5. Utuliser une Api 

# Déploiement
Pour déployer ce projet sur votre serveur d'application, suivez les étapes suivantes :

1. Téléchargez le projet sur votre ordinateur
2. Ouvrez-le dans votre éditeur de code( Android Studio)
3. Configurez votre IP dans le fichier pour recever les donnes d"apres backend
4. Ranner le projet
# Auteur
Ce projet a été réalisé par REFKI ELHADI-KADIRI YAHYA-  MAKAOUI REDA.


