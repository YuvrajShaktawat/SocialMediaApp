package com.springproject.rest.webservices.restful_web_services.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UsersDaoService {
    
    private static int usersCount = 0;

    private static final List<User> users = new ArrayList<>(List.of(
            new User(++usersCount, "John Doe", 25),
            new User(++usersCount, "Jim Craen", 30),
            new User(++usersCount, "Alice Pandey", 35)
    ));

    public List<User> findAll() {
        return users;
    }

    public User findById(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public User save(User user) {
        user.setId(++usersCount);
        users.add(user); 
        return user;
    }

}
