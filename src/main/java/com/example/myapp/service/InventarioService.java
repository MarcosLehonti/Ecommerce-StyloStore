package com.example.myapp.service;

import com.example.myapp.dto.InventarioDTO;
import com.example.myapp.model.Inventario;
import com.example.myapp.model.Producto;
import com.example.myapp.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    // Guardar inventario
    public Inventario guardarInventario(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    // Buscar inventario por ID
    public Optional<Inventario> obtenerInventarioPorId(Long id) {
        return inventarioRepository.findById(id);
    }

    // Buscar inventario por producto
    public List<Inventario> obtenerInventarioPorProducto(Long productoId) {
        return inventarioRepository.findByProductoId(productoId);
    }

    // Buscar inventario por color
    public List<Inventario> obtenerInventarioPorColor(Long colorId) {
        return inventarioRepository.findByColorId(colorId);
    }

    // Buscar inventario por producto y color
    public Optional<Inventario> obtenerInventarioPorProductoYColor(Long productoId, Long colorId) {
        return inventarioRepository.findByProductoIdAndColorId(productoId, colorId);
    }

    // Actualizar inventario
    public Inventario actualizarInventario(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    // Eliminar inventario
    public void eliminarInventario(Long id) {
        inventarioRepository.deleteById(id);
    }

    // Listar todos los inventarios
    public List<Inventario> listarInventarios() {
        return inventarioRepository.findAll();
    }


    // Método para obtener el inventario completo con detalles de productos y colores
    public List<InventarioDTO> obtenerInventarioCompleto() {
        List<Inventario> inventarios = inventarioRepository.findAll();

        return inventarios.stream().map(inventario -> {
            InventarioDTO dto = new InventarioDTO();
            dto.setId(inventario.getId());
            dto.setCantidad(inventario.getCantidad());

            // Asignar detalles del producto
            Producto producto = inventario.getProducto();
            dto.setNombreProducto(producto.getNombre());
            dto.setDescripcionProducto(producto.getDescripcion());
            dto.setPrecioProducto(producto.getPrecio());
            dto.setImagenUrlProducto(producto.getImagenUrl());

            // Asignar detalles del color
            dto.setNombreColor(inventario.getColor().getNombre());
            dto.setCodigoHexColor(inventario.getColor().getCodigoHex());

            return dto;
        }).collect(Collectors.toList());
    }
}
