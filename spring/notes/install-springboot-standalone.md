# Installer une application Spring Boot en autonome

Installer le projet

    $ cd /opt
    $ git clone https://github.com/remipassmoilesel/simple-hostel-management
    $ cd simple-hostel-management

Choisir l'emplacement du jar:

    $ mv target/bookingsystem.jar .

Changer les droits et s'assurer que l'on peut l'éxecuter:

    $ chmod +x bookingsystem.jar
    $ ./bookingsystem.jar
    
Si ça ne fonctionne pas, installer jarwrapper:

    $ sudo apt install jarwrapper
    
Créer un fichier de service V:
    
    $ sudo vim /etc/systemd/system/bookingsystem.service
        
    [Unit]
    Description=bookingsystem
    After=syslog.target
    
    [Service]
    User=myapp
    ExecStart=/opt/simple-hostel-management/bookingsystem.jar
    SuccessExitStatus=143
    
    [Install]
    WantedBy=multi-user.target

Démarrer et vérifier le statut:

    $ sudo systemctl daemon-reload                                                                                                                                                                                                                                      [13:49:44]
    $ sudo systemctl start bookingsystem.service                                                                                                                                                                                                                        [13:50:59]
    $ sudo systemctl status bookingsystem.service                                                                                                                                                                                                                        [13:50:59]

