# Thymeleaf layout dialect

Permet d'utiliser un template "principal" qui décore du contenu.
Dans l'exemple qui suit, "layout.html" décore "content1.html" et éventuellement "content2.html", etc ...

## pom.xml

Ajouter les dépendances suivantes au project Spring Boot:

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
 
     
    <dependency>
        <groupId>nz.net.ultraq.thymeleaf</groupId>
        <artifactId>thymeleaf-layout-dialect</artifactId>
        <version>1.3.1</version>
    </dependency>
    
Spring Boot configure automatiquement le SpringTemplateEngine.

## Créer un layout, pour décorer du contenu
    
    <!DOCTYPE html>
    <html xmlns:th="http://www.thymeleaf.org" xmlns:layout="http://www.ultraq.net.nz/web/thymeleaf/layout">
    <head>
        <!-- Le titre sera remplacé par le titre du contenu -->
        <title layout:title-pattern="$LAYOUT_TITLE - $CONTENT_TITLE">Layout</title>
        
        <!-- Le contenu de head sera cloné dans le décorator-->
        <script>
        <link>
        ... 
        
    </head>
    <body>
    
        ...
        
        <!-- Sera remplacé par l'élément "header" du content -->
        <h1 layout:fragment="header">This is a layout header</h1>
    
        <!-- Sera remplacé par l'élément "content" du content -->
        <div layout:fragment="content">A placeholder for real content.</div>
        
## Créer un contenu, décoré par un layout
    
    <!DOCTYPE html>
    <html xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout" layout:decorator="layout/layout">
    <head>
        <title>Content page</title>
        ...
    </head>
    <body>
    
        <!-- Contenu -->
        <section layout:fragment="content">
            <p>And this is content from content page.</p>
        </section>
        
        ...
        
## Servir le contenu

    @RequestMapping(value = Mappings.LAYOUT_EXAMPLE, method = RequestMethod.GET)
    public String displayLayoutExample() {
        return "layout/content1";
    }