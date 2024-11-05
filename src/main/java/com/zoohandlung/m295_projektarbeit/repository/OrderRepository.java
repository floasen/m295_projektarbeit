package com.zoohandlung.m295_projektarbeit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zoohandlung.m295_projektarbeit.model.Order;

/**
 * Repository für den Zugriff auf {@link Order}-Entitäten.
 * <p>
 * Diese Schnittstelle erweitert {@link JpaRepository} und bietet 
 * grundlegende CRUD-Operationen sowie eine Methode zum Abrufen von 
 * Bestellungen anhand der Benutzer-ID.
 * </p>
 * 
 * @see Order
 * @see JpaRepository
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
        /**
     * Sucht eine Liste von {@link Order} anhand der angegebenen Benutzer-ID.
     * 
     * @param userId die ID des Benutzers
     * @return eine Liste von Bestellungen, die dem angegebenen Benutzer gehören
     */
    List<Order> findByUserId(Long userId);
}