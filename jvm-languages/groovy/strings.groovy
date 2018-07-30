


def map = [:] 

// possible<identifiers
map."an identifier with a space and double quotes" = "ALLOWED" 
map.'with-dash-signs-and-single-quotes' = "ALLOWED" 

assert map."an identifier with a space and double quotes" == "ALLOWED" 
assert map.'with-dash-signs-and-single-quotes' == "ALLOWED"

// possible strings
map.'single quote' 
map."double quote" 
map.'''triple single quote''' 
map."""triple double quote""" 
map./slashy string/
map.$/dollar slashy string/$

// interpolated strings: value shift
def firstname = "Homer" 
map."Simson-${firstname}" = "Homer Simson" 
assert map.'Simson-Homer' == "Homer Simson"

def sum = "The sum of 2 and 3 equals ${2 + 3}" 
assert sum.toString() == 'The sum of 2 and 3 equals 5'

// with closures
def number = 1 
def eagerGString = "value == ${number}" 
def lazyGString = "value == ${ -> number }" 
assert eagerGString == "value == 1" 
assert lazyGString == "value == 1" 

number = 2 
assert eagerGString == "value == 1" 
assert lazyGString == "value == 2" 

// GStrings and strings does not have the same hashcode
// beware with hashmap keys
assert "one: ${1}".hashCode() != "one: 1".hashCode()

def name = 'Groovy' 
def template = """ 

Dear Mr ${name}, 

You're the winner of the lottery! Yours sincerly, Dave 

""" 

assert template.toString().contains('Groovy')

