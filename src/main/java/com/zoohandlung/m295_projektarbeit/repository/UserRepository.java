package com.zoohandlung.m295_projektarbeit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zoohandlung.m295_projektarbeit.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
