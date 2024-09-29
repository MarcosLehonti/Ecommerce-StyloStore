package com.example.myapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "carrito_de_compras")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoDeCompras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private User usuario;  // Relación con el usuario

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCarrito> items;

    private Double total;

    // Método para actualizar el total
    public void actualizarTotal() {
        this.total = items.stream().mapToDouble(item -> item.getCantidad() * item.getProducto().getPrecio()).sum();
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public List<ItemCarrito> getItems() {
        return items;
    }

    public Long getId() {
        return id;
    }

    public Double getTotal() {
        return total;
    }

    public void setItems(List<ItemCarrito> items) {
        this.items = items;
        actualizarTotal(); // Actualizar el total al modificar los items
    }

    // Método para agregar un item al carrito
    public void agregarItem(ItemCarrito item) {
        this.items.add(item);
        item.setCarrito(this); // Establecer la referencia del carrito en el item
        actualizarTotal(); // Actualizar el total después de agregar un item
    }

    // Método para eliminar un item del carrito
    public void eliminarItem(ItemCarrito item) {
        this.items.remove(item);
        item.setCarrito(null); // Eliminar la referencia del carrito en el item
        actualizarTotal(); // Actualizar el total después de eliminar un item
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
