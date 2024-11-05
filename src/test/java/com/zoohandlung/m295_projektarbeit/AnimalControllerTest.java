package com.zoohandlung.m295_projektarbeit;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.zoohandlung.m295_projektarbeit.controller.AnimalController;
import com.zoohandlung.m295_projektarbeit.model.Animal;
import com.zoohandlung.m295_projektarbeit.repository.AnimalRepository;

public class AnimalControllerTest {

    @Mock
    private AnimalRepository animalRepository;

    @InjectMocks
    private AnimalController animalController;

    private Animal animal;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        animal = new Animal("Hund", "Hund", 200.0, true, "Ein treuer Begleiter");
    }

    @Test
    public void testGetAllAnimals() {
        when(animalRepository.findAll()).thenReturn(Collections.singletonList(animal));
         List<Animal> response = animalController.getAllAnimals();
        
        assertEquals(1, response.size());
        assertEquals(animal, response.get(0));
        // hier die API-Methode aufrufen und überprüfen, dass die Liste der Tiere zurückgegeben wird
    }

    @Test
    public void testGetAnimalByIdSuccess() {
        when(animalRepository.findById(1L)).thenReturn(Optional.of(animal));
        ResponseEntity<Animal> response = animalController.getAnimalById(1L);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(animal, response.getBody());
        // hier die API-Methode aufrufen und überprüfen, dass das Tier zurückgegeben wird
    }

    @Test
    public void testGetAnimalByIdNotFound() {
        when(animalRepository.findById(999L)).thenReturn(Optional.empty());
        ResponseEntity<Animal> response = animalController.getAnimalById(999L);
        
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        // hier die API-Methode aufrufen und überprüfen, dass ein Fehler zurückgegeben wird
    }
}