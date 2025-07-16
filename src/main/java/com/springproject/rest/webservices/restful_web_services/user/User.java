package com.springproject.rest.webservices.restful_web_services.user;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class User {

    private int id;
    
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;
    
    @Min(value = 1, message = "Age should be at least 1")
    private int age;

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User []";
    }

}
