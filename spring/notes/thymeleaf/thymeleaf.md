# Thymeleaf

Mémo intéréssant: https://github.com/engma/thyemeleaf-cheat-sheet

Insérer une variable:

    <p th:text="${content}"/>
    
    <!-- utet permet de ne pas échaper du contenu (HTML par exemple)-->
    <p th:utext="${content}"/>
    
    <!-- Avec un getter -->
    <p th:utext="${currentNote.getHtml()}"/>
    
Pour eviter les erreurs sur les objets null, utiliser '?':

    <td th:text="${customer?.getPhoneNumber()}">Phone number</td>
    
Changer un attribut arbitraire:

    th:attr="data-primary-value=*{sharedResourceId}">
    
Ajouter une classe:

    <td class="fa" th:classappend="${service.isPaid()} ? 'fa-check' : 'fa-times'">X</td>
    
## Différents types d'expressions (Thymeleaf Standard Expression syntax)

Source: http://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#standard-expression-syntax

    Simple expressions:
        Variable Expressions:               ${...}
        Selection Variable Expressions:     *{...}
        Message Expressions:                #{...}
        Link URL Expressions:               @{...}
        Fragment Expressions:               ~{...}
    Literals
        Text literals:                      'one text', 'Another one!',…
        Number literals:                    0, 34, 3.0, 12.3,…
        Boolean literals:                   true, false
        Null literal:                       null
        Literal tokens:                     one, sometext, main,…
    Text operations:
        String concatenation:               +
        Literal substitutions:              |The name is ${name}|
    Arithmetic operations:
        Binary operators:                   +, -, *, /, %
        Minus sign (unary operator):        -
    Boolean operations:
        Binary operators:                   and, or
        Boolean negation (unary operator):  !, not
    Comparisons and equality:
        Comparators:                        >, <, >=, <= (gt, lt, ge, le)
        Equality operators:                 ==, != (eq, ne)
    Conditional operators:
        If-then:                            (if) ? (then)
        If-then-else:                       (if) ? (then) : (else)
        Default:                            (value) ?: (defaultvalue)
    Special tokens:
        No-Operation:                       _
 

 
Différence ${} et *{}: * utilise les valeurs des objets sélectionnés avec th:object="${objectName}". Exemple:
 
        <div th:object="${session.user}">
         <p>Name: <span th:text="*{firstName}">Sebastian</span>.</p>
         <p>Surname: <span th:text="*{lastName}">Pepper</span>.</p>
         <p>Nationality: <span th:text="*{nationality}">Saturn</span>.</p>
        </div>
 
        <!-- Mixed expressions. Below #object is a keyword for selected object -->
 
        <div th:object="${session.user}">
          <p>Name: <span th:text="${#object.firstName}">Sebastian</span>.</p>
          <p>Surname: <span th:text="${session.user.lastName}">Pepper</span>.</p>
          <p>Nationality: <span th:text="*{nationality}">Saturn</span>.</p>
        </div>

Traitement des urls avec l'expression URL:

    <a th:href="@{${url}(orderId=${o.id})}">view</a>
    <a th:href="@{'/details/'+${user.login}(orderId=${o.id})}">view</a>

## Objets utilitaires

    #execInfo: information about the template being processed.
    #messages: methods for obtaining externalized messages inside variables expressions, in the same way as they would be obtained using #{…} syntax.
    #uris: methods for escaping parts of URLs/URIs
    #conversions: methods for executing the configured conversion service (if any).
    #dates: methods for java.util.Date objects: formatting, component extraction, etc.
    #calendars: analogous to #dates, but for java.util.Calendar objects.
    #numbers: methods for formatting numeric objects.
    #strings: methods for String objects: contains, startsWith, prepending/appending, etc.
    #objects: methods for objects in general.
    #bools: methods for boolean evaluation.
    #arrays: methods for arrays.
    #lists: methods for lists.
    #sets: methods for sets.
    #maps: methods for maps.
    #aggregates: methods for creating aggregates on arrays or collections.
    #ids: methods for dealing with id attributes that might be repeated (for example, as a result of an iteration).

Conversion de dates
    th:text="${#calendars.format(today,'dd MMMM yyyy')}"

## Changer un attribut

Exemple:

    <td><a href="notes/display/note_name" th:attr="href='notes/display/' + ${name}" th:text="${name}">Name</a></td>
    
## Itération

Itération simple de chaines de caractères (Arraylist par exemple):

    <table>
        <tr th:each="name : ${notesList}">
            <td><a href="notes?name=note_name" th:attr="href='note?name=' + ${name}" th:text="${name}">Name</a></td>
        </tr>
    </table>

## Validation de formulaires

    Voir https://spring.io/guides/gs/validating-form-input/

## Conditions

    <span th:if="${1 == 0}">
    
    <!-- Attention: ne pas utiliser < > -->
    <span th:if="${1 &lt; 0}">

        &lt; for < 
        &gt; for >
        &le; for <= 
        &ge; for >=
        
    <span th:if="${1 and 0}">
    <span th:if="${1 or 0}">
    
    
    <div th:switch="${user.role}"> 
      <p th:case="'admin'">User is an administrator</p>
      <p th:case="#{roles.manager}">User is a manager</p>
      <p th:case="*">User is some other thing</p> 
    </div>