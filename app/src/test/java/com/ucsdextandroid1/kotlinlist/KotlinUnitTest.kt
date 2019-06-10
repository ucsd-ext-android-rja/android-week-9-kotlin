package com.ucsdextandroid1.kotlinlist

import android.graphics.Color
import android.view.View
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*
import kotlin.Comparator
import kotlin.test.assertNotEquals

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class KotlinUnitTest {

    /**
     * example 1: syntax
     * private String helloWorld() { return "Hello World"; }
     */
    private fun helloWorld(): String = "Hello World"

    @Test
    fun example_1_test() {
        assertEquals("Hello World", helloWorld())
    }

    /**
     * example 2: named arguments
     * Using named arguments join the string with a " " separator and a ! postfix
     */
    private fun joinOptions(options: Collection<String>) = options.joinToString(
            separator = " ", postfix = "!"
    )

    @Test
    fun example_2_test() {
        assertEquals("Kotlin is fun!", joinOptions(listOf("Kotlin", "is", "fun")))
    }

    /**
     * example 3: default arguments
     *
     * Add default day 1
     * Add default month 1
     * Add default year 2000
     */
    private fun createDate(day: Int = 1, month: Int = 1, year: Int = 2000) = "$month/$day/$year"

    @Test
    fun example_3_test() {
        assertEquals(createDate(), "1/1/2000")
        assertEquals(createDate(3), "1/3/2000")
        assertEquals(createDate(3, 9), "9/3/2000")
        assertEquals(createDate(3, 9, 1999), "9/3/1999")
    }

    /**
     * example 4: lambdas
     *
     * Add code to the lambda function that returns true if the number is even. In Java the
     * code would look something like this
     *
     * collection.any(item -> { return item % 2 == 0; });
     */
    private fun containsAnyEvenNumbers(collection: Collection<Int>): Boolean = collection.any {
        it % 2 == 0
    }

    @Test
    fun example_4_test() {
        assertEquals(true, containsAnyEvenNumbers(listOf(2, 4, 6)))
        assertEquals(false, containsAnyEvenNumbers(listOf(1, 3, 5, 7)))
    }

    /**
     * example 5: data classes
     *
     * Convert this java class to a data class
     *
     * public class Person {
     *      private String name;
     *      private int age;
     *
     *      public Person(String name, int age) {
     *              this.name = name;
     *              this.age = age;
     *      }
     *
     *      public String getName() {
     *          return name;
     *      }
     *
     *      public int getAge() {
     *           return age;
     *      }
     *
     *      public void setAge(int newAge) {
     *           this.age = newAge
     *      }
     * }
     */
    data class Person(val name: String, var age: Int)

    @Test
    fun example_5_test() {
        //TODO uncomment this code to test
        val person = Person("rj", 26)

        assertEquals("rj", person.name)
        assertEquals(26, person.age)

        assertEquals(Person("rj", 26), person)

        person.age = 27
        assertEquals(27, person.age)
    }

    class School(val classroom: Classroom?)
    class Classroom(val students: List<Student>?)
    class Student(val name: String)

    /**
     * example 6: nullability
     *
     * use the null safe operator to safely access the student names
     *
     * equivalent java code
     *
     * if(school != null && school.classroom != null && school.classroom.students != null)
     *      ... loop through all the student names
     * else
     *      return Collections.emptyList()
     *
     */
    private fun getAllStudentNames(school: School?): List<String> {
        return school?.classroom?.students?.map { it.name } ?: emptyList()
    }

    @Test
    fun example_6_test() {
        assertEquals(
                listOf("student 1", "student 2", "student 3"),
                getAllStudentNames(
                        School(Classroom(listOf(
                                Student("student 1"),
                                Student("student 2"),
                                Student("student 3")
                        )))
                )
        )
    }

    /**
     * example 7: Smart Casting
     *
     * Convert this java method to a when statement with smart casting
     *
     * private String getNameOrSpecies(Object animalOrHuman) {
     *       if(animalOrHuman instanceof Animal) {
     *           return ((Animal) animalOrHuman).getSpecies();
     *       }
     *       else if(animalOrHuman instanceof Human) {
     *           return ((Human) animalOrHuman).getName();
     *       }
     *       else throw new IllegalArgumentException("Unknown");
     *  }
     */
    private class Human(val name: String)
    private class Animal(val species: String)

    private fun getNameOrSpecies(animalOrHuman: Any): String {
        return when(animalOrHuman) {
            is Animal -> animalOrHuman.species
            is Human -> animalOrHuman.name
            else -> throw IllegalArgumentException("Unknown")
        }
    }

    @Test
    fun example_7_test() {
        assertEquals("rj", getNameOrSpecies(Human("rj")))
        assertEquals("dog", getNameOrSpecies(Animal("dog")))
    }

    /**
     * example 8: Extension Methods
     *
     * Convert this utility method to an extension method on the Random class
     *
     * @ColorInt
     * public static int toRandomColor(Random random) {
     * return Color.argb(
     *      255,
     *      random.nextInt(256),
     *      random.nextInt(256),
     *      random.nextInt(256)
     *   );
     * }
     */

    private fun Random.toRandomColor(): Int {
        return Color.argb(
                255,
                this.nextInt(256),
                this.nextInt(256),
                this.nextInt(256)
        )
    }

    @Test
    fun example_8_test() {
        val color = Random().toRandomColor()
        assertNotEquals(color, Random().toRandomColor())
        assertNotEquals(color, Random().toRandomColor())
        assertNotEquals(color, Random().toRandomColor())
        assertNotEquals(color, Random().toRandomColor())
    }

    /**
     * example 8a: object anonymous classes
     *
     * Create a sorted list but use the object keyword and the long form of the anonymous Comparator
     * class instead of the lambda. The Comparator should be equivalent the java code
     *
     *         Collections.sort(list, new Comparator<Integer>() {
     *              @Override
     *              public int compare(Integer x, Integer y) {
     *                  return y - x;
     *              }
     *          });
     *
     */

    fun getSortedListWithObjectNotation(item1: Int, item2: Int, item3: Int): List<Int> {
        val arrayList = listOf(item1, item2, item3)

        Collections.sort(arrayList, object : Comparator<Int> {

            override fun compare(x: Int, y: Int): Int {
                return y - x
            }

        })

        return arrayList
    }

    /**
     * example 8b: SAM conversion
     *
     * Create a sorted list but use the object keyword and the long form of the anonymous Comparator
     * class instead of the lambda. The Comparator should be equivalent the java code
     *
     *     Collections.sort(list, (x, y) -> {
     *          return y - x;
     *     });
     *
     */
    fun getSortedListWithLambdaNotation(item1: Int, item2: Int, item3: Int): List<Int> {
        val arrayList = listOf(item1, item2, item3)

//        Collections.sort(arrayList) { x, y -> y - x }

        Collections.sort(arrayList) { x: Int, y: Int ->
            return@sort y - x
        }

        return arrayList
    }

    @Test
    fun example_9_test() {
        assertEquals(listOf(7, 5, 3), getSortedListWithObjectNotation(7, 3, 5))
        assertEquals(listOf(7, 5, 3), getSortedListWithLambdaNotation(7, 3, 5))

    }

    class PropertyDemo {

        var count: Int = 0
            private set

        var propertyWithCounter: String? = null
            set(property) {
                field = property
                count += 1
            }

        val countWithProperty: String get() = "$propertyWithCounter $count"

        fun getCountAndProperty(): String {
            return "$count $propertyWithCounter"
        }
    }

    @Test
    fun example_10_test() {
        val property = PropertyDemo()

        assertEquals(0, property.count)

        property.propertyWithCounter = "something"
        assertEquals(1, property.count)

        property.propertyWithCounter = "something else"
        assertEquals(2, property.count)

    }

    //TODO other things to go over include: Lazy, lateinit

}