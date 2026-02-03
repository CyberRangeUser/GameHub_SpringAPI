package com.example.demo.controller;

import com.example.demo.dto.PartidaDTO;
import com.example.demo.service.GameService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// CRUD para partidas
@RestController
@RequestMapping("/api/partidas")
@CrossOrigin(origins = "*")
public class GameController {

    private final GameService service;

    public GameController(GameService service) { this.service = service; }

    @PostMapping
    public String crear(@RequestBody PartidaDTO dto) {
        service.crear(dto);
        return "Partida creada";
    }

    @GetMapping
    public List<PartidaDTO> listar() { return service.obtenerTodas(); }

    @GetMapping("/{id}")
    public PartidaDTO obtener(@PathVariable Long id) { return service.obtenerPorId(id); }

    @PutMapping("/{id}")
    public String actualizar(@PathVariable Long id, @RequestBody PartidaDTO dto) {
        service.actualizar(id, dto);
        return "Partida actualizada";
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "Partida eliminada";
    }
}
