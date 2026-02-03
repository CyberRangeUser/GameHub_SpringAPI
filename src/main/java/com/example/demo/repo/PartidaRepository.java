package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Partida;

public interface PartidaRepository extends JpaRepository<Partida, Long> {}

