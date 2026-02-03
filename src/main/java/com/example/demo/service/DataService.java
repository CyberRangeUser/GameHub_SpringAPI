package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.model.*;
import com.example.demo.repo.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

// Servicio para manejar ranking, historial y estadísticas de jugadores y partidas
@Service
public class DataService {

    private final JugadorRepository jugadorRepo;
    private final PartidaRepository partidaRepo;

    public DataService(JugadorRepository jugadorRepo, PartidaRepository partidaRepo) {
        this.jugadorRepo = jugadorRepo;
        this.partidaRepo = partidaRepo;
    }

    // Ranking de jugadores por score total
    public List<RankingJugadorDTO> rankingJugadores() {
        return jugadorRepo.findAll().stream().map(j -> {
            RankingJugadorDTO dto = new RankingJugadorDTO();
            dto.setJugadorId(j.getId());
            dto.setNombre(j.getNombre());
            int scoreTotal = j.getPartidas().stream().mapToInt(JugadorPartida::getScore).sum();
            int duracionTotal = j.getPartidas().stream().mapToInt(jp -> jp.getPartida().getDuracion()).sum();
            dto.setScoreTotal(scoreTotal);
            dto.setDuracionTotal(duracionTotal);
            dto.setTotalPartidas(j.getPartidas().size());
            return dto;
        }).sorted((a, b) -> Integer.compare(b.getScoreTotal(), a.getScoreTotal()))
          .collect(Collectors.toList());
    }

    // Historial de partidas de un jugador
    public List<HistorialPartidaDTO> historial(Long jugadorId) {
        Jugador j = jugadorRepo.findById(jugadorId).orElseThrow(() -> new RuntimeException("Jugador no encontrado"));
        return j.getPartidas().stream().map(jp -> {
            HistorialPartidaDTO dto = new HistorialPartidaDTO();
            dto.setPartidaId(jp.getPartida().getId());
            dto.setScore(jp.getScore());
            dto.setDuracion(jp.getPartida().getDuracion());
            dto.setFecha(jp.getPartida().getFecha());
            return dto;
        }).sorted((a,b) -> b.getFecha().compareTo(a.getFecha()))
          .collect(Collectors.toList());
    }

    // Estadísticas de un jugador
    public EstadisticasJugadorDTO estadisticasJugador(Long jugadorId) {
        Jugador j = jugadorRepo.findById(jugadorId).orElseThrow(() -> new RuntimeException("Jugador no encontrado"));
        EstadisticasJugadorDTO dto = new EstadisticasJugadorDTO();
        dto.setJugadorId(j.getId());
        int totalPartidas = j.getPartidas().size();
        dto.setTotalPartidas(totalPartidas);

        List<Integer> scores = j.getPartidas().stream().map(JugadorPartida::getScore).toList();
        dto.setScoreTotal(scores.stream().mapToInt(Integer::intValue).sum());
        dto.setScorePromedio(scores.isEmpty() ? 0 : scores.stream().mapToInt(Integer::intValue).average().orElse(0));
        dto.setScoreMaximo(scores.stream().mapToInt(Integer::intValue).max().orElse(0));
        dto.setScoreMinimo(scores.stream().mapToInt(Integer::intValue).min().orElse(0));

        List<Integer> duraciones = j.getPartidas().stream().map(jp -> jp.getPartida().getDuracion()).toList();
        dto.setDuracionTotal(duraciones.stream().mapToInt(Integer::intValue).sum());
        dto.setDuracionPromedio(duraciones.isEmpty() ? 0 : duraciones.stream().mapToInt(Integer::intValue).average().orElse(0));

        return dto;
    }

    // Partidas recientes
    public List<PartidaRecienteDTO> recientes(int limit) {
        return partidaRepo.findAll().stream()
                .sorted((a,b) -> b.getFecha().compareTo(a.getFecha()))
                .limit(limit)
                .map(p -> {
                    PartidaRecienteDTO dto = new PartidaRecienteDTO();
                    dto.setPartidaId(p.getId());
                    dto.setDuracion(p.getDuracion());
                    dto.setFecha(p.getFecha());
                    dto.setJugadores(p.getJugadores().stream().map(jp -> {
                        JugadorScoreDTO js = new JugadorScoreDTO();
                        js.setJugadorId(jp.getJugador().getId());
                        js.setNombre(jp.getJugador().getNombre());
                        js.setScore(jp.getScore());
                        return js;
                    }).collect(Collectors.toList()));
                    return dto;
                }).toList();
    }

    // Ranking de partidas por score total
    public List<RankingPartidaDTO> rankingPartidas() {
        return partidaRepo.findAll().stream().map(p -> {
            RankingPartidaDTO dto = new RankingPartidaDTO();
            dto.setPartidaId(p.getId());
            dto.setDuracion(p.getDuracion());
            dto.setFecha(p.getFecha());
            int scoreTotal = p.getJugadores().stream().mapToInt(JugadorPartida::getScore).sum();
            dto.setScoreTotal(scoreTotal);
            return dto;
        }).sorted((a,b) -> Integer.compare(b.getScoreTotal(), a.getScoreTotal()))
          .collect(Collectors.toList());
    }

    // Estadísticas generales del juego
    public EstadisticasJuegoDTO estadisticasJuego() {
        EstadisticasJuegoDTO dto = new EstadisticasJuegoDTO();
        List<Jugador> jugadores = jugadorRepo.findAll();
        List<Partida> partidas = partidaRepo.findAll();

        dto.setTotalJugadores(jugadores.size());
        dto.setTotalPartidas(partidas.size());
        dto.setPromedioScorePorPartida(
                partidas.isEmpty() ? 0 : partidas.stream().mapToInt(p -> p.getJugadores().stream().mapToInt(JugadorPartida::getScore).sum()).average().orElse(0)
        );
        dto.setPromedioDuracionPorPartida(
                partidas.isEmpty() ? 0 : partidas.stream().mapToInt(Partida::getDuracion).average().orElse(0)
        );
        dto.setPromedioJugadoresPorPartida(
                partidas.isEmpty() ? 0 : partidas.stream().mapToInt(p -> p.getJugadores().size()).average().orElse(0)
        );
        return dto;
    }
}
