package com.krakedev.veterinaria.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mascota {
    private int id;
    private String nombre;
    private String especie;
    private int edad;
    private String propietario;
}
