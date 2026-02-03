package com.example.demo.controller;

import com.example.demo.dto.JugadorDTO;
import com.example.demo.model.Jugador;
import com.example.demo.service.JugadorService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// CRUD para jugadores
@RestController
@RequestMapping("/api/jugadores")
@CrossOrigin(origins = "*")
public class JugadorController {

    private final JugadorService service;

    public JugadorController(JugadorService service) { this.service = service; }

    @PostMapping
    public Jugador crear(@RequestBody JugadorDTO dto) { return service.crear(dto); }

    @GetMapping
    public List<JugadorDTO> listar() { return service.obtenerTodos(); }

    @GetMapping("/{id}")
    public JugadorDTO obtener(@PathVariable Long id) { return service.obtenerPorId(id); }

    @PutMapping("/{id}")
    public Jugador actualizar(@PathVariable Long id, @RequestBody JugadorDTO dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "Jugador eliminado";
    }
}
