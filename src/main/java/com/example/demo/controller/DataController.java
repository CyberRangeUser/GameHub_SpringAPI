package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.DataService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// Controlador unificado para Data (Ranking, Historial, Estadísticas)
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class DataController {

    private final DataService dataService;

    public DataController(DataService dataService) { this.dataService = dataService; }

    @GetMapping("/jugadores/ranking")
    public List<RankingJugadorDTO> rankingJugadores() { return dataService.rankingJugadores(); }

    @GetMapping("/jugadores/{id}/historial")
    public List<HistorialPartidaDTO> historialJugador(@PathVariable Long id) { return dataService.historial(id); }

    @GetMapping("/jugadores/{id}/estadisticas")
    public EstadisticasJugadorDTO estadisticasJugador(@PathVariable Long id) { return dataService.estadisticasJugador(id); }

    @GetMapping("/partidas/recientes")
    public List<PartidaRecienteDTO> partidasRecientes(@RequestParam(defaultValue = "10") int limit) {
        return dataService.recientes(limit);
    }

    @GetMapping("/partidas/ranking")
    public List<RankingPartidaDTO> rankingPartidas() { return dataService.rankingPartidas(); }

    @GetMapping("/estadisticas")
    public EstadisticasJuegoDTO estadisticasJuego() { return dataService.estadisticasJuego(); }
}
