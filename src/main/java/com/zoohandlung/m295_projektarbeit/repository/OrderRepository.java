package com.zoohandlung.m295_projektarbeit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zoohandlung.m295_projektarbeit.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
}