package com.example.myapp.service;

import com.example.myapp.model.CarritoDeCompras;
import com.example.myapp.model.ItemCarrito;
import com.example.myapp.model.Producto;
import com.example.myapp.model.User;
import com.example.myapp.repository.CarritoDeComprasRepository;
import com.example.myapp.repository.ItemCarritoRepository;
import com.example.myapp.repository.ProductoRepository;
import com.example.myapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CarritoDeComprasService {

    @Autowired
    private CarritoDeComprasRepository carritoDeComprasRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemCarritoRepository itemCarritoRepository;

    // Método para agregar productos al carrito
    public CarritoDeCompras agregarProducto(Long userId, Long productoId, Integer cantidad) {
        Optional<CarritoDeCompras> carritoOpt = carritoDeComprasRepository.findByUsuarioId(userId);
        CarritoDeCompras carrito = carritoOpt.orElseGet(() -> {
            CarritoDeCompras nuevoCarrito = new CarritoDeCompras();
            User usuario = userRepository.findById(userId).orElseThrow();
            nuevoCarrito.setUsuario(usuario);
            return carritoDeComprasRepository.save(nuevoCarrito);
        });

        Producto producto = productoRepository.findById(productoId).orElseThrow();
        ItemCarrito item = new ItemCarrito();
        item.setProducto(producto);
        item.setCantidad(cantidad);

        carrito.getItems().add(item);
        carrito.actualizarTotal();
        return carritoDeComprasRepository.save(carrito);
    }

    // Método para obtener o crear el carrito del usuario
    public CarritoDeCompras obtenerOCrearCarrito(User user) {
        return carritoDeComprasRepository.findByUsuarioId(user.getId())
                .orElseGet(() -> {
                    CarritoDeCompras nuevoCarrito = new CarritoDeCompras();
                    nuevoCarrito.setUsuario(user);
                    return carritoDeComprasRepository.save(nuevoCarrito);
                });
    }

    // Método para guardar el carrito
    public CarritoDeCompras guardarCarrito(CarritoDeCompras carrito) {
        return carritoDeComprasRepository.save(carrito);
    }

    // Método para obtener el carrito del usuario
    public Optional<CarritoDeCompras> obtenerCarrito(Long userId) {
        return carritoDeComprasRepository.findByUsuarioId(userId);
    }

    // Método para vaciar el carrito
    public void vaciarCarrito(Long carritoId) {
        carritoDeComprasRepository.deleteById(carritoId);
    }

    // **Nueva Función**: Método para calcular el total de productos en el carrito
    public Double calcularTotalCarrito(CarritoDeCompras carrito) {
        return carrito.getItems().stream()
                .mapToDouble(item -> item.getCantidad() * item.getProducto().getPrecio())
                .sum();
    }
}
