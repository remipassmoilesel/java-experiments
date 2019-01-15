#!/usr/bin/env groovy

@GrabConfig(systemClassLoader=true)
@Grab(group='mysql', module='mysql-connector-java', version='5.1.6')

def userid = 'groovy'
def password = 'groovy'

def sql = groovy.sql.Sql.newInstance('jdbc:mysql://localhost:3306/weatherinfo', userid, password, 'com.mysql.jdbc.Driver')

println sql.connection.catalog
