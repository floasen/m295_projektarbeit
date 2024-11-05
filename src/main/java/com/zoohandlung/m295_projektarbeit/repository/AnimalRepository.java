package com.zoohandlung.m295_projektarbeit.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zoohandlung.m295_projektarbeit.model.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
   
}