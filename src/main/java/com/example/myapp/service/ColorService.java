package com.example.myapp.service;

import com.example.myapp.model.Color;
import com.example.myapp.repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;

@Service
public class ColorService {

    @Autowired
    private ColorRepository colorRepository;

    public Set<Color> obtenerColoresPorIds(List<Long> ids) {
        return new HashSet<>(colorRepository.findAllById(ids));
    }

    public List<Color> obtenerTodosLosColores() {
        return colorRepository.findAll();
    }

    public Color guardarColor(Color color) {
        return colorRepository.save(color);
    }



    // Método para obtener un color por ID
    public Color obtenerColorPorId(Long id) {
        Optional<Color> colorOpt = colorRepository.findById(id);
        return colorOpt.orElse(null);
    }
    // Método para buscar un color por su código hexadecimal
    public Color obtenerColorPorHex(String codigoHex) {
        Optional<Color> colorOpt = colorRepository.findByCodigoHex(codigoHex);
        return colorOpt.orElse(null);  // Devuelve el color si lo encuentra, o null si no existe
    }
}
