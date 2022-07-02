package com.example.backend_lab3.service;

import com.example.backend_lab3.dto.UserRequest;
import com.example.backend_lab3.dto.UserResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UsersService {
    List<UserResponse> userList;
    String userFile = "src/main/resources/static/Users.txt";

    @PostConstruct
    private void onCreate() {
        {
            try {
                String fileContent = new String(Files.readAllBytes(Paths.get(userFile)));
                ObjectMapper mapper = new ObjectMapper();
                userList = mapper.readValue(fileContent, new TypeReference<>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @PreDestroy
    private void onDestroy() throws IOException {
        String currentContent = new String(Files.readAllBytes(Paths.get(userFile)));
        PrintWriter writer = new PrintWriter(userFile);
        try {
            writer.print(new Gson().toJson(userList));
            writer.close();
        } catch (Exception e) {
            writer.print(currentContent);
            writer.close();
            e.printStackTrace();
        }
    }

    public ResponseEntity<List<UserResponse>> getUsers() {
        if (!userList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(userList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<UserResponse> getUserById(String id) {
        Long validId = isIdNumber(id) ? Long.parseLong(id) : -1;

        if (validId == -1 || findUser(Long.parseLong(id)) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(userList.stream()
                            .filter(user -> validId.equals(user.getId()))
                            .findAny()
                            .orElse(null));
        }
    }

    public ResponseEntity<UserResponse> postUser(UserRequest user) {
        try {
            Long greatestId = userList.get(userList.size() - 1).getId();

            UserResponse newUser = UserResponse.builder()
                    .id(greatestId + 1)
                    .name(user.getName())
                    .email(user.getEmail())
                    .build();

            userList.add(newUser);

            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(newUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<UserResponse> updateUser(String id, UserRequest userRequest) {
        Long validId = isIdNumber(id) ? Long.parseLong(id) : -1;
        UserResponse userToUpdate = findUser(validId);

        if (validId == -1 || userToUpdate == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            UserResponse updatedUser = UserResponse.builder()
                    .id(validId)
                    .name(userRequest.getName())
                    .email(userRequest.getEmail())
                    .build();

            userList.set(userList.indexOf(userToUpdate), updatedUser);
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(userList.get(userList.indexOf(updatedUser)));
        }
    }

    public ResponseEntity<Map<String, Boolean>> deleteUser(String id) {
        long validId = isIdNumber(id) ? Long.parseLong(id) : -1;
        UserResponse userToDelete = findUser(validId);

        if (validId == -1 || userToDelete == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            userList.remove(userToDelete);
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Collections.singletonMap("result", true));
        }
    }

    private UserResponse findUser(Long id) {
        return userList.stream()
                .filter(user -> id.equals(user.getId()))
                .findAny()
                .orElse(null);
    }

    private boolean isIdNumber(String id) {
        return id.chars().allMatch(Character::isDigit);
    }
}
