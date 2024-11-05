package com.zoohandlung.m295_projektarbeit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zoohandlung.m295_projektarbeit.model.User;

/**
 * Repository für den Zugriff auf {@link User}-Entitäten.
 * <p>
 * Diese Schnittstelle erweitert {@link JpaRepository} und bietet 
 * grundlegende CRUD-Operationen sowie eine Methode zum Abrufen 
 * eines Benutzers anhand des Benutzernamens.
 * </p>
 * 
 * @see User
 * @see JpaRepository
 */
public interface UserRepository extends JpaRepository<User, Long> {
      /**
     * Sucht einen {@link User} anhand des angegebenen Benutzernamens.
     * 
     * @param username der Benutzername
     * @return ein Optional mit dem gefundenen Benutzer, falls vorhanden
     */
    Optional<User> findByUsername(String username);
}
