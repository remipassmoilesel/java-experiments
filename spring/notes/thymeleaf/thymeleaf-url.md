# URL

Les liens et références doivent toutes comporter un préfixe correspondant au contexte de serveur.
Il peut être ajouté manuellement, ou avec Thymeleaf. Exemple pour une application au contexte 'app':
  
    <!-- Lien relatif au context path -->
    <a th:href="@{/order/list}">
    <a th:href="@{'/order/list' + ${iter.index}">
    
    <!-- Avec arguments -->
    <iframe th:src="@{/gh-activity/gh-activity.html(user='remipassmoilesel', type='user')"></iframe>
    
    <!-- Lien relatif au serveur -->
    <a th:href="@{~/billing-app/showDetails.htm}">
    
    <!-- Ajouter le context path dans du inline javascript -->
    var url = /*[[${#httpServletRequest.getContextPath() + mappings.get('SUBSCRIBE')}]]*/ null;

       
Source: http://www.thymeleaf.org/doc/articles/standardurlsyntax.html