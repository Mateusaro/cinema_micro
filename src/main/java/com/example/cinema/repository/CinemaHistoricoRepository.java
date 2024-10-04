package com.example.cinema.repository;

import com.example.cinema.model.CinemaHistorico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaHistoricoRepository extends JpaRepository<CinemaHistorico, Long> {
}