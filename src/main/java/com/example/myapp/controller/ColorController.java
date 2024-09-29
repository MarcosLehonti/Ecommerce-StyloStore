package com.example.myapp.controller;

import com.example.myapp.model.Color;
import com.example.myapp.service.ColorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/colores")
public class ColorController {

    private final ColorService colorService;

    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @GetMapping
    public List<Color> obtenerTodosLosColores() {
        return colorService.obtenerTodosLosColores();
    }

    @PostMapping("/agregar")
    public Color agregarColor(@RequestBody Color color) {
        return colorService.guardarColor(color);
    }

}

