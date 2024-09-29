package com.example.myapp.controller;

import com.example.myapp.model.Categoria;
import com.example.myapp.model.Producto;
import com.example.myapp.repository.CategoriaRepository;
import com.example.myapp.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    // Crear una nueva categoría
    @PostMapping("/crear")
    public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria categoria) {
        Categoria nuevaCategoria = categoriaRepository.save(categoria);
        return ResponseEntity.ok(nuevaCategoria);
    }

    // Obtener todas las categorías
    @GetMapping("/listar")
    public ResponseEntity<List<Categoria>> listarCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return ResponseEntity.ok(categorias);
    }

    // Asignar productos a una categoría
    @PostMapping("/{categoriaId}/asignar-productos")
    public ResponseEntity<?> asignarProductosACategoria(
            @PathVariable Long categoriaId,
            @RequestBody List<Long> productosIds) {

        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        List<Producto> productos = productoRepository.findAllById(productosIds);
        productos.forEach(producto -> producto.setCategoria(categoria));

        productoRepository.saveAll(productos);

        return ResponseEntity.ok("Productos asignados correctamente a la categoría");
    }
}

