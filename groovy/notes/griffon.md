# Utiliser Griffon pour une application Java FX / Groovy

Installer sdkman:

    $ curl -s http://get.sdkman.io | bash
    
Installer le JDK Java Oracle:

    $ sdk install java
    
Installer et configurer lazybones:

    $ sdk install lazybones
                        
    $ vim ~/.lazybones/config.groovy
    
    bintrayRepositories = [
        "griffon/griffon-lazybones-templates",
        "pledbrook/lazybones-templates"
    ]
    
Créer un template griffon / jfx:    
                             
    $ lazybones list                
    $ lazybones create  griffon-javafx-groovy console

Voir: https://github.com/pledbrook/lazybones