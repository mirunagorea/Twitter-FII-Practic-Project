package com.fiipractic.service.implementations;

import com.fiipractic.exception.EmailAlreadyExistsException;
import com.fiipractic.exception.RegistrationFieldsNotValidException;
import com.fiipractic.exception.UserNotFoundException;
import com.fiipractic.exception.UsernameAlreadyExistsException;
import com.fiipractic.model.User;
import com.fiipractic.repository.UserDAO;
import com.fiipractic.service.interfaces.UserService;
import jakarta.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.validation.Validator;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userRepository;

    private final BCryptPasswordEncoder passwordEncoder;
    private final Validator validator;

    @Autowired
    public UserServiceImpl(UserDAO userRepository, BCryptPasswordEncoder passwordEncoder, Validator validator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.validator = validator;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public void registerUser(User user) {
        validateUser(user);
        List<User> usersWithSameUsername = userRepository.searchUsersByUsername(user.getUsername());
        if (!usersWithSameUsername.isEmpty()) {
            throw new UsernameAlreadyExistsException("Username is already taken.");
        }

        List<User> usersWithSameEmail = userRepository.searchUsersByEmail(user.getEmail());
        if (!usersWithSameEmail.isEmpty()) {
            throw new EmailAlreadyExistsException("Email address is already used.");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        userRepository.createUser(user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail(), encodedPassword);

        System.out.println("User registration attempt: " + user.getUsername());
    }

    public void validateUser(User user) {
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder();
            for (ConstraintViolation<User> violation : violations) {
                errorMessage.append(violation.getMessage()).append("\n");
            }
            throw new RegistrationFieldsNotValidException(errorMessage.toString());
        }
    }

    @Override
    public List<User> searchUsers(String name) {
        List<User> users = userRepository.searchUsers(name);
        if (users.isEmpty()) {
            throw new UserNotFoundException("No user found with this name");
        }
        return users;
    }

    @Override
    public void unregister(String id) {
        userRepository.deleteUser(id);
    }

    @Override
    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if(user == null || !passwordEncoder.matches(password, user.getPassword())){
            throw new UserNotFoundException("Incorrect username or password");
        }
        return user;
    }
}
