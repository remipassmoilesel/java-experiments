#!/usr/bin/env groovy

// identify gdk objects or return toString()
str = 'hello'
println str
println str.dump()

// execute closure in object context
lst = [1, 2]
lst.identity {
	add(3)
	add(4)
	println size()
	println contains(2)

	println "this is ${this},"
	println "owner is ${owner},"
	println "delegate is ${delegate}."
}

// sleep without catch interruptex
thread = Thread.start {
	println "Thread started"
	startTime = System.nanoTime()
	new Object().sleep(500)
	endTime = System.nanoTime()
	println "Thread done in ${(endTime - startTime)/10**9} seconds"
}

// access properties
class Car{
	int miles, fuelLevel
}

car = new Car(fuelLevel: 80, miles: 25)
properties = ['miles', 'fuelLevel']

properties.each { name ->
	println "$name = ${car[name]}"
}

car[properties[1]] = 100

println "fuelLevel now is ${car.fuelLevel}"

// indirect method invoke
class Person{
	def walk() { println "Walking..." }
	def walk(int miles) { println "Walking $miles miles..." }
	def walk(int miles, String where) { println "Walking $miles miles $where..." }
}

peter = new Person()
peter.invokeMethod("walk", null)
peter.invokeMethod("walk", 10)
peter.invokeMethod("walk", [2, 'uphill'] as Object[])

// array extensio'
int[] arr = [1, 2, 3, 4, 5, 6]
println arr[2..4]

// read a file
println new File('gdk.groovy').text.size()

// apply closure to each lone
new File('gdk.groovy').eachLine { line ->
	println line.size()
}

// filter lines
println new File('gdk.groovy').filterLine { it =~ /invoke/ }

// lire avec un bufferedreader
new File("gdk.groovy").withReader{ bread ->
	println bread.getClass().name
}

// write to file with a bufferedwriter
new File("output.txt").withWriter{ file ->
	file << "some data..."
}
