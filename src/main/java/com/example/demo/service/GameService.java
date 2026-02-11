package com.example.demo.service;

import com.example.demo.dto.PartidaDTO;
import com.example.demo.model.*;
import com.example.demo.repo.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

// Servicio para manejar partidas
@Service
public class GameService {

    private final JugadorRepository jugadorRepo; // Repositorio de jugadores
    private final PartidaRepository partidaRepo; // Repositorio de partidas
    private final JugadorPartidaRepository jpRepo; // Repositorio de relación jugador-partida

    public GameService(JugadorRepository j, PartidaRepository p, JugadorPartidaRepository jp) {
        this.jugadorRepo = j;
        this.partidaRepo = p;
        this.jpRepo = jp;
    } // Inyección de dependencias de los repositorios

    // Crear una partida
    public void crear(PartidaDTO dto) {
        Partida partida = new Partida(); // Nueva instancia de partida
        partida.setDuracion(dto.getDuracion()); // Establecer duración de la partida
        partidaRepo.save(partida); // Guardar la partida

        for (int i = 0; i < dto.getJugadorIds().size(); i++) {
            Jugador jugador = jugadorRepo.findById(dto.getJugadorIds().get(i)) // Buscar jugador por ID
                    .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));
            JugadorPartida jp = new JugadorPartida(); // Nueva instancia de relación jugador-partida
            jp.setJugador(jugador);
            jp.setPartida(partida);
            jp.setScore(dto.getScores().get(i));
            jpRepo.save(jp);
        } // Asociar jugadores a la partida con sus scores
    }

    // Leer todas las partidas
    public List<PartidaDTO> obtenerTodas() {
        return partidaRepo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    // Leer por ID
    public PartidaDTO obtenerPorId(Long id) {
        return toDTO(partidaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Partida no encontrada")));
    }

    // Actualizar partida
    public void actualizar(Long id, PartidaDTO dto) {
        Partida p = partidaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Partida no encontrada"));
        p.setDuracion(dto.getDuracion());
        partidaRepo.save(p);

        for (JugadorPartida jp : p.getJugadores()) {
            int index = dto.getJugadorIds().indexOf(jp.getJugador().getId());
            if (index != -1) jp.setScore(dto.getScores().get(index));
            jpRepo.save(jp);
        }
    }

    // Eliminar partida
    public void eliminar(Long id) { partidaRepo.deleteById(id); }

    private PartidaDTO toDTO(Partida p) {
        PartidaDTO dto = new PartidaDTO();
        dto.setId(p.getId());
        dto.setDuracion(p.getDuracion());
        dto.setJugadorIds(p.getJugadores().stream().map(jp -> jp.getJugador().getId()).collect(Collectors.toList()));
        dto.setScores(p.getJugadores().stream().map(JugadorPartida::getScore).collect(Collectors.toList()));
        return dto;
    }
}
