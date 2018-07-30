# Variables

## Variables dans le POM

Exemple:
    
      <properties>
            <geotools.version>15.1</geotools.version>
      </properties>

Utilisation:

       <dependency>
          ....
          <version>${geotools.version}</version>
       </dependency>