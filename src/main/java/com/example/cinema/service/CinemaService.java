package com.example.cinema.service;

import com.example.cinema.model.Cinema;
import com.example.cinema.model.CinemaHistorico;
import com.example.cinema.repository.CinemaHistoricoRepository;
import com.example.cinema.repository.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CinemaService {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private CinemaHistoricoRepository cinemaHistoricoRepository;

    public Cinema createCinema(Cinema cinema) {
        cinema.setUltimaAcao("CREATE");
        cinema.setDataUltimaAlteracao(LocalDateTime.now());
        Cinema savedCinema = cinemaRepository.save(cinema);
        registrarHistorico(savedCinema, "CREATE");
        return savedCinema;
    }

    public Cinema updateCinema(Long id, Cinema cinema) {
        Cinema existingCinema = cinemaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cinema não encontrado"));
        existingCinema.setNome(cinema.getNome());
        existingCinema.setCep(cinema.getCep());
        // Atualizar os outros campos...

        existingCinema.setUltimaAcao("UPDATE");
        existingCinema.setDataUltimaAlteracao(LocalDateTime.now());
        Cinema updatedCinema = cinemaRepository.save(existingCinema);
        registrarHistorico(updatedCinema, "UPDATE");
        return updatedCinema;
    }

    public void deleteCinema(Long id) {
        Cinema cinema = cinemaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cinema não encontrado"));
        cinemaRepository.delete(cinema);
        registrarHistorico(cinema, "DELETE");
    }

    private void registrarHistorico(Cinema cinema, String acao) {
        CinemaHistorico historico = new CinemaHistorico();
        historico.setCinemaId(cinema.getId());
        historico.setNome(cinema.getNome());
        historico.setAcao(acao);
        historico.setDataAlteracao(LocalDateTime.now());
        cinemaHistoricoRepository.save(historico);
    }

    public List<CinemaHistorico> getHistorico() {
        return cinemaHistoricoRepository.findAll();
    }

    public List<Cinema> getAllCinemas() {
        return cinemaRepository.findAll();
    }

}
