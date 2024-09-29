//package com.example.myapp.model;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//@Table(name = "productos")
//public class Producto {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String nombre;
//
//    private String descripcion;
//
//    private Double precio;
//
//    private String imagenUrl;  // URL o ruta de la imagen
//
//    @ManyToOne
//    @JoinColumn(name = "usuario_id")
//    private User usuario;  // Relación con el usuario que compró el producto
//
//    // @JsonBackReference evita que se serialice la categoría al serializar el producto
//    @ManyToOne
//    @JoinColumn(name = "categoria_id")
//    @JsonBackReference
//    private Categoria categoria; // Relación con la categoría
//
//
//
//    // Getters y Setters manuales
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getNombre() {
//        return nombre;
//    }
//
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
//
//    public String getDescripcion() {
//        return descripcion;
//    }
//
//    public void setDescripcion(String descripcion) {
//        this.descripcion = descripcion;
//    }
//
//    public Double getPrecio() {
//        return precio;
//    }
//
//    public void setPrecio(Double precio) {
//        this.precio = precio;
//    }
//
//    public String getImagenUrl() {
//        return imagenUrl;
//    }
//
//    public void setImagenUrl(String imagenUrl) {
//        this.imagenUrl = imagenUrl;
//    }
//
//    public User getUsuario() {
//        return usuario;
//    }
//
//    public void setUsuario(User usuario) {
//        this.usuario = usuario;
//    }
//
//    public Categoria getCategoria() {
//        return categoria;
//    }
//
//    public void setCategoria(Categoria categoria) {
//        this.categoria = categoria;
//    }
//
//}
//



package com.example.myapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    private Double precio;

    private String imagenUrl;  // URL o ruta de la imagen

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;  // Relación con el usuario que compró el producto

    // @JsonBackReference evita que se serialice la categoría al serializar el producto
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    @JsonBackReference
    private Categoria categoria; // Relación con la categoría




    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "producto_colores",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "color_id")
    )
    private Set<Color> colores = new HashSet<>();


    // Getters y Setters manuales
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    // Getters y Setters para los colores
    public Set<Color> getColores() {
        return colores;
    }

    public void setColores(Set<Color> colores) {
        this.colores = colores;
    }
}
