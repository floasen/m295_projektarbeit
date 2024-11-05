package com.zoohandlung.m295_projektarbeit.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Repräsentiert einen Artikel in einer Bestellung.
 */
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    private Long orderId;

    private Long animalId;

    private int quantity;

    private double price;
    
    /**
     * Standard-Konstruktor.
     */
    public OrderItem() {
    }

    /**
     * Konstruktor zur Initialisierung eines Bestellartikels.
     *
     * @param orderItemId die ID des Bestellartikels
     * @param orderId     die ID der Bestellung
     * @param animalId    die ID des Tiers
     * @param quantity    die Menge des Artikels
     * @param price       der Preis des Artikels
     */
    public OrderItem(Long orderId, Long animalId, int quantity, double price) {
        this.orderId = orderId;
        this.animalId = animalId;    
        this.quantity = quantity;
        this.price = price;
    }

    // Getter und Setter
    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}