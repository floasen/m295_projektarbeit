package com.zoohandlung.m295_projektarbeit.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zoohandlung.m295_projektarbeit.model.Animal;
import com.zoohandlung.m295_projektarbeit.model.Cart;
import com.zoohandlung.m295_projektarbeit.model.CartItem;
import com.zoohandlung.m295_projektarbeit.repository.AnimalRepository;
import com.zoohandlung.m295_projektarbeit.repository.CartItemRepository;
import com.zoohandlung.m295_projektarbeit.repository.CartRepository;
/**
 * REST-Controller für den Zugriff auf Einkaufswagen-Daten.
 * Bietet Endpunkte zum Anzeigen, Hinzufügen, Aktualisieren und Entfernen von Artikeln im Einkaufswagen.
 */
@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private AnimalRepository animalRepository;

    
    /**
     * Zeigt den Einkaufswagen eines bestimmten Benutzers an.
     *
     * @param userId Die ID des Benutzers, dessen Einkaufswagen angezeigt werden soll
     * @return ResponseEntity mit dem Einkaufswagen oder einem Not-Found-Status, falls kein Einkaufswagen gefunden wurde
     */
    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCartByUserId(@PathVariable Long userId) {
        Optional<Cart> cart = cartRepository.findByUserId(userId);
        return cart.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

     /**
     * Fügt ein Tier zum Einkaufswagen eines Benutzers hinzu.
     * Falls der Einkaufswagen für den Benutzer noch nicht existiert, wird ein neuer Einkaufswagen erstellt.
     *
     * @param userId   Die ID des Benutzers, zu dessen Einkaufswagen das Tier hinzugefügt werden soll
     * @param animalId Die ID des hinzuzufügenden Tieres
     * @param quantity Die Menge des hinzuzufügenden Tieres
     * @return ResponseEntity mit dem hinzugefügten Einkaufswagen-Element und einem Created-Status,
     *         oder Not-Found-Status, falls das Tier nicht existiert
     */
    @PostMapping("/{userId}/items")
    public ResponseEntity<CartItem> addItemToCart(@PathVariable Long userId, @RequestParam Long animalId, @RequestParam int quantity) {
        Optional<Cart> cartOptional = cartRepository.findByUserId(userId);
        if (cartOptional.isEmpty()) {
            Cart newCart = new Cart(userId);
            cartRepository.save(newCart);
            cartOptional = Optional.of(newCart);
        }

        Cart cart = cartOptional.get();
        Optional<Animal> animalOptional = animalRepository.findById(animalId);
        if (animalOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Animal animal = animalOptional.get();
        CartItem cartItem = new CartItem(cart, animal, quantity);
        cartItemRepository.save(cartItem);
        return new ResponseEntity<>(cartItem, HttpStatus.CREATED);
    }

      /**
     * Aktualisiert die Menge eines bestimmten Artikels im Einkaufswagen.
     *
     * @param cartItemId Die ID des zu aktualisierenden Einkaufswagen-Artikels
     * @param quantity   Die neue Menge des Artikels
     * @return ResponseEntity mit dem aktualisierten Einkaufswagen-Element oder einem Not-Found-Status,
     *         falls das Element nicht existiert
     */
    @PutMapping("/items/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable Long cartItemId, @RequestParam int quantity) {
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
        if (cartItemOptional.isPresent()) {
            CartItem cartItem = cartItemOptional.get();
            cartItem.setQuantity(quantity);
            cartItemRepository.save(cartItem);
            return ResponseEntity.ok(cartItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

     /**
     * Entfernt ein bestimmtes Element aus dem Einkaufswagen.
     *
     * @param cartItemId Die ID des zu entfernenden Einkaufswagen-Artikels
     * @return ResponseEntity mit einem No-Content-Status, falls das Element erfolgreich entfernt wurde,
     *         oder Not-Found-Status, falls das Element nicht existiert
     */
    @DeleteMapping("/items/{cartItemId}")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable Long cartItemId) {
        if (cartItemRepository.existsById(cartItemId)) {
            cartItemRepository.deleteById(cartItemId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}