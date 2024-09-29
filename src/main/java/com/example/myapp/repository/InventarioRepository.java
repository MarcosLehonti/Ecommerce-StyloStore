package com.example.myapp.repository;

import com.example.myapp.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    // Opcionalmente, agregar métodos de consulta personalizados

    // Buscar inventario por producto
    List<Inventario> findByProductoId(Long productoId);

    // Buscar inventario por color
    List<Inventario> findByColorId(Long colorId);

    // Buscar inventario por producto y color
    Optional<Inventario> findByProductoIdAndColorId(Long productoId, Long colorId);
}
