package com.fiipractic.service.interfaces;

import com.fiipractic.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void registerUser(User user);
    List<User> searchUsers(String name);

    void unregister(String id);

    User login(String username, String password);
}
