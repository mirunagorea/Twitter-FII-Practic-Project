package com.fiipractic.service.implementations;
import com.fiipractic.exception.EmailAlreadyExistsException;
import com.fiipractic.exception.RegistrationFieldsNotValidException;
import com.fiipractic.exception.UsernameAlreadyExistsException;
import com.fiipractic.exception.UserNotFoundException;
import com.fiipractic.model.User;
import com.fiipractic.repository.UserDAO;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {
    @Mock
    private UserDAO userRepository;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private Validator validator;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRegisterUser_Success() {
        User user = new User();
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setUsername("username");
        user.setEmail("username@example.com");
        user.setPassword("password");
        when(userRepository.searchUsersByUsername("username")).thenReturn(new ArrayList<>());
        when(userRepository.searchUsersByEmail("username@example.com")).thenReturn(new ArrayList<>());
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        userService.registerUser(user);
        verify(userRepository, times(1)).createUser("FirstName", "LastName", "username", "username@example.com", "encodedPassword");
    }

    @Test
    void testRegisterUser_UsernameAlreadyExists(){
        User user = new User();
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setUsername("usernameThatAlreadyExists");
        user.setEmail("username@example.com");
        user.setPassword("password");
        List<User> existingUsers = new ArrayList<>();
        existingUsers.add(new User());
        when(userRepository.searchUsersByUsername("usernameThatAlreadyExists")).thenReturn(existingUsers);
        assertThrows(UsernameAlreadyExistsException.class, () -> userService.registerUser(user));
    }

    @Test
    void testRegisterUser_EmailAlreadyExists(){
        User user = new User();
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setUsername("username");
        user.setEmail("existingemail@example.com");
        user.setPassword("password");
        List<User> existingUsers = new ArrayList<>();
        existingUsers.add(new User());
        when(userRepository.searchUsersByEmail("existingemail@example.com")).thenReturn(existingUsers);
        assertThrows(EmailAlreadyExistsException.class, () -> userService.registerUser(user));
    }

    @Test
    void testValidateUser_ValidUser() {
        UserServiceImpl userService = new UserServiceImpl(userRepository, null, validator);

        User user = new User();
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setUsername("username");
        user.setEmail("username@example.com");
        user.setPassword("password");

        assertDoesNotThrow(() -> userService.validateUser(user));
    }

//    @Test
//    void testValidateUser_InvalidUser() {
//        UserServiceImpl userService = new UserServiceImpl(userRepository, null, validator);
//
//        User user = new User();
//
//        assertThrows(RegistrationFieldsNotValidException.class, () -> userService.validateUser(user));
//    }

    @Test
    void testSearchUsers_ExistingUsers() {
        UserServiceImpl userService = new UserServiceImpl(userRepository, null, null);
        String name = "FirstName";
        User user = new User();
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setUsername("username");
        user.setEmail("username@example.com");
        user.setPassword("password");

        List<User> existingUsers = Collections.singletonList(user);
        when(userRepository.searchUsers(name)).thenReturn(existingUsers);

        List<User> result = userService.searchUsers(name);
        assertEquals(existingUsers, result);
    }

    @Test
    void testSearchUsers_NoUsersFound() {
        UserServiceImpl userService = new UserServiceImpl(userRepository, null, null);
        String name = "NonexistentName";
        when(userRepository.searchUsers(name)).thenReturn(Collections.emptyList());

        assertThrows(UserNotFoundException.class, () -> userService.searchUsers(name));
    }
}