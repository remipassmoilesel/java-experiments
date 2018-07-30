# Créer un installeur IzPack

## Configuration générale

Configuration:

    build.gradle:
    
    izpack {
        installFile = file('src/main/resources/installer/install.xml')
        baseDir = file('build/')
        outputFile = file("$buildDir/distributions/izpack.jar")
        compression = 'deflate'
        compressionLevel = 5
    }
    
    dependencies {
        izpack('org.codehaus.izpack:izpack-standalone-compiler:4.3.4')
    }
    
    buildscript {
    	dependencies {
            classpath 'com.bmuschko:gradle-izpack-plugin:2.1'
    	}
    }


Fichier descripteur d'installation:
    
    <?xml version="1.0" encoding="iso-8859-1" standalone="yes" ?>
    
    <installation version="1.0">
        <info>
            <appname>JavaFX-experiments</appname>
            <appversion>1.0.0</appversion>
            <authors>
                <author name="Remi Passmoilesel" email="remi.passmoilesel@gmail.com"/>
            </authors>
            <url>http://site.com</url>
        </info>
    
        <guiprefs width="640" height="480" resizable="yes"/>
    
        <locale>
            <langpack iso3="eng"/>
            <!--<langpack iso3="fr"/>-->
        </locale>
    
        <resources>
            <res id="installer.langsel.img" src="resources/main/images/logo.png" />
            <res id="Installer.image" src="resources/main/images/logo.png" />
            <res id="LicencePanel.licence" src="resources/main/installer/LICENSE.txt"/>
            <res id="InfoPanel.info" src="resources/main/installer/README.md"/>
        </resources>
    
        <panels>
            <panel classname="HelloPanel"/>
            <panel classname="InfoPanel"/>
            <panel classname="LicencePanel"/>
            <panel classname="TargetPanel"/>
            <panel classname="PacksPanel"/>
            <panel classname="InstallPanel"/>
            <panel classname="FinishPanel"/>
        </panels>
    
        <packs>
            <pack name="JavaFX-Experiments" required="yes">
                <description>JavaFX Experiments main package</description>
                <file src="distributions/javafx-experiments-0.0.1-SNAPSHOT.zip" targetdir="$INSTALL_PATH"/>
            </pack>
        </packs>
    
    </installation>

Exécution: 

    $  gradle clean && gradle shadowJar && gradle izPackCreateInstaller  
    
## Rendre un fichier éxecutable

Il est possible également de lancer un script avant/après installation:

      <file src="resources/main/installer/launch.sh" targetdir="$INSTALL_PATH"/>
        <executable targetfile="$INSTALL_PATH/launch.sh" type="bin" stage="never"></executable>
