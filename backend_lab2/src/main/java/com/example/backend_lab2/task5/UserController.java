package com.example.backend_lab2.task5;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Random;

@RestController
public class UserController {

    private Map<Integer, UserEntity> users;

    @GetMapping("/users")
    public String getUsers() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(users);
    }

    @GetMapping("/users/{id}/get")
    public String getUser(@PathVariable Integer id) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(users.get(id));
    }

    @GetMapping("/users/{id}/remove")
    public String removeUser(@PathVariable Integer id) {
        return users.containsKey(id) ? "User with name " + users.remove(id).getName() + " was removed successfully!" : "User with provided ID does not exists.";
    }


    @GetMapping("/user/add")
    public String addUser(@RequestParam String name, @RequestParam int age) throws JsonProcessingException {
        int id;
        do {
            Random rn = new Random();
            id = rn.nextInt(10000) + 1;
        } while (users.containsKey(id));

        UserEntity newUser = new UserEntity(name, age);
        users.put(id, newUser);

        return new ObjectMapper().writeValueAsString(users.get(id));
    }
}
