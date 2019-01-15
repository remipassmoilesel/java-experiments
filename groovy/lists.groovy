#!/usr/bin/env groovy

// Create an array list
lst = [1, 3, 4, 1, 8, 9, 2, 6]

println lst
println lst.getClass().name

// negatives indexes
println lst[-1]
println lst[-2]

subLst = lst[2..5]
println subLst.dump()

// return a copy
subLst[0] = 55
println "After subLst[0]=55 lst = $lst"

// iteration
lst.each { println it }

// closures
total = 0
lst.each { total += it }

println "Total is $total"

// apply closure on a copy
println lst.collect { it * 2 }
println "Original: $lst"

// find elements
println lst.find { it == 2 }
println lst.find { it > 4 }
println lst.findAll { it == 2 }

// chains
println lst.collect { it * 2 }.sum()

// list as argument
def words(a, b, c, d){
	println "$a $b $c $d"
}

words(*lst[0..3])

// multidimensioninal lists
lst2 = [["Be", "Productive"], "In", "Groovy"]
println lst2	
println lst2.flatten()

println lst2.size()
println lst2*.size()
