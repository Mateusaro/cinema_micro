package com.example.cinema.rabbit;

import com.example.cinema.model.Cinema;
import com.example.cinema.service.CinemaService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CinemaQueueListener {
    @Autowired
    private CinemaService cinemaService;

    @RabbitListener(queues = RabbitMQConfig.CINEME_QUEUE)
    public void listen(Cinema cinema) {
        System.out.println("Mensagem recebida da fila: " + cinema.getNome());
        cinemaService.createCinema(cinema);
    }
}
