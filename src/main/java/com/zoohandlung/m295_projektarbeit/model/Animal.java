package com.zoohandlung.m295_projektarbeit.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Entität für ein Tier im System.
 * Enthält Informationen wie Name, Spezies, Preis, Verfügbarkeit und Beschreibung.
 */
@Entity
public class Animal {

        /**
     * Eindeutige ID des Tieres, automatisch generiert.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long animalId;

      /**
     * Der Name des Tieres.
     * Muss angegeben werden und darf nicht null sein.
     */
    @Column(nullable = false)
    private String name;

       /**
     * Die Spezies des Tieres (z. B. "Hund", "Katze").
     * Muss angegeben werden und darf nicht null sein.
     */
    @Column(nullable = false)
    private String species;

       /**
     * Der Preis des Tieres.
     * Muss angegeben werden und darf nicht null sein.
     */
    @Column(nullable = false)
    private double price;

    
    /**
     * Gibt an, ob das Tier verfügbar ist.
     * Muss angegeben werden und darf nicht null sein.
     */
    @Column(nullable = false)
    private boolean available;

        /**
     * Eine optionale Beschreibung des Tieres.
     * Kann bis zu 500 Zeichen lang sein.
     */
    @Column(length = 500)
    private String description;

        /**
     * Standard-Konstruktor.
     */
    public Animal() {
    }

        /**
     * Konstruktor zur Initialisierung aller Felder der Animal-Entität.
     *
     * @param name        Der Name des Tieres
     * @param species     Die Spezies des Tieres
     * @param price       Der Preis des Tieres
     * @param available   Verfügbarkeit des Tieres
     * @param description Eine optionale Beschreibung des Tieres
     */
    public Animal(String name, String species, double price, boolean available, String description) {
        this.name = name;
        this.species = species;
        this.price = price;
        this.available = available;
        this.description = description;
    }

    // Getter und Setter
    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}