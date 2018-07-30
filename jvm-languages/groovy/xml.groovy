#!/usr/bin/env groovy

// parse and explore document using XMLParser
// a usefull tool but for small documents
languages = new XmlParser().parse('sample.xml')

println "Languages and authors"

languages.each {
	println "${it.@name} authored by ${it.author[0].text()}"
}

def languagesByAuthor = { authorName ->
	languages
		.findAll { it.author[0].text() == authorName }
		// @name allow to access properties instead of childs
		.collect {it.@name }.join(', ')
}

println "Languages by Wirth:" + languagesByAuthor('Wirth')

// parse using XMLSlurper, better for larger documents
languages = new XmlSlurper().parse('sample.xml')

println "Languages and authors"

languages.language.each {
	println "${it.@name} authored by ${it.author[0].text()}"
}

def languagesByAuthor2 = { authorName ->

languages.language
	.findAll { it.author[0].text() == authorName }
	.collect { it.@name }.join(', ') 
}

println "Languages by Wirth:" + languagesByAuthor2('Wirth')

// access by namespace
languages = new XmlSlurper().parse('sample2.xml')
	.declareNamespace(human: 'Natural')

print "Languages: "
println languages.language.collect { it.@name }.join(', ')

print "Natural languages: "
println languages.'human:language'.collect { it.@name }.join(', ')
