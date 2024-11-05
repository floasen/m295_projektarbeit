package com.zoohandlung.m295_projektarbeit.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zoohandlung.m295_projektarbeit.model.Animal;

/**
 * Repository für den Zugriff auf {@link Animal}-Entitäten.
 * <p>
 * Diese Schnittstelle erweitert {@link JpaRepository} und bietet 
 * grundlegende CRUD-Operationen für Tiere im Zoo-Verwaltungssystem.
 * </p>
 * 
 * @see Animal
 * @see JpaRepository
 */
@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
   
}