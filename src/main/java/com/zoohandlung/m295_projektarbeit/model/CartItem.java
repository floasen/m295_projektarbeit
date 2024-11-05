package com.zoohandlung.m295_projektarbeit.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Entität, die ein Element in einem Einkaufswagen repräsentiert.
 * Ein CartItem besteht aus einem Tier (Animal), das dem Einkaufswagen hinzugefügt wurde, 
 * und der Menge dieses Tieres.
 */
@Entity
public class CartItem {

     /**
     * Eindeutige ID des CartItem, automatisch generiert.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    /**
     * Der Einkaufswagen, zu dem dieses CartItem gehört.
     * Repräsentiert eine viele-zu-eins-Beziehung zur Cart-Entität.
     */
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    /**
     * Das Tier (Animal), das diesem CartItem zugeordnet ist.
     * Repräsentiert eine viele-zu-eins-Beziehung zur Animal-Entität.
     */
    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    
    /**
     * Die Menge dieses spezifischen Tieres im Einkaufswagen.
     */
    private int quantity;

        /**
     * Standard-Konstruktor für die CartItem-Klasse.
     */
    public CartItem() {
    }

        /**
     * Konstruktor zur Initialisierung eines CartItem mit Einkaufswagen, Tier und Menge.
     *
     * @param cart     Der Einkaufswagen, dem dieses Item hinzugefügt wird
     * @param animal   Das Tier, das im Einkaufswagen hinzugefügt wird
     * @param quantity Die Menge des Tieres im Einkaufswagen
     */
    public CartItem(Cart cart, Animal animal, int quantity) {
        this.cart = cart;
        this.animal = animal;
        this.quantity = quantity;
    }

    // Getter und Setter
    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}