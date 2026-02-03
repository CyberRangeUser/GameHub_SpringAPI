package com.example.demo.service;

import com.example.demo.dto.JugadorDTO;
import com.example.demo.model.Jugador;
import com.example.demo.repo.JugadorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

// Servicio para CRUD de jugadores
@Service
public class JugadorService {
    private final JugadorRepository repo;

    public JugadorService(JugadorRepository repo) { this.repo = repo; }

    public Jugador crear(JugadorDTO dto) {
        Jugador j = new Jugador();
        j.setNombre(dto.getNombre());
        j.setEmail(dto.getEmail());
        return repo.save(j);
    }

    public List<JugadorDTO> obtenerTodos() {
        return repo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public JugadorDTO obtenerPorId(Long id) {
        return toDTO(repo.findById(id).orElseThrow(() -> new RuntimeException("Jugador no encontrado")));
    }

    public Jugador actualizar(Long id, JugadorDTO dto) {
        Jugador j = repo.findById(id).orElseThrow(() -> new RuntimeException("Jugador no encontrado"));
        j.setNombre(dto.getNombre());
        j.setEmail(dto.getEmail());
        return repo.save(j);
    }

    public void eliminar(Long id) { repo.deleteById(id); }

    private JugadorDTO toDTO(Jugador j) {
        JugadorDTO dto = new JugadorDTO();
        dto.setId(j.getId());
        dto.setNombre(j.getNombre());
        dto.setEmail(j.getEmail());
        return dto;
    }
}
