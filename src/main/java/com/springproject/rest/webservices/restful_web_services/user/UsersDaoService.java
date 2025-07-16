package com.springproject.rest.webservices.restful_web_services.user;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.stereotype.Component;

@Component
public class UsersDaoService {

    private static int usersCount = 0;
    private Queue<Integer> freeIds = new LinkedList<>();

    private static final List<User> users = new ArrayList<>(List.of(
            new User(++usersCount, "John Doe", 25),
            new User(++usersCount, "Jim Craen", 30),
            new User(++usersCount, "Alice Pandey", 35)));

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
        if(freeIds.isEmpty()) {
            user.setId(++usersCount);
        } else {
            user.setId(freeIds.poll());
        }
        users.add(user);
        return user;
    }

    public void deleteById(int id) {
        users.stream().filter(user -> user.getId() == id)
                .findFirst()
                .ifPresent(user -> {
                    users.remove(user);
            freeIds.add(user.getId());
                });
    }

}
