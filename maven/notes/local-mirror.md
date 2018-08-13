# Utiliser un mirroir local Maven

## En tant que dépôt d'artefacts

    <project>
        
        <repositories>
            <repository>
                <id>internal</id>
                <name>Nexus Managed Internal Repository</name>
                <url>http://mavenrepository....</url>
                <releases>
                    <enabled>true</enabled>
                </releases>
                <snapshots>
                    <enabled>false</enabled>
                </snapshots>
            </repository>
            
     ...


## En tant que dépôt de plugins

    <project>
    
        <pluginRepositories>
    
            <pluginRepository>
                <id>internal-plugin</id>
                <name>Nexus Managed Internal Repository</name>
                <url>http://mavenrepository...</url>
                <releases>
                    <enabled>true</enabled>
                </releases>
                <snapshots>
                    <enabled>false</enabled>
                </snapshots>
            </pluginRepository>
    
        ...