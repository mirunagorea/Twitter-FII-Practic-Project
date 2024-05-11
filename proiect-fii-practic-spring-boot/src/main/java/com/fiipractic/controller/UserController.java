package com.fiipractic.controller;

import com.fiipractic.model.User;
import com.fiipractic.service.JwtTokenService;
import com.fiipractic.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    private final UserService userService;
    private final JwtTokenService jwtTokenService;

    @Autowired
    public UserController(UserService userService, JwtTokenService jwtTokenService) {
        this.userService = userService;
        this.jwtTokenService = jwtTokenService;
    }

    @GetMapping(value="/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping(value="/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login(@RequestBody User user){
        User user1 = userService.login(user.getUsername(), user.getPassword());
        String token = jwtTokenService.generateToken(user.getUsername());
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value="/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registerUser(@RequestBody User user){
        userService.registerUser(user);
    }

    @GetMapping(value="/users/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> searchUsers(@RequestParam("firstName/lastName/username") String name){
        return userService.searchUsers(name);
    }

    @DeleteMapping(value="/users/{id}")
    public void unregisterUser(@PathVariable String id){
        userService.unregister(id);
    }


}
