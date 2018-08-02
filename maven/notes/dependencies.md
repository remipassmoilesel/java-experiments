# Dépendances

## Rafraichier les dépendances d'un projet

    $ mvn dependency:purge-local-repository


## Arbre de dépendances

    $ mvn dependency:tree
    
    
## Analyse de dépendances

Pour trouver des dépendances inutilisées:

    $ mvn dependency:analyze


## Importer une dépendance mais ignorer des sous dépendances

        <dependency>
            <groupId>com.awesome.library</groupId>
            <artifactId>iWantThisDependency</artifactId>
            <version>0.0.1-SNAPSHOT</version>
            <exclusions>
                <exclusion>
                    <groupId>com.awesome.library</groupId>
                    <artifactId>butNotThis</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
 