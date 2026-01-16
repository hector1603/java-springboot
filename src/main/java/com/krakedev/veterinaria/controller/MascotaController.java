package com.krakedev.veterinaria.controller;

//import java.util.ArrayList;
import java.util.List;
//import java.util.Optional;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakedev.veterinaria.entity.Mascota;
import com.krakedev.veterinaria.service.MascotaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/mascotas")
@RequiredArgsConstructor
public class MascotaController {
    
    private final MascotaService mascotaService;

    //private List<Mascota> mascotas = new ArrayList<>();

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarMascota(@RequestBody Mascota mascota) {
        Mascota nuevaMascota = mascotaService.registrarMascota(mascota);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaMascota);
    }

    @GetMapping
    public ResponseEntity<List<Mascota>> listarMascotas() {
        List<Mascota> mascotas = mascotaService.listarMascotas();
        return ResponseEntity.ok(mascotas);
    }

    @GetMapping("/buscar/nombre/{nombre}")
    public ResponseEntity<?> buscarPorNombre(@PathVariable String nombre) {
        Optional<Mascota> mascota = mascotaService.buscarPorNombre(nombre);
        return mascota.isPresent() ? ResponseEntity.ok(mascota.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mascota no encontrada");
    }

    @GetMapping("/buscar/id/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<Mascota> mascota = mascotaService.buscarPorId(id);
        return mascota.isPresent() ? ResponseEntity.ok(mascota.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mascota no encontrada");
    }

    @PutMapping("/actualizar/{idMascota}")
    public ResponseEntity<?> actualizarMascota(@PathVariable Long idMascota, @RequestBody Mascota mascota) {
        try {
            Mascota mascotaActualizada = new Mascota();
            mascotaActualizada.setNombre(mascota.getNombre());
            mascotaActualizada.setEspecie(mascota.getEspecie());
            mascotaActualizada.setEdad(mascota.getEdad());
            mascotaActualizada.setPropietario(mascota.getPropietario());

            Mascota mascotaBBDD = mascotaService.actualizarMascota(idMascota, mascotaActualizada);
            return ResponseEntity.ok(mascotaBBDD);
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{idMascota}")
    public ResponseEntity<?> eliminarMascota(@PathVariable Long idMascota) {
        try {
            mascotaService.eliminarMascota(idMascota);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
        }
    }
    
    /*
    @PostMapping
    public Mascota agregarMascota(@RequestBody Mascota mascota) {
        mascotas.add(mascota);
        return mascota;
    }

    @GetMapping
    public List<Mascota> obtenerTodasLasMascotas() {
        return mascotas;
    }
        

    @GetMapping("/{id}")
    public Mascota obtenerMascotaPorId(@PathVariable int id) {
        Optional<Mascota> mascota = mascotas.stream()
                .filter(m -> m.getId() == id)
                .findFirst();
        return mascota.orElse(null);
    }

    @DeleteMapping("/{id}")
    public void eliminarMascota(@PathVariable int id) {
        mascotas.removeIf(m -> m.getId() == id);
    }
     */
}
