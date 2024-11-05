package com.zoohandlung.m295_projektarbeit.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zoohandlung.m295_projektarbeit.model.Animal;
import com.zoohandlung.m295_projektarbeit.repository.AnimalRepository;

/**
 * REST-Controller für den Zugriff auf Tierdaten.
 * Bietet Endpunkte für CRUD-Operationen an, um Tiere zu erstellen, abzurufen, zu aktualisieren und zu löschen.
 */
@RestController
@RequestMapping("/api/animals")
public class AnimalController {


        /**
     * Gibt eine Liste aller verfügbaren Tiere zurück.
     *
     * @return Liste aller Tiere im Repository
     */
    @Autowired
    private AnimalRepository animalRepository;

    // Alle Tiere abrufen
    @GetMapping
    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

   /**
     * Ruft ein einzelnes Tier anhand der ID ab.
     *
     * @param id Die ID des gewünschten Tieres
     * @return ResponseEntity mit dem gefundenen Tier oder einem Not-Found-Status, falls das Tier nicht existiert
     */
    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable Long id) {
        Optional<Animal> animal = animalRepository.findById(id);
        return animal.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

      /**
     * Erstellt ein neues Tier im Repository.
     *
     * @param animal Das zu speichernde Tierobjekt
     * @return ResponseEntity mit dem erstellten Tier und einem Created-Status
     */
    @PostMapping
    public ResponseEntity<Animal> createAnimal(@RequestBody Animal animal) {
        Animal savedAnimal = animalRepository.save(animal);
        return new ResponseEntity<>(savedAnimal, HttpStatus.CREATED);
    }

        /**
     * Aktualisiert ein bestehendes Tier anhand der ID.
     *
     * @param id Die ID des zu aktualisierenden Tieres
     * @param animalDetails Die neuen Details des Tieres
     * @return ResponseEntity mit dem aktualisierten Tier oder einem Not-Found-Status, falls das Tier nicht existiert
     */
    @PutMapping("/{id}")
    public ResponseEntity<Animal> updateAnimal(@PathVariable Long id, @RequestBody Animal animalDetails) {
        Optional<Animal> animalOptional = animalRepository.findById(id);
        if (animalOptional.isPresent()) {
            Animal animalToUpdate = animalOptional.get();
            animalToUpdate.setName(animalDetails.getName());
            animalToUpdate.setSpecies(animalDetails.getSpecies());
            animalToUpdate.setPrice(animalDetails.getPrice());
            animalToUpdate.setAvailable(animalDetails.isAvailable());
            animalToUpdate.setDescription(animalDetails.getDescription());
            Animal updatedAnimal = animalRepository.save(animalToUpdate);
            return ResponseEntity.ok(updatedAnimal);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

      /**
     * Löscht ein Tier anhand der ID.
     *
     * @param id Die ID des zu löschenden Tieres
     * @return ResponseEntity mit No-Content-Status, falls erfolgreich gelöscht, 
     *         oder Not-Found-Status, falls das Tier nicht existiert
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable Long id) {
        if (animalRepository.existsById(id)) {
            animalRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}