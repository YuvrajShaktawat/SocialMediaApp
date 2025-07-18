package com.springproject.rest.webservices.restful_web_services.user;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

@Entity(name = "user_details")
public class User {

    @Id
    @GeneratedValue
    private int id;

    @Size(min = 2, message = "Name should have at least 2 characters")
    // @JsonProperty("user_name")
    private String name;

    @Min(value = 1, message = "Age should be at least 1")
    private int age;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Post> posts;

    public User() {

    }

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

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "User []";
    }

}
