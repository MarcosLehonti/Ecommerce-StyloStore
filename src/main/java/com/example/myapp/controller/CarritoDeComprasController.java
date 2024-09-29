package com.example.myapp.controller;

import com.example.myapp.model.CarritoDeCompras;
import com.example.myapp.model.ItemCarrito;
import com.example.myapp.model.Producto;
import com.example.myapp.model.User;
import com.example.myapp.repository.ItemCarritoRepository;
import com.example.myapp.repository.ProductoRepository;
import com.example.myapp.service.CarritoDeComprasService;
import com.example.myapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/carrito")
public class CarritoDeComprasController {

    @Autowired
    private CarritoDeComprasService carritoDeComprasService;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemCarritoRepository itemCarritoRepository; // Inyecta el ItemCarritoRepository

    @PostMapping("/agregar")
    public CarritoDeCompras agregarProductoAlCarrito(@RequestBody ItemCarrito itemCarrito, Authentication authentication) {
        // Obtener el nombre de usuario autenticado
        String username = authentication.getName();

        // Buscar el usuario por su nombre de usuario
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Buscar el producto por su ID
        Producto producto = productoRepository.findById(itemCarrito.getProducto().getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Buscar o crear el carrito de compras del usuario
        CarritoDeCompras carrito = carritoDeComprasService.obtenerOCrearCarrito(user);

        // Crear un nuevo ItemCarrito con el producto encontrado
        ItemCarrito nuevoItem = new ItemCarrito();
        nuevoItem.setProducto(producto);
        nuevoItem.setCantidad(itemCarrito.getCantidad());
        nuevoItem.setCarrito(carrito); // Establecer la referencia del carrito en el item

        // Guardar el ItemCarrito en la base de datos antes de agregarlo al carrito
        itemCarritoRepository.save(nuevoItem);

        // Agregar el nuevo item al carrito
        carrito.getItems().add(nuevoItem);
        carrito.actualizarTotal();  // Actualizar el total del carrito

        // Guardar los cambios en el carrito
        carritoDeComprasService.guardarCarrito(carrito);

        return carrito;
    }


    @GetMapping("/obtener")
    public ResponseEntity<Map<String, Object>> obtenerCarrito(Authentication authentication) {
        // Obtener el nombre de usuario autenticado
        String username = authentication.getName();

        // Buscar el usuario por su nombre de usuario
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Obtener o crear el carrito del usuario
        CarritoDeCompras carrito = carritoDeComprasService.obtenerOCrearCarrito(user);

        // Verificar si el carrito está vacío
        if (carrito.getItems().isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("productos", List.of());  // Devolver una lista vacía de productos
            response.put("total", 0.0);  // Total es 0
            response.put("mensaje", "Aún no tienes productos en tu carrito");  // Mensaje informativo
            return ResponseEntity.ok(response);
        }

        // Mapear los productos del carrito con nombre, cantidad, imagen e ID
        List<Map<String, Object>> productosCarrito = carrito.getItems().stream()
                .map(item -> {
                    Map<String, Object> productoInfo = new HashMap<>();
                    productoInfo.put("id", item.getProducto().getId()); // Incluir el ID del producto
                    productoInfo.put("nombre", item.getProducto().getNombre());
                    productoInfo.put("cantidad", item.getCantidad());

                    // Obtener la URL de la imagen desde el controlador de productos
                    String[] partesImagen = item.getProducto().getImagenUrl().split("/");
                    String nombreArchivo = partesImagen[partesImagen.length - 1];
                    productoInfo.put("imagenUrl", "http://localhost:8080/api/productos/imagen/" + nombreArchivo);

                    return productoInfo;
                })
                .collect(Collectors.toList());

        // Preparar la respuesta con los productos y el total
        Map<String, Object> response = new HashMap<>();
        response.put("productos", productosCarrito);
        response.put("total", carrito.getTotal());  // Incluir el total del carrito

        return ResponseEntity.ok(response);
    }

    // Vaciar el carrito
    @DeleteMapping("/vaciar")
    public void vaciarCarrito(Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        CarritoDeCompras carrito = carritoDeComprasService.obtenerCarrito(user.getId()).orElseThrow();
        carritoDeComprasService.vaciarCarrito(carrito.getId());
    }

    // Eliminar un producto del carrito
    @DeleteMapping("/eliminar/{productoId}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ADMIN') or hasRole('ADMIN_DE_PRODUCTOS')")
    public ResponseEntity<?> eliminarProductoDelCarrito(@PathVariable Long productoId, Authentication authentication) {
        // Obtener el nombre de usuario autenticado
        String username = authentication.getName();

        // Buscar el usuario por su nombre de usuario
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Obtener el carrito del usuario
        CarritoDeCompras carrito = carritoDeComprasService.obtenerCarrito(user.getId())
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        // Buscar el item del carrito a eliminar usando el id del producto
        ItemCarrito itemParaEliminar = carrito.getItems().stream()
                .filter(item -> item.getProducto().getId().equals(productoId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Producto no encontrado en el carrito."));

        // Eliminar el item del carrito
        carrito.getItems().remove(itemParaEliminar);

        // Establecer la referencia inversa a null para que Hibernate lo detecte
        itemParaEliminar.setProducto(null);

        // Actualizar el total del carrito
        carrito.actualizarTotal();

        // Guardar el carrito actualizado
        carritoDeComprasService.guardarCarrito(carrito);

        return ResponseEntity.ok("Producto eliminado del carrito correctamente");
    }

    // **Nueva Función**: Método para obtener el total del carrito de un usuario
    @GetMapping("/total")
    public ResponseEntity<Map<String, Object>> obtenerTotalCarrito(Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Obtener el carrito del usuario
        CarritoDeCompras carrito = carritoDeComprasService.obtenerOCrearCarrito(user);

        // Preparar la respuesta con el total
        Map<String, Object> response = new HashMap<>();
        response.put("total", carrito.getTotal());

        return ResponseEntity.ok(response);
    }
}

