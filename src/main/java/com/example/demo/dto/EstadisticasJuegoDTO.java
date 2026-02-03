package com.example.demo.dto;

// DTO para estadísticas generales del juego
public class EstadisticasJuegoDTO {
    private int totalJugadores;
    private int totalPartidas;
    private double promedioScorePorPartida;
    private double promedioDuracionPorPartida;
    private double promedioJugadoresPorPartida;

    public int getTotalJugadores() { return totalJugadores; }
    public void setTotalJugadores(int totalJugadores) { this.totalJugadores = totalJugadores; }

    public int getTotalPartidas() { return totalPartidas; }
    public void setTotalPartidas(int totalPartidas) { this.totalPartidas = totalPartidas; }

    public double getPromedioScorePorPartida() { return promedioScorePorPartida; }
    public void setPromedioScorePorPartida(double promedioScorePorPartida) { this.promedioScorePorPartida = promedioScorePorPartida; }

    public double getPromedioDuracionPorPartida() { return promedioDuracionPorPartida; }
    public void setPromedioDuracionPorPartida(double promedioDuracionPorPartida) { this.promedioDuracionPorPartida = promedioDuracionPorPartida; }

    public double getPromedioJugadoresPorPartida() { return promedioJugadoresPorPartida; }
    public void setPromedioJugadoresPorPartida(double promedioJugadoresPorPartida) { this.promedioJugadoresPorPartida = promedioJugadoresPorPartida; }
}
