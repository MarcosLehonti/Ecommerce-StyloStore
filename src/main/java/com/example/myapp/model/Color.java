package com.example.myapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "colores")
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre; // Nombre del color, por ejemplo, "Rojo", "Azul"
    private String codigoHex; // Código hexadecimal del color, por ejemplo, "#FF0000"

    // Constructor vacío
    public Color() {}

    // Constructor con parámetros
    public Color(String nombre, String codigoHex) {
        this.nombre = nombre;
        this.codigoHex = codigoHex;
    }

    // Getters y Setters
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

    public String getCodigoHex() {
        return codigoHex;
    }

    public void setCodigoHex(String codigoHex) {
        this.codigoHex = codigoHex;
    }
}

