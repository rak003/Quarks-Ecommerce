package com.quarks.ecommerce.Ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.quarks.ecommerce.Ecommerce.entities.UserTable;
import com.quarks.ecommerce.Ecommerce.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/userData")
@Tag(name = "User", description = "API for managing users.")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Create a User",
               description = "Add a new User to the system.")
    @PostMapping("/")
    public UserTable create(@RequestBody UserTable user) {
        if (user == null || user.getUsername() == null ||
                user.getUsername().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is required.");
        }
        return userService.addUser(user);
    }
    
    @Operation(summary = "Health Check",
               description = "A simple endpoint to check service health.")
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("hello", HttpStatus.OK);
    }
}
