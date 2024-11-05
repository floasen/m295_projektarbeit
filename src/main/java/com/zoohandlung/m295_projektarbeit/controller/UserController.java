package com.zoohandlung.m295_projektarbeit.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zoohandlung.m295_projektarbeit.model.User;
import com.zoohandlung.m295_projektarbeit.repository.UserRepository;

/**
 * REST-Controller für die Benutzerverwaltung.
 * Bietet Endpunkte zur Registrierung von Benutzern.
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

     /**
     * Registriert einen neuen Benutzer, falls der Benutzername noch nicht vergeben ist.
     * Setzt eine Standardrolle "USER" für den Benutzer, wenn keine Rolle angegeben ist.
     *
     * @param user Das User-Objekt mit den Benutzerdaten zur Registrierung
     * @return ResponseEntity mit einer Bestätigungsmeldung und dem Status 201 (Created),
     *         oder einem Fehlerstatus 400 (Bad Request), falls der Benutzername bereits vergeben ist
     */
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            return new ResponseEntity<>("Username is already taken", HttpStatus.BAD_REQUEST);
        }
    
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER");
        }

        userRepository.save(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }

       /**
     * Meldet einen Benutzer an, wenn der Benutzername und das Passwort korrekt sind.
     *
     * @param user Das User-Objekt mit den Anmeldedaten (Benutzername und Passwort)
     * @return ResponseEntity mit einer Bestätigungsmeldung und dem Status 200 (OK),
     *         oder einem Fehlerstatus 400 (Bad Request) bei falschem Passwort,
     *         oder 404 (Not Found), wenn der Benutzer nicht existiert
     */
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            User foundUser = existingUser.get();
            if (foundUser.getPassword().equals(user.getPassword())) {
                return new ResponseEntity<>("Login successful", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Invalid password", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }
}
