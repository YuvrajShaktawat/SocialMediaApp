package com.springproject.rest.webservices.restful_web_services.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springproject.rest.webservices.restful_web_services.user.Post;

public interface PostJpaRepository extends JpaRepository<Post, Integer> {


}
