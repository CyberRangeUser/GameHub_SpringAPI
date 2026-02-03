package com.example.demo.dto;

// DTO para representar un jugador dentro de una partida
public class JugadorScoreDTO {
    private Long jugadorId;
    private String nombre;
    private int score;

    public Long getJugadorId() { return jugadorId; }
    public void setJugadorId(Long jugadorId) { this.jugadorId = jugadorId; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
}

