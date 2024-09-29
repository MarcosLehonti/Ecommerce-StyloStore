//package com.example.myapp.repository;
//import com.example.myapp.model.Producto;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//
//public interface ProductoRepository extends JpaRepository<Producto, Long> {
//    List<Producto> findByUsuarioId(Long usuarioId);  // Para listar los productos de un usuario
//}


package com.example.myapp.repository;

import com.example.myapp.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // Método para filtrar productos por el nombre de la categoría
    @Query("SELECT p FROM Producto p WHERE p.categoria.nombre = :nombreCategoria")
    List<Producto> findByCategoriaNombre(@Param("nombreCategoria") String nombreCategoria);
}
