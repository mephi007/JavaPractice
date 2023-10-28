package com.example.javapractice;

import java.util.ArrayList;
import java.util.List;

class Person{
    public String name;
    public int age;
    public String gender;// M/F

    public Person(String name, int age, String gender) {
      this.name = name;
      this.age = age;
      this.gender = gender;
    }

    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("A", 20, "M"));
        persons.add(new Person("B", 26, "M"));
        persons.add(new Person("C", 50, "F"));
        persons.add(new Person("D", 40, "M"));
        persons.add(new Person("E", 22, "M"));
        persons.add(new Person("F", 38, "M"));
        persons.add(new Person("G", 60, "F"));
        persons.add(new Person("H", 80, "M"));

        int avg_age = 0;
        persons.stream().forEach((person) -> {
            if(person.gender.equals("M")){
                avg_age += person.age;
            }
        });

        // for(Person p : persons){
        //     avg_age += p.age;
        // }
        // int avg = persons.stream().filter((person) -> {
        //     person.gender.equals("M");
        // }).reduce((val1, val2) -> val1+val2 ).collect();
        

        System.out.println(avg_age);
    
    }
    

  }

//   public class Test{

//   }