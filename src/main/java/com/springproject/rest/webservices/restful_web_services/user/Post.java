package com.springproject.rest.webservices.restful_web_services.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;

@Entity (name= "post_details")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 2, message = "message should have at least 2 characters")
    @JsonProperty("message")
    private String postMessage;

    @ManyToOne(fetch= FetchType.LAZY)
    @JsonIgnore
    private User user;

    public Post() {
     
    }

    public Post(int id, String postMessage, User user) {
        this.id = id;
        this.postMessage = postMessage;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return postMessage;
    }

    public void setMessage(String postMessage) {
        this.postMessage = postMessage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post []";
    }

}
