package com.ucsdextandroid1.kotlinlist

/**
 * Created by rjaylward on 2019-06-08
 */

class Test {

//    private fun helloWorld(): String = "Hello World"

//    data class Person(val name: String, var age: Int)

    private class Human(val name: String)
    private class Animal(val species: String)

    private fun getNameOrSpecies(animalOrHuman: Any): String {
        return when(animalOrHuman) {
            is Animal -> animalOrHuman.species
            is Human -> animalOrHuman.name
            else -> throw IllegalArgumentException("Unknown")
        }
    }

}