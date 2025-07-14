package com.springproject.rest.webservices.restful_web_services.user;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

    private final UsersDaoService UsersDaoService;

    public UserResource(UsersDaoService UsersDaoService) {
        this.UsersDaoService = UsersDaoService;
    }

    @GetMapping("/users")
    public List<User> allUsers(){
        return UsersDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public User Users(@PathVariable int id){
        User user = UsersDaoService.findById(id);
        if (user == null) {
            throw new UserNotFoundException("User not found with id: " + id);            
        }
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        UsersDaoService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(location).build();    
    }
}
