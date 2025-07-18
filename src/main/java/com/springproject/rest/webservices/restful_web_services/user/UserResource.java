package com.springproject.rest.webservices.restful_web_services.user;

import java.net.URI;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserResource {

    MessageSource messageSource;


    private final UsersDaoService UsersDaoService;

    public UserResource(UsersDaoService UsersDaoService, MessageSource messageSource) {
        this.UsersDaoService = UsersDaoService;
        this.messageSource = messageSource;
    }

    @GetMapping("/users")
    public List<User> allUsers(){
        return UsersDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> Users(@PathVariable int id){
        User user = UsersDaoService.findById(id);
        if (user == null) {
            throw new UserNotFoundException("User not found with id: " + id);            
        }
        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).allUsers());
        EntityModel<User> resource = EntityModel.of(user);
        resource.add(link.withRel("all-users"));

        return resource;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        UsersDaoService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(location).build();    
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        if (UsersDaoService.findById(id) != null) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        UsersDaoService.deleteById(id);
    }

    @GetMapping("/hello-world-internationalized")
    public String helloWorld(){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("hello.world.message", null, "Default Message", locale);
    }
}
