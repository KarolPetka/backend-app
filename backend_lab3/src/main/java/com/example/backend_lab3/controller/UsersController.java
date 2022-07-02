package com.example.backend_lab3.controller;

import com.example.backend_lab3.dto.UserRequest;
import com.example.backend_lab3.dto.UserResponse;
import com.example.backend_lab3.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @GetMapping(value = "/users", produces = "application/json")
    public ResponseEntity<List<UserResponse>> getUsers() {
        return usersService.getUsers();
    }

    @GetMapping(value = "/users/{id}", produces = "application/json")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String id) {
        return usersService.getUserById(id);
    }

    @PostMapping(value = "user/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserResponse> postUser(@RequestBody UserRequest user){
        return usersService.postUser(user);
    }

    @PutMapping(value = "users/{id}/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserResponse> updateUser(@PathVariable String id, @RequestBody UserRequest user) {
        return usersService.updateUser(id, user);
    }

    @DeleteMapping(value = "users/{id}/remove", produces = "application/json")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable String id) {
        return usersService.deleteUser(id);
    }
}
