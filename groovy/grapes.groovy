#!/usr/bin/env

// import dependency in ~/.groovy/grapes

@Grab(group='org.springframework', module='spring-orm', version='3.2.5.RELEASE')
import org.springframework.jdbc.core.JdbcTemplate

// shortcut
@Grab('org.springframework:spring-orm:3.2.5.RELEASE')

// add a repo
@GrabResolver(name='restlet', root='http://maven.restlet.org/')

// attach dependencies to system class loaders (eg for JDBC drivers)
@GrabConfig(systemClassLoader=true)

/*

Debug download

	$ export JAVA_OPTS="-Dgroovy.grape.report.downloads=true"
	 or -Divy.message.logger.level=4

Command line tool
	
	$ grape install <groupId> <artifactId> [<version>]"

*/


