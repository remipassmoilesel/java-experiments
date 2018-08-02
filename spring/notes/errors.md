# Erreurs possibles

## java.lang.annotation.AnnotationFormatError: Invalid default

    java.lang.annotation.AnnotationFormatError: Invalid default:
        public abstract org.springframework.beans.factory.annotation.Autowire
        org.springframework.config.java.annotation.Configuration.defaultAutowire()

La version de Spring java config n'est pas compatible


## Resolved exception caused by Handler execution: org.springframework.web.HttpMediaTypeNotAcceptableException: Could not find acceptable representation
     
L'objet à exporter est mal configuré, ajouter @XmlRootElement(name = "note")

Voir: https://docs.oracle.com/javase/tutorial/jaxb/intro/

     
## Error 405 HTTP method GET is not supported by this URL
    
Implémenter la méthode doGet() et enlever l'appel de super.doGet(req, resp);
    
    
## An Authentication object was not found in the SecurityContext
    
    <oauth>
        <error_description>
            An Authentication object was not found in the SecurityContext
        </error_description>
        <error>unauthorized</error>
    </oauth>
    
Voir config SpringSecurity:

    .and().anonymous().disable(); <- Enlever
    

## SpringJPAhikariCP - Connection is not available, request timed out after 30000ms.

Changer la taille du pool de connexions. Voir:

    http://blog.davidvassallo.me/2018/07/24/lessons-learned-spring-data-postgres-application-configuration/

    
## java.lang.IllegalStateException: couldn't determine local port. Please supply service-base-url. 

Erreur non significative, le client admin essai de s'enregistrer avant d'avoir eu connaissance de son port et son hostname.

L'enregistrement sera fait avec succès plus tard.


## Caused by: java.sql.SQLFeatureNotSupportedException: La fonction org.postgresql.jdbc.PgConnection.createClob() n'est pas encore implémentée.

Exception sans importance, voir: https://github.com/pgjdbc/pgjdbc/issues/1102

Une fonctionnalité n'est pas disponible avec postgresql et jdbc, hibernate le signale.

Solution:

    # Disable unimportant exception logging
    spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true    