# Profils de build

Voir: https://maven.apache.org/guides/introduction/introduction-to-profiles.html

Afficher les profils actifs:

	$ mvn help:active-profiles

Spécifier un profil explicitement:

	$ mvn clean package -P profile-name

Spécifier un profile par défaut dans le pom:

	$ vim pom.xml

	<profile>
		<profiles>
			 <activation>
			         <activeByDefault>true</activeByDefault>
		         </activation>
		...
