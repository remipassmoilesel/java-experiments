# Migration 

## Guide Spring boot 2.0

https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.0-Migration-Guide


## Verification de configuration

Il est possible d'ajouter ce paquet:
    
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-properties-migrator</artifactId>
        <scope>runtime</scope>
    </dependency>
    
Pour afficher les clefs de properties qui ont changé.    