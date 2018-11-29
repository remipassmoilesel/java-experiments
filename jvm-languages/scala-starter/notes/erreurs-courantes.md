# Erreurs courantes

## IntelliJ ne propose pas d'import pour les classes Java (java.util.* par exemple)

Ajouter les librairies nécéssaires (JRE, etc) dans la structure du projet:

    Project Structure > SDK > 1.8 > + > /usr/lib/jvm/java-8-openjdk-amd64/jre/lib/