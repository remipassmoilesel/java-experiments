#!/usr/bin/env groovy

// regex
obj = ~"hello"
println obj.getClass().name

pattern = ~"(G|g)roovy"
text = 'Groovy is Hip'

// partial match
if (text =~ pattern)
	println "operator =~ match"
else
	println "operator ~= no match"

// exact match
if (text ==~ pattern)
	println "operator ==~ match"
else
	println "operator ==~ no match"

// get a matcher object
matcher = 'Groovy is groovy' =~ /(G|g)roovy/
print "Size of matcher is ${matcher.size()} "
println "with elements ${matcher[0]} and ${matcher[1]}."

// replace
str = 'Groovy is groovy, really groovy'
println str
result = (str =~ /groovy/).replaceAll('hip')
println result
