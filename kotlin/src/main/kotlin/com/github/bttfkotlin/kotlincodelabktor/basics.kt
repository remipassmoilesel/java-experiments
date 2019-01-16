package com.github.bttfkotlin.kotlincodelabktor

val quote: String = "Hey !"
var quoteVar: String = "Hey !"

class ExampleClass(private val field: String){
    fun method1(arg1: String){
        println(arg1)
    }
}

val instance = ExampleClass("fieldValue")

data class User(val username: String = "default value")

val user1 = User("username")

fun main(){
    instance.method1("arg value")
    user1.copy("username-modified")
}

