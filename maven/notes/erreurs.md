# Erreurs courantes
	
## Could not resolve dependencies for project .... was cached in the local repository ...	
	
Message:	
        
    [ERROR] Failed to execute goal on project core: Could not resolve dependencies for project .... was cached in the local repository, 
    resolution will not be reattempted until the update interval of internal has elapsed or updates are forced -> [Help 1]
        
Solution:

    $ mvn dependency:purge-local-repository        
    
## Invalid signature file digest for Manifest main attributes        
	
Message:

		Exception in thread "main" java.lang.SecurityException: Invalid signature file digest for Manifest main attributes
			at sun.security.util.SignatureFileVerifier.processImpl(SignatureFileVerifier.java:287)
			at sun.security.util.SignatureFileVerifier.process(SignatureFileVerifier.java:240)
			at java.util.jar.JarVerifier.processEntry(JarVerifier.java:317)
			at java.util.jar.JarVerifier.update(JarVerifier.java:228)
			at java.util.jar.JarFile.initializeVerifier(JarFile.java:348)
			at java.util.jar.JarFile.getInputStream(JarFile.java:415)
			at sun.misc.URLClassPath$JarLoader$2.getInputStream(URLClassPath.java:775)
			at sun.misc.Resource.cachedInputStream(Resource.java:77)
			at sun.misc.Resource.getByteBuffer(Resource.java:160)
			at java.net.URLClassLoader.defineClass(URLClassLoader.java:436)
			at java.net.URLClassLoader.access$100(URLClassLoader.java:71)

Utiliser dans le pom.xml, juste sous plugins/execution/configuration:

	Source: http://hemika-kodikara.blogspot.fr/2015/03/creating-runnable-jar-using-maven.html
			<filters>
				<filter>
					<artifact>*:*</artifact>
					<excludes>
						<exclude>META-INF/*.SF</exclude>
						<exclude>META-INF/*.DSA</exclude>
						<exclude>META-INF/*.RSA</exclude>
					</excludes>
				</filter>
			</filters>


## vendorName == null

Modifier le fichier manifest.mf:

		<transformer
			implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
			<manifestEntries>
				<Main-Class>abcmap.Launcher</Main-Class>
				<Vendor-Name>abcd</Vendor-Name>
				<Implementation-Title>abcd</Implementation-Title>
				<Implementation-Version>abcd</Implementation-Version>
				<Specification-Vendor>abcd</Specification-Vendor>
				<Implementation-Vendor>abcd</Implementation-Vendor>
				<Specification-Title>abcd</Specification-Title>
				<Built-By>remipassmoilesel</Built-By>
				<Created-By>Apache Maven</Created-By>
			</manifestEntries>
		</transformer>


## Plugin execution not covered by lifecycle configuration

Ajouter les balises <pluginManagement> dans <build>:

	<build>
		<pluginManagement>
		...
		</pluginManagement>
	</build>


## cannot find symbol

Message:
    
    [ERROR] /.../interactionstats/InteractionStatsTests.java:[12,10] cannot find symbol
	[ERROR] symbol:   class Test
	[ERROR] location: class org.interactionstats.InteractionStatsTests

Cette erreur est déclenché car lorsque JUnit est importé comme dépendance (dans pom.xml) il 
l'est généralement avec pour scope "test". Donc lors d'un "mvn package" étant donné que 
les dépendances de test ne sont pas chargées il y a erreur. 

Solutions: changer le scope de la dépendance ou déplacer les classes de test dans un autre 
dossier de sources.

Source: http://stackoverflow.com/questions/15029634/maven-doesnt-find-org-junit-even-though-its-in-the-dependencies


## Les tests ne démarrent pas:

	Les noms de classes doivent finir ou commencer par Test

