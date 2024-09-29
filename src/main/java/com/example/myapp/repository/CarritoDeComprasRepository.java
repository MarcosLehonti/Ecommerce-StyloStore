package com.example.myapp.repository;

import com.example.myapp.model.CarritoDeCompras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarritoDeComprasRepository extends JpaRepository<CarritoDeCompras, Long> {
    Optional<CarritoDeCompras> findByUsuarioId(Long usuarioId);
}
