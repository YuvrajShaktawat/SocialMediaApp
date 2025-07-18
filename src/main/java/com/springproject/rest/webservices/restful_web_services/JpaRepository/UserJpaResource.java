package com.springproject.rest.webservices.restful_web_services.JpaRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.springproject.rest.webservices.restful_web_services.user.Post;
import com.springproject.rest.webservices.restful_web_services.user.User;
import com.springproject.rest.webservices.restful_web_services.user.UserNotFoundException;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {

    private UserJpaRepository UsersDaoService;

    private PostJpaRepository postRepository;

    public UserJpaResource(UserJpaRepository UsersDaoService, PostJpaRepository postRepository) {
        this.UsersDaoService = UsersDaoService;
        this.postRepository = postRepository;
    }

    @GetMapping("/jpa/users")
    public List<User> allUsers() {
        return UsersDaoService.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> Users(@PathVariable int id) {
        Optional<User> user = UsersDaoService.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).allUsers());
        EntityModel<User> resource = EntityModel.of(user.get());
        resource.add(link.withRel("all-users"));

        return resource;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        UsersDaoService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        UsersDaoService.deleteById(id);
    }

    // @GetMapping("/hello-world-internationalized")
    // public String helloWorld(){
    // Locale locale = LocaleContextHolder.getLocale();
    // return messageSource.getMessage("hello.world.message", null, "Default
    // Message", locale);
    // }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> PostsFromUser(@PathVariable int id) {
        Optional<User> user = UsersDaoService.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        List<Post> posts = user.get().getPosts();

        return posts;
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Post> createUser(@PathVariable int id, @Valid @RequestBody Post post) {
        Optional<User> user = UsersDaoService.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        post.setUser(user.get());
        Post savedPost = postRepository.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
