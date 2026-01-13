package com.krakedev.veterinaria;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/veterinaria")
public class VeterinariaController {

    @GetMapping("/bienvenida")
    public String saludar() {
        return "Bienvenido al Sistema de Gestión de Veterinaria!";
    }
}