package com.github.bttfkotlin.kotlincodelabktor

import com.github.bttfkotlin.kotlincodelabktor.entities.Event

val quote: String = "Hey !"
var quoteVar: String = "Hey !"

class ExampleClass(private val field: String) {
    fun method1(arg1: String) {
        println(arg1)
    }
}

val instance = ExampleClass("fieldValue")

data class User(val username: String = "default value")

val user1 = User("username")

fun main() {
    instance.method1("arg value")
    user1.copy("username-modified")
}

fun prettier(events: List<Event>): List<Event> {
    return events.filter { it.date.trim().isNotEmpty() }
}

fun forIn() {
    for (number in 1..200) {
        println(number)
    }
    for (number in 200 downTo 1) {
        println(number)
    }
}

fun whenUsage(username: String) {
    when (username) {
//        in
    }
}

fun ternaryLike(username: String) {
    val usernameCapitalized = if (username.isNotEmpty()) {
        username.toUpperCase()
    } else {
        "empty"
    }

    println(usernameCapitalized)
}

fun elvisPlusSafeCall(username: String?) {
    val usernameLength = username?.length;
    println(usernameLength)
}
