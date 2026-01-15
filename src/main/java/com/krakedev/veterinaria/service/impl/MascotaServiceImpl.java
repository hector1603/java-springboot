package com.krakedev.veterinaria.service.impl;

import java.util.List;
import java.util.Optional;

import com.krakedev.veterinaria.entity.Mascota;
import com.krakedev.veterinaria.repository.MascotaRepository;
import com.krakedev.veterinaria.service.MascotaService;

import lombok.SneakyThrows;

public class MascotaServiceImpl implements MascotaService {

    MascotaRepository mascotaRepository;

    @Override
    public Mascota registrarMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    @Override
    public List<Mascota> listarMascotas() {
        return mascotaRepository.findAll();
    }

    @Override
    public Optional<Mascota> buscarPorNombre(String nombre) {
        return mascotaRepository.findByNombreMascota(nombre);
    }

    @Override
    public Optional<Mascota> buscarPorId(Long id) {
        return mascotaRepository.findByIdMascota(id);
    }

    @Override
    @SneakyThrows
    public Mascota actualizarMascota(Long idMascota, Mascota mascota) {
        Mascota mascotaExistente = mascotaRepository.findByIdMascota(idMascota)
                .orElseThrow(() -> new Exception("Mascota no encontrada con id: " + idMascota));
        
        mascotaExistente.setNombre(mascota.getNombre());
        mascotaExistente.setEspecie(mascota.getEspecie());
        mascotaExistente.setEdad(mascota.getEdad());
        mascotaExistente.setPropietario(mascota.getPropietario());
        mascotaExistente.setFechaRegistro(mascota.getFechaRegistro());

        Mascota mascotaActulizada = mascotaRepository.save(mascotaExistente);
        return mascotaActulizada;
    }

    @Override
    @SneakyThrows
    public void eliminarMascota(Long idMascota) {
        mascotaRepository.findByIdMascota(idMascota)
                .orElseThrow(() -> new Exception("Mascota no encontrada con id: " + idMascota));

        mascotaRepository.deleteById(idMascota);
    }
}