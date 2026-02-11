package com.example.demo.controller;

import com.example.demo.dto.JugadorDTO;
import com.example.demo.model.Jugador;
import com.example.demo.service.JugadorService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// CRUD para jugadores
@RestController // Indica que esta clase es un controlador REST.
@RequestMapping("/api/jugadores") // Mapea la ruta base.
@CrossOrigin(origins = "*") // Configuración CORS para permitir solicitudes desde cualquier origen.
public class JugadorController {

    //Vinculo al servicio de jugadores.
    private final JugadorService service;

    public JugadorController(JugadorService service) { this.service = service; } // Inyección de dependencia del servicio.

    @PostMapping // Maneja solicitudes POST para crear un nuevo jugador.
    public Jugador crear(@RequestBody JugadorDTO dto) { return service.crear(dto); }

    @GetMapping // Maneja solicitudes GET para listar todos los jugadores.
    public List<JugadorDTO> listar() { return service.obtenerTodos(); }

    @GetMapping("/{id}") // Maneja solicitudes GET para obtener un jugador por ID.
    public JugadorDTO obtener(@PathVariable Long id) { return service.obtenerPorId(id); }

    @PutMapping("/{id}") // Maneja solicitudes PUT para actualizar un jugador existente.
    public Jugador actualizar(@PathVariable Long id, @RequestBody JugadorDTO dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}") // Maneja solicitudes DELETE para eliminar un jugador por ID.
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "Jugador eliminado";
    }
}
