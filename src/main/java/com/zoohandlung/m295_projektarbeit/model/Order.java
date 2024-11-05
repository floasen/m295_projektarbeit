package com.zoohandlung.m295_projektarbeit.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;



/**
 * Repräsentiert eine Bestellung im System.
 */
@Entity
@Table(name = "orders")
public class Order {

  /**
     * Eindeutige ID der Bestellung.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    /**
     * ID des Benutzers, der die Bestellung aufgegeben hat.
     */
    private Long userId;

     /**
     * Datum der Bestellung.
     */
    @Temporal(TemporalType.DATE)
    private Date orderDate;

      /**
     * Gesamtbetrag der Bestellung.
     */
    private double totalAmount;

    public Order() {
    }

    /**
     * Konstruktor zur Initialisierung einer Bestellung mit Benutzer-ID, Bestelldatum und Gesamtbetrag.
     *
     * @param userId      Die ID des Benutzers, der die Bestellung aufgegeben hat
     * @param orderDate   Das Datum der Bestellung
     * @param totalAmount Der Gesamtbetrag der Bestellung
     */
    public Order(Long userId, Date orderDate, double totalAmount) {
        this.userId = userId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }

    // Getter und Setter
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}