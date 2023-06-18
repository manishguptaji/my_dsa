package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class PassByValue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_by_value_example);

        Person p1 = new Person(10, "Manish");
        Person p2 = new Person(20, "Jack");

        function1(p1,p2);
        function2(p1,p2);
        function3(p1,p2);
        Log.e("PassValue1", p1.getAge() + "..." + p1.getName());
        Log.e("PassValue2", p2.getAge() + "..." + p2.getName());
    }

    private void function1(Person p1, Person p2) {
        Person temp = p1;
        p1 = p2;
        p2 = temp;
    }

    private void function2(Person p1, Person p2) {
        int age = p1.getAge();
        p1.setAge(p2.getAge());
        p2.setAge(age);

        String name = p1.getName();
        p1.setName(p2.getName());
        p2.setName(name);
    }

    private void function3(Person p1, Person p2) {
        p1 = new Person();
        int age = p1.getAge();
        p1.setAge(p2.getAge());
        p2.setAge(age);

        p2 = new Person();

        String name = p1.getName();
        p1.setName(p2.getName());
        p2.setName(name);
    }
}