# Fragments Thymeleaf
 
Les fragments permette de réutiliser du code.

Exemple avec un patron principal appelé main-template.html:

    <!DOCTYPE HTML>
    <html xmlns:th="http://www.thymeleaf.org">
    
    <!-- FRAGMENT 1 -->
    <head th:fragment="page-headers">
    
        <!-- <head th:fragment="page-headers" th:remove="tag"> Pour enlever le tag "header" à l'insertion-->
    
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    
        <link rel="stylesheet" type="text/css" href="/bower_components/bootstrap/dist/css/bootstrap.css"/>
        <script type="text/javascript" src="/bower_components/bootstrap/dist/js/bootstrap.js"></script>
        
    </head>
    
    <body>
    
        ...
            
            <!-- FRAGMENT 2 -->
            
            <div th:fragment="message-small">
                <div>
                    <h4 th:text="${title}">Small message / Date / Writer</h4>
                    <p th:text="${message}">Lorem ipsum dolor sit amet, consectetur adipiscing elit...</p>
                </div>
            </div>
        ...
        
    ...
    
Pour les utiliser dans une vue Thymeleaf:

    <!-- FRAGMENT 1: remplacement total de la balise -->
    
    <script th:replace="main-template :: page-headers"></script>

    
    <!-- FRAGMENT 2: insertion d'une partie du template avec parametres -->
    
    <div th:each="messageObject : ${lastMessagesList}">
        <div th:replace="main-template :: message-small (
                                            title=${messageObject.getDate()},
                                            message=${#strings.substring(messageObject.getMessage(), 0, 200) + '...'})">
        </div>
    </div>
    
Syntaxe:

    path/to/template :: template-part-name (var1=val, var2=val)
    
Autres exemples:

    <!-- include nav bar -->
    <div th:replace="fragments/navbar"></div>

    <!-- brackets are optionnal -->
    <div th:insert="~{footer :: copy}"></div>
    <div th:insert="footer :: copy"></div>

Exemple avec: 

    <!DOCTYPE html>
    
    <html xmlns:th="http://www.thymeleaf.org">
    
      <body>
      
        <div th:fragment="copy">
          &copy; 2011 The Good Thymes Virtual Grocery
        </div>
      
      </body>
      
    </html>
    
    <!-- 
        These expressions will only insert selected elements (fragment=copy) 
        
        <div th:insert="~{templatename :: selector}"></div>
    -->
    
    <div th:insert="~{footer :: copy}"></div>
    <div th:insert="footer :: copy"></div>
    
    <!-- include from present fragment -->
    <div th:insert="::copy"></div>
    
    <!-- conditionnal insertion -->
    <div th:insert="footer :: (${user.isAdmin}? #{footer.admin} : #{footer.normaluser})"></div>
    
    <!-- select fragments by id or other attributes -->
    <div th:insert="~{footer :: #copy-section}"></div>
    