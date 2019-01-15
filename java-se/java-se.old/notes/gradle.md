# Gradle

## Commandes courantes

Afficher les tâches dispos:

    $ gradle tasks

## Configuration

Ajouter un dépôt:

    repositories {

        maven {
            url "http://download.osgeo.org/webdav/geotools/"
        }
        maven {
            url "http://repo.boundlessgeo.com/main"
        }
        mavenCentral()
        mavenLocal()
        jcenter()
    }

Utiliser des variables:

    def geotoolsVersion = '17.2'
    dependencies {
        compile("org.geotools:gt-wms:${geotoolsVersion}")
    }

Télécharger les docs et sources (pour Intellij, mais fonctionne pour Eclipse etc...):

    idea {
        module {
            downloadJavadoc = true
            downloadSources = true
        }
    }
    
Installer cobertura pour observer la couverture de code:

    buildscript {
        dependencies {
            classpath "net.saliman:gradle-cobertura-plugin:2.5.0"
        }
    
        repositories {
            mavenCentral()
            jcenter()
        }
    
    }


    apply plugin: 'net.saliman.cobertura'
    
    // see https://github.com/stevesaliman/gradle-cobertura-plugin/blob/master/usage.md
    cobertura {
        coverageReportDir = file('./coverage')
        coverageFormats = ['html']
    }    