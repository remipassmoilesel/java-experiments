# Créer un graph de dépendances 

Voir: https://ferstl.github.io/depgraph-maven-plugin/plugin-info.html

Installer graphviz (pour éventuellement convertir les fichiers .dot générés):

    $ sudo apt install graphviz

Installer xdot pour visualiser les graphiques:

    $ sudo apt install xdot
    
Ajouter le plugin graph:

          <build>
            <plugins>
                <plugin>
                    <groupId>com.github.ferstl</groupId>
                    <artifactId>depgraph-maven-plugin</artifactId>
                    <version>3.2.0</version>
                </plugin>
                ...    

Lancer la tâche:
    
    $ mvn depgraph:graph
    
Ouvrir les fichier .dot des dossier target/    
         