package com.example.myapp.repository;
import com.example.myapp.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ColorRepository extends JpaRepository<Color, Long> {

    // Método para buscar un color por su código hexadecimal
    Optional<Color> findByCodigoHex(String codigoHex);
}
