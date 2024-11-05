package com.zoohandlung.m295_projektarbeit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zoohandlung.m295_projektarbeit.model.OrderItem;

/**
 * Repository für den Zugriff auf {@link OrderItem}-Entitäten.
 * <p>
 * Diese Schnittstelle erweitert {@link JpaRepository} und bietet 
 * grundlegende CRUD-Operationen sowie eine Methode zum Abrufen von 
 * Bestellpositionen anhand der Bestell-ID.
 * </p>
 * 
 * @see OrderItem
 * @see JpaRepository
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
      /**
     * Sucht eine Liste von {@link OrderItem} anhand der angegebenen Bestell-ID.
     * 
     * @param orderId die ID der Bestellung
     * @return eine Liste von Bestellpositionen, die zur angegebenen Bestellung gehören
     */
    List<OrderItem> findByOrderId(Long orderId); // Neue Methode für das Abrufen von OrderItems nach orderId
}