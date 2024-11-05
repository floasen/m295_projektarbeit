package com.zoohandlung.m295_projektarbeit.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * Entität, die einen Einkaufswagen (Cart) im System repräsentiert.
 * Enthält Informationen über den Benutzer, der den Einkaufswagen besitzt,
 * das Erstellungsdatum und die Liste der Artikel im Einkaufswagen.
 */
@Entity
public class Cart {

    
    /**
     * Eindeutige ID des Einkaufswagens, automatisch generiert.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

      /**
     * Die ID des Benutzers, dem der Einkaufswagen gehört.
     */
    private Long userId;

      /**
     * Das Datum und die Uhrzeit, wann der Einkaufswagen erstellt wurde.
     * Wird automatisch auf das aktuelle Datum gesetzt.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    
    /**
     * Liste der Artikel im Einkaufswagen.
     * Verknüpft mit der CartItem-Entität und weist eine eins-zu-viele-Beziehung auf.
     * Die Artikel werden im Zusammenhang mit dem Einkaufswagen geladen.
     */
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartItem> items;

        /**
     * Standard-Konstruktor für die Cart-Klasse.
     */
    public Cart() {
    }

     /**
     * Konstruktor zur Initialisierung des Einkaufswagens für einen bestimmten Benutzer.
     *
     * @param userId Die ID des Benutzers, dem der Einkaufswagen gehört.
     */
    public Cart(Long userId) {
        this.userId = userId;
    }

    // Getter und Setter
    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }
}