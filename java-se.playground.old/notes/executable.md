# Créer une application exécutable avec gradle

Ajouter le plugin application dans build.gradle:

    apply plugin: 'application'
    
    version = '0.0.1-SNAPSHOT'
    sourceCompatibility = 1.8
    targetCompatibility = 1.8
    
    mainClassName = "org.remipassmoilesel.javafxexperiments.JavafxExperimentsApplication"
    applicationDefaultJvmArgs = ["-Dgreeting.language=en"]

Ensuite: 

    $ gradle run
    
Il est possible ensuite d'empaqueter simplement l'application:

    $ gradle distZip 
    
## Fatjar

Ajouter le plugin shadow:

    buildscript {
        repositories {
            jcenter()
        }
        dependencies {
            classpath 'com.github.jengelman.gradle.plugins:shadow:2.0.1'
        }
    }    
    
Options possibles:

    shadowJar {
        baseName = 'abc-map2-full'
        version = '0.0.1-SNAPSHOT'
        destinationDir = file('build')
    }    
   
Puis empaqueter:

    $ gradle shadowDistZip
    