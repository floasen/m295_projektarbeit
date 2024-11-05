package com.zoohandlung.m295_projektarbeit;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import org.springframework.http.ResponseEntity;

import com.zoohandlung.m295_projektarbeit.controller.UserController;
import com.zoohandlung.m295_projektarbeit.model.User;
import com.zoohandlung.m295_projektarbeit.repository.UserRepository;


public class UserControllerTest {
    
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    private User user;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        user = new User("benutzer1", "Passwort123", "USER");
    }

  
    @Test
    public void testRegisterUserSuccess() {
        when(userRepository.save(any(User.class))).thenReturn(user);
        ResponseEntity<String> response = userController.registerUser(user);
        assertEquals(CREATED, response.getStatusCode());
        assertEquals("User registered successfully", response.getBody());
        // Testet die Registrierung eines neuen Benutzers, wenn der Benutzername verfügbar ist.
    }

    @Test
    public void testRegisterUserAlreadyExists() {
        when(userRepository.findByUsername("benutzer1")).thenReturn(Optional.of(user));
        ResponseEntity<String> response = userController.registerUser(user);
        assertEquals(CONFLICT, response.getStatusCode());
        assertEquals("Username is already taken", response.getBody());
        // Testet die Registrierung eines Benutzers, wenn der Benutzername bereits vergeben ist.
    }


     @Test
    public void testLoginSuccess() {
        User wrongPasswordUser = new User("benutzer1", "falschesPasswort", "USER");
        when(userRepository.findByUsername("benutzer1")).thenReturn(Optional.of(user));
        ResponseEntity<String> response = userController.loginUser(wrongPasswordUser);
        assertEquals(CONFLICT, response.getStatusCode());
        assertEquals("Invalid password", response.getBody());
         // Testet die Anmeldung eines Benutzers mit dem falschen Passwort.
    }

    @Test
    public void testLoginWrongPassword() {
        when(userRepository.findByUsername("benutzer2")).thenReturn(Optional.of(user));
        ResponseEntity<String> response = userController.loginUser(user);
        assertEquals(NOT_FOUND, response.getStatusCode());
        assertEquals("User not found", response.getBody());
        // Testet die Anmeldung eines Benutzers, der nicht in der Datenbank vorhanden ist.
    }
}
