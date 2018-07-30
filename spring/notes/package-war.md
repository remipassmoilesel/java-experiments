# Créer une application WAR à partir d'une application Spring Boot

Changer le packaging pour war dans le fichier pom.xml:

    <?xml version="1.0" encoding="UTF-8"?>
    <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">

        <packaging>war</packaging>

Etendre la classe SpringBootServletInitializer pour initialiser le WAR au démarrage. Exemple: 

    @SpringBootApplication
    public class AbcmapFrApplication extends SpringBootServletInitializer {
    
        public static void main(String[] args) {
            ...
        }
    
        @Override
        protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
            return application.sources(AbcmapFrApplication.class);
        }
    
    }

Lancement:

    $ mvn package
    
    
/!\ à vérifier que le context path est pris en compte partout !