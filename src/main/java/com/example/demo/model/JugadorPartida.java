package com.example.demo.model;

import jakarta.persistence.*;

// Entidad intermedia JugadorPartida
@Entity
@Table(name = "jugador_partida")
public class JugadorPartida {

    @EmbeddedId
    private JugadorPartidaId id = new JugadorPartidaId();

    @ManyToOne
    @MapsId("jugadorId")
    private Jugador jugador;

    @ManyToOne
    @MapsId("partidaId")
    private Partida partida;

    private int score;

    // Getters y setters
    public JugadorPartidaId getId() { return id; }
    public void setId(JugadorPartidaId id) { this.id = id; }

    public Jugador getJugador() { return jugador; }
    public void setJugador(Jugador jugador) { this.jugador = jugador; }

    public Partida getPartida() { return partida; }
    public void setPartida(Partida partida) { this.partida = partida; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
}

