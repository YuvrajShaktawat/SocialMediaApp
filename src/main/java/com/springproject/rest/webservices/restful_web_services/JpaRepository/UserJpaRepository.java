package com.springproject.rest.webservices.restful_web_services.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springproject.rest.webservices.restful_web_services.user.User;

public interface UserJpaRepository extends JpaRepository<User, Integer> {

}
