package com.ucsdextandroid1.kotlinlist;

import android.util.Log;
import android.view.View;

import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

/**
 * Created by rjaylward on 2019-06-08
 */
public class JavaUnitText {

    private String helloWorld() {
        return "Hello World";
    }

    private void doSomething() {
        Collections.sort(Collections.emptyList(), (Comparator<Integer>) (o1, o2) -> 0);

        PropertyDemo demo = new PropertyDemo();

        demo.getCount();
    }

      public class Person {

           private String name;
           private int age;

           public Person(String name, int age) {
                   this.name = name;
                   this.age = age;
           }

           public String getName() {
               return name;
           }

           public int getAge() {
                return age;
           }

           public void setAge(int newAge) {
                this.age = newAge;
          }

      }

      //    class PropertyDemo {
    //
    //        private var _counter = 0
    //
    //        val count: Int get() = TODO()
    //
    //        var propertyWithCounter: String? = null
    //            set(property) {
    //                field = property
    //                TODO("iterate counter")
    //            }
    //    }

    public class PropertyDemo {

        private int counter;
        private String propertyWithCounter;

        public int getCount() {
            return counter;
        }

        public String getPropertyWithCounter() {
            return propertyWithCounter;
        }

        public void setPropertyWithCounter(String propertyWithCounter) {
            this.counter += 1;
            this.propertyWithCounter = propertyWithCounter;
        }

    }

}