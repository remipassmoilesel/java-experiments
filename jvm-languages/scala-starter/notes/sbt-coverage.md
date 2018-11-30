
# Faire de la couverture de code avec SBT

Voir: https://github.com/scoverage/sbt-scoverage

Ajouter le plugin sbt-coverage:

    $ vim project/plugins.sbt
    
    addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.5.1")

Activer l'instrumentation, lancer les tests, créer un rapport de couverture:

    $ sbt clean coverage test coverageReport
    
    