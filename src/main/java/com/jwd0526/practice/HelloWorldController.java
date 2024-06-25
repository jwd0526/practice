package com.jwd0526.practice;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
public class HelloWorldController {

    private static final Logger logger = Logger.getLogger(HelloWorldController.class.getName());

    @Autowired
    private Gson gson;

    @GetMapping(path = "/hello")
    public String helloWorld() {
        return "Hello, World!";
    }

    @GetMapping(path = "/users")
    public ResponseEntity<String> userJson() {
        User[] users = getUsers();
        if (users == null) {
            return new ResponseEntity<>("[]", HttpStatus.OK);
        }
        String json = gson.toJson(users);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

        return new ResponseEntity<>(json, headers, HttpStatus.OK);
    }

    private User[] getUsers() {
        try (Reader reader = new FileReader("src/main/resources/Users.json")) {
            User[] users = gson.fromJson(reader, User[].class);
            return users;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to read the Users.json file: {0}", e.getMessage());
        }
        return null;
    }
}
