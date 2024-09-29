package com.example.myapp.controller;

import com.example.myapp.dto.InventarioDTO;
import com.example.myapp.model.Inventario;
import com.example.myapp.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    // Crear un nuevo inventario
    @PostMapping("/crear")
    public ResponseEntity<Inventario> crearInventario(@RequestBody Inventario inventario) {
        Inventario nuevoInventario = inventarioService.guardarInventario(inventario);
        return ResponseEntity.ok(nuevoInventario);
    }

    // Obtener inventario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Inventario> obtenerInventarioPorId(@PathVariable Long id) {
        Optional<Inventario> inventario = inventarioService.obtenerInventarioPorId(id);
        return inventario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Listar todos los inventarios
    @GetMapping("/listar")
    public ResponseEntity<List<Inventario>> listarInventarios() {
        List<Inventario> inventarios = inventarioService.listarInventarios();
        return ResponseEntity.ok(inventarios);
    }

    // Obtener inventario por producto
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<Inventario>> obtenerInventarioPorProducto(@PathVariable Long productoId) {
        List<Inventario> inventarios = inventarioService.obtenerInventarioPorProducto(productoId);
        return ResponseEntity.ok(inventarios);
    }

    // Obtener inventario por color
    @GetMapping("/color/{colorId}")
    public ResponseEntity<List<Inventario>> obtenerInventarioPorColor(@PathVariable Long colorId) {
        List<Inventario> inventarios = inventarioService.obtenerInventarioPorColor(colorId);
        return ResponseEntity.ok(inventarios);
    }

    // Obtener inventario por producto y color
    @GetMapping("/producto/{productoId}/color/{colorId}")
    public ResponseEntity<Inventario> obtenerInventarioPorProductoYColor(@PathVariable Long productoId, @PathVariable Long colorId) {
        Optional<Inventario> inventario = inventarioService.obtenerInventarioPorProductoYColor(productoId, colorId);
        return inventario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar inventario
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Inventario> actualizarInventario(@PathVariable Long id, @RequestBody Inventario inventarioDetalles) {
        Optional<Inventario> inventario = inventarioService.obtenerInventarioPorId(id);
        if (inventario.isPresent()) {
            Inventario inventarioActualizado = inventario.get();
            inventarioActualizado.setCantidad(inventarioDetalles.getCantidad());
            inventarioService.actualizarInventario(inventarioActualizado);
            return ResponseEntity.ok(inventarioActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar inventario
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarInventario(@PathVariable Long id) {
        inventarioService.eliminarInventario(id);
        return ResponseEntity.ok().build();
    }


    // Obtener el inventario completo con información detallada de productos y colores
    @GetMapping("/detalles")
    public ResponseEntity<List<InventarioDTO>> obtenerInventarioCompleto() {
        List<InventarioDTO> inventarioDetalles = inventarioService.obtenerInventarioCompleto();
        return ResponseEntity.ok(inventarioDetalles);
    }

}
