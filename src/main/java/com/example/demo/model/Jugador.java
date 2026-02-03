package com.example.demo.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;

// Entidad Jugador
@Entity
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;

    // Relación M:N con Partida a través de JugadorPartida
    @JsonIgnore
    @OneToMany(mappedBy = "jugador", cascade = CascadeType.ALL)
    private Set<JugadorPartida> partidas = new HashSet<>();

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Set<JugadorPartida> getPartidas() { return partidas; }
    public void setPartidas(Set<JugadorPartida> partidas) { this.partidas = partidas; }
}
