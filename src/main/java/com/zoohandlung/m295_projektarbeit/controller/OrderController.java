package com.zoohandlung.m295_projektarbeit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zoohandlung.m295_projektarbeit.model.Order;
import com.zoohandlung.m295_projektarbeit.model.OrderItem;
import com.zoohandlung.m295_projektarbeit.repository.OrderItemRepository;
import com.zoohandlung.m295_projektarbeit.repository.OrderRepository;

/**
 * REST-Controller für den Zugriff auf Bestellungsdaten.
 * Bietet Endpunkte für das Erstellen, Anzeigen und Verwalten von Bestellungen und deren Artikel.
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;
    
    /**
     * Erstellt eine neue Bestellung und speichert sie im Repository.
     *
     * @param order Die zu erstellende Bestellung
     * @return ResponseEntity mit der erstellten Bestellung
     */
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        // Bestelle die Bestellung und speichere sie
        Order newOrder = orderRepository.save(order);
        return ResponseEntity.ok(newOrder);
    }

    
    /**
     * Gibt alle Bestellungen eines bestimmten Benutzers zurück.
     *
     * @param userId Die ID des Benutzers, dessen Bestellungen abgerufen werden sollen
     * @return ResponseEntity mit der Liste der Bestellungen für den angegebenen Benutzer
     */
    @GetMapping("/{userId}")
    public ResponseEntity<List<Order>> getUserOrders(@PathVariable Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return ResponseEntity.ok(orders);
    }

        /**
     * Fügt ein OrderItem zu einer bestehenden Bestellung hinzu.
     *
     * @param orderId Die ID der Bestellung, zu der das OrderItem hinzugefügt wird
     * @param orderItem Das OrderItem-Objekt, das hinzugefügt werden soll
     * @return ResponseEntity mit dem hinzugefügten OrderItem
     */
    @PostMapping("/{orderId}/items")
    public ResponseEntity<OrderItem> addOrderItem(@PathVariable Long orderId, @RequestBody OrderItem orderItem) {
        orderItem.setOrderId(orderId); // Setzt die orderId für das OrderItem
        OrderItem newOrderItem = orderItemRepository.save(orderItem);
        return ResponseEntity.ok(newOrderItem);
    }

     /**
     * Ruft die Liste der OrderItems für eine bestimmte Bestellung ab.
     *
     * @param orderId Die ID der Bestellung, deren OrderItems abgerufen werden sollen
     * @return ResponseEntity mit der Liste der OrderItems für die angegebene Bestellung
     */
    @GetMapping("/{orderId}/items")
    public ResponseEntity<List<OrderItem>> getOrderItems(@PathVariable Long orderId) {
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId);
        return ResponseEntity.ok(orderItems);
    }
}