package com.zoohandlung.m295_projektarbeit.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Repräsentiert einen Benutzer im System.
 */
@Entity
@Table(name = "users")
public class User {
    
    /**
     * Eindeutige ID des Benutzers.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    /**
     * Benutzername des Benutzers. Muss einzigartig sein.
     */
    @NotBlank(message = "Username is required")
    private String username;

    /**
     * Passwort des Benutzers. Muss mindestens 8 Zeichen lang sein und eine Zahl enthalten.
     */
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = ".*\\d.*", message = "Password must contain at least one number")
    private String password;

    /**
     * Rolle des Benutzers. Kann "USER" oder "ADMIN" sein.
     */
    @NotBlank(message = "Role is required")
    private String role;

    /**
     * Standard-Konstruktor.
     */
    public User() {}

       /**
     * Konstruktor zur Initialisierung eines Benutzers.
     *
     * @param username der Benutzername
     * @param password das Passwort
     * @param role     die Rolle des Benutzers
     */
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
