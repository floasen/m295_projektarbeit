package com.zoohandlung.m295_projektarbeit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zoohandlung.m295_projektarbeit.model.Cart;

/**
 * Repository für den Zugriff auf {@link Cart}-Entitäten.
 * <p>
 * Diese Schnittstelle erweitert {@link JpaRepository} und bietet 
 * grundlegende CRUD-Operationen sowie eine Methode zur Suche eines 
 * Warenkorbs anhand der Benutzer-ID.
 * </p>
 * 
 * @see Cart
 * @see JpaRepository
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    /**
     * Sucht einen {@link Cart} basierend auf der angegebenen Benutzer-ID.
     * 
     * @param userId die ID des Benutzers
     * @return ein Optional mit dem gefundenen Warenkorb, falls vorhanden
     */
    Optional<Cart> findByUserId(Long userId);
}