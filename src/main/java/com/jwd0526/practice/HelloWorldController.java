package com.jwd0526.practice;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.jwd0526.practice.model.User;
import com.jwd0526.practice.repository.UserRepository;

@RestController
public class HelloWorldController {

    private static final Logger logger = Logger.getLogger(HelloWorldController.class.getName());

    @Autowired
    private Gson gson;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/hello")
    public String helloWorld() {
        return "Hello, World!";
    }

    @GetMapping(path = "/users")
    public ResponseEntity<String> userJson() {
        List<User> users = getUsers();
        String json = gson.toJson(users);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

        return new ResponseEntity<>(json, headers, HttpStatus.OK);
    }

    private List<User> getUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to fetch users from database: {0}", e.getMessage());
            return null;
        }
    }
}
