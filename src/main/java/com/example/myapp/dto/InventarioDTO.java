//package com.example.myapp.dto;
//// Importa las anotaciones de Lombok si quieres utilizar Lombok para generar los métodos
//// import lombok.AllArgsConstructor;
//// import lombok.Data;
//// import lombok.NoArgsConstructor;
//
//// También puedes agregar manualmente los getters y setters si no usas Lombok
//
//// @Data  // Genera getters y setters automáticamente
//// @AllArgsConstructor  // Genera constructor con todos los atributos
//// @NoArgsConstructor  // Genera constructor vacío
//public class InventarioDTO {
//    private String color;    // Código Hexadecimal del color o ID del color
//    private Integer cantidad;  // Cantidad de productos de este color
//
//    // Constructor vacío
//    public InventarioDTO() {}
//
//    // Constructor con parámetros
//    public InventarioDTO(String color, Integer cantidad) {
//        this.color = color;
//        this.cantidad = cantidad;
//    }
//
//    // Getters y Setters
//    public String getColor() {
//        return color;
//    }
//
//    public void setColor(String color) {
//        this.color = color;
//    }
//
//    public Integer getCantidad() {
//        return cantidad;
//    }
//
//    public void setCantidad(Integer cantidad) {
//        this.cantidad = cantidad;
//    }
//
//    // Sobreescribimos el método toString para facilitar la depuración si es necesario
//    @Override
//    public String toString() {
//        return "InventarioDTO{" +
//                "color='" + color + '\'' +
//                ", cantidad=" + cantidad +
//                '}';
//    }
//}

package com.example.myapp.dto;

public class InventarioDTO {

    private Long id;
    private String nombreProducto;       // Nombre del producto
    private String descripcionProducto;  // Descripción del producto
    private Double precioProducto;       // Precio del producto
    private String imagenUrlProducto;    // URL de la imagen del producto
    private String nombreColor;          // Nombre del color (ej. Rojo, Azul)
    private String codigoHexColor;       // Código hexadecimal del color (ej. #FFFFFF)
    private Integer cantidad;            // Cantidad del producto en este color

    // Constructor vacío
    public InventarioDTO() {}

    // Constructor con parámetros
    public InventarioDTO(Long id, String nombreProducto, String descripcionProducto, Double precioProducto, String imagenUrlProducto, String nombreColor, String codigoHexColor, Integer cantidad) {
        this.id = id;
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.precioProducto = precioProducto;
        this.imagenUrlProducto = imagenUrlProducto;
        this.nombreColor = nombreColor;
        this.codigoHexColor = codigoHexColor;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public Double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(Double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public String getImagenUrlProducto() {
        return imagenUrlProducto;
    }

    public void setImagenUrlProducto(String imagenUrlProducto) {
        this.imagenUrlProducto = imagenUrlProducto;
    }

    public String getNombreColor() {
        return nombreColor;
    }

    public void setNombreColor(String nombreColor) {
        this.nombreColor = nombreColor;
    }

    public String getCodigoHexColor() {
        return codigoHexColor;
    }

    public void setCodigoHexColor(String codigoHexColor) {
        this.codigoHexColor = codigoHexColor;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "InventarioDTO{" +
                "id=" + id +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", descripcionProducto='" + descripcionProducto + '\'' +
                ", precioProducto=" + precioProducto +
                ", imagenUrlProducto='" + imagenUrlProducto + '\'' +
                ", nombreColor='" + nombreColor + '\'' +
                ", codigoHexColor='" + codigoHexColor + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }



    public String getColor() {
        return this.codigoHexColor;  // Retorna el código hexadecimal del color
    }

}
