package com.zoohandlung.m295_projektarbeit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zoohandlung.m295_projektarbeit.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrderId(Long orderId); // Neue Methode für das Abrufen von OrderItems nach orderId
}