package com.zoohandlung.m295_projektarbeit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zoohandlung.m295_projektarbeit.model.CartItem;

/**
 * Repository für den Zugriff auf {@link CartItem}-Entitäten.
 * <p>
 * Diese Schnittstelle erweitert {@link JpaRepository} und bietet 
 * grundlegende CRUD-Operationen für Warenkorbartikel im Zoo-Verwaltungssystem.
 * </p>
 * 
 * @see CartItem
 * @see JpaRepository
 */
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}