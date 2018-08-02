# Insérer du code en ligne

Utiliser l'espression: 
    
    /*[[...]]*/

Exemple: 

    <script th:inline="javascript">
    /*<![CDATA[*/
        ...
    
        var username = /*[[${session.user.name}]]*/ 'Sebastian';
    
        ...
    /*]]>*/
    </script>
    
Pour éviter des erreurs liée au parsing de Javascript par Thymeleaf (< & ..), utiliser:

    /*<![CDATA[*/
    
    var codejs ....
    
    /*]]>*/
    
    
Attention:

    - Ne pas oublier le <script th:inline="javascript">
    - 'Sebastian' sera affiché si la page est statique
    - Tout ce qui suit /*[[...]]*/ sera supprimé au remplacement ( 'Sebastian' )
     