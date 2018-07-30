# Recettes

Autre solution pour exécuter une classe main:

	<properties>
		<exec.mainClass>org.opencv.Stitching</exec.mainClass>
	</properties>

Puis:

	$ mvn compile exec:java -Dexec.args="/home/..."

Pour construire un jar éxecutable, ajouter dans le POM:

    <properties>
        <jdk.version>1.7</jdk.version>
    </properties>
	
    <build>
        <finalName>lcd-display</finalName>
        <plugins>

        <!-- Set a JDK compiler level -->
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>2.3.2</version>
            <configuration>
                <source>${jdk.version}</source>
                <target>${jdk.version}</target>
            </configuration>
        </plugin>

        <!-- Make this jar executable -->
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-jar-plugin</artifactId>
            <configuration>
                <excludes>
                    <!--<exclude>**/log4j.properties</exclude>-->
                </excludes>
                <archive>
                    <manifest>
                        <mainClass>remipassmoilesel.Launcher</mainClass>
                    </manifest>
                </archive>
            </configuration>
        </plugin>

	<!-- Copy project dependency -->
	<plugin>
		<groupId>org.apache.maven.plugins</groupId>
		<artifactId>maven-dependency-plugin</artifactId>
		<version>2.5.1</version>
		<executions>
		  <execution>
			<id>copy-dependencies</id>
			<phase>package</phase>
			<goals>
				<goal>copy-dependencies</goal>
			</goals>
			<configuration>
			  <!-- exclude junit, we need runtime dependency only -->
			  <includeScope>runtime</includeScope>
			  <outputDirectory>${project.build.directory}/dependency-jars/</outputDirectory>
			</configuration>
		  </execution>
		</executions>
	</plugin>


        </plugins>

    </build>

Puis executer:

		$ mvn package

Pour éxécuter un projet avec toutes les ressources et le répertoire courant dans le classpath:

	  <build>
		<plugins>

			....

		    <plugin>
			<groupId>org.codehaus.mojo</groupId>
			<artifactId>exec-maven-plugin</artifactId>
			<version>1.5.0</version>
			<executions>
			    <execution>
				<goals>
				    <goal>java</goal>
				</goals>
			    </execution>
			</executions>
			<configuration>
			    <executable>java</executable>
			    <arguments>
				<argument>-classpath</argument>
				<classpath/>
				<argument>org.abcmap.Launcher</argument>
			    </arguments>
			</configuration>
		    </plugin>
		</plugins>
	    </build>

	$ mvn compile
	$ mvn exec:exec

Exclure des dépendances d'une dépendance:

	 <dependency>
                <groupId>com.achatsolutions.selenee</groupId>
                <artifactId>seleneeSecurity</artifactId>
                <version>${selenee.security.version}</version>
                <exclusions>
                    <exclusion>
                        <groupId>org.springframework.boot</groupId>
                        <artifactId>spring-boot-starter</artifactId>
                    </exclusion>
                    <exclusion>
                        <groupId>org.springframework.boot</groupId>
                        <artifactId>spring-boot-starter-web</artifactId>
                    </exclusion>
                </exclusions>
            </dependency>

