
// !!!! Never use bad chars in file names (like -)

0.upto(2) { print "ho $it "}
print "Merry Groovy!"

// start from zero
3.times { print "$it "}

// start from 0, end at 10 but with steps
0.step(10, 2) { println "$it "}

// invole proceds
println "git status".execute().text
println "git status".execute().getClass().name

// safe operator no avood nullpointerex
def foo(str){
	//if (str != null) { return str.reverse() }
	str?.reverse()
}
println foo('evil')
println foo(null)

// optionnal parameters
def log(x, base=10){
	Math.log(x) / Math.log(base)
}

println log(1024)
println log(1024, 10)
println log(1024, 2)

// init with named parameters
class Robot{

	def type, height, width
	def access(location, weight, fragile){
		println "Received fragile? $fragile, weight: $weight, loc: $location"
	}
}

robot = new Robot(type: 'arm', width: 10, height: 40)
println "$robot.type, $robot.height, $robot.width"

// arguments in disorder and grouped in maps
robot.access(50, x: 30, y: 20, z: 10, true)
