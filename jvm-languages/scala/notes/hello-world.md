# Projet Hello World en scala

Installer Scala Build Tool:

    $ echo "deb https://dl.bintray.com/sbt/debian /" | sudo tee -a /etc/apt/sources.list.d/sbt.list
    $ sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 2EE0EA64E40A89B84B2DF73499E82A75642AC823
    $ sudo apt-get update
    $ sudo apt-get install sbt

Initialiser un projet à partir d'un template Github:

    $ sbt new scala/hello-world.g8

Lancer le projet en console:

    $ cd hello-world-template
    $ sbt
    > run

Ou directement:

    $ sbt run