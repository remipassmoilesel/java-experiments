#!/usr/bin/env groovy

// create a map
langs = ['C++' : 'Stroustrup', 'Java' : 'Gosling', 'Lisp' : 'McCarthy']
println langs.getClass().name

// find by keys
println langs['Java']
println langs['C++']

// operator overloading
println langs.Java
println langs.'C++'
println langs.Java++

// iterating
langs.each { entry ->
	println "Language $entry.key was authored by $entry.value"
}

langs.each { language, author ->
	println "Language $language was authored by $author"
}

// apply closure on a copy
println langs.collect { language, author ->
	language.replaceAll("[+]", "P")
}

// find
println "Looking for the first language with name greater than 3 characters"

entry = langs.find { language, author ->
	language.size() > 3
}

println "Found $entry.key written by $entry.value"

// find all
println "Looking for all languages with name greater than 3 characters"

selected = langs.findAll { language, author ->
	language.size() > 3
}

selected.each { key, value ->
	println "Found $key written by $value"
}

// check if any, if all
print "Does any language name have a nonalphabetic character? "

println langs.any {language, author ->
	language =~ "[^A-Za-z]"
}

print "Do all language names have a nonalphabetic character? "

println langs.every {language, author ->
	language =~ "[^A-Za-z]"
}

// group by
friends = [ briang = 'Brian Goetz', brians = 'Brian Sletten',
	davidb = 'David Bock', davidg = 'David Geary',
	scottd = 'Scott Davis', scottl = 'Scott Leberknight',
	stuarth = 'Stuart Halloway']

groupByFirstName = friends.groupBy { it.split(' ')[0] }

groupByFirstName.each { firstName, buddies ->
	println "$firstName : ${buddies.join(', ')}"
}
