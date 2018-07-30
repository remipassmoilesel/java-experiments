# Thymeleaf #lists

Thymeleaf propose un utilitaire (parmi d'autres) pour utiliser des dates dans les templates.

#list
    
    <div th:if="${#lists.isEmpty(customersList)}">Vide</div>
    <div th:if="${not #lists.isEmpty(customersList)}">Non vide</div>
    
    #lists.isEmpty()
    #lists.contains()
    #lists.containsAll()
    #lists.size()
    
Voir: http://www.thymeleaf.org/apidocs/thymeleaf/2.0.2/org/thymeleaf/expression/Lists.html