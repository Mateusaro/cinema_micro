package com.example.cinema;

import com.example.cinema.controller.CinemaController;
import com.example.cinema.model.Cinema;
import com.example.cinema.service.CinemaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CreateCinemaTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CinemaService cinemaService;

    @InjectMocks
    private CinemaController cinemaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cinemaController).build();
    }

    @Test
    public void testCreateCinema() throws Exception {
        Cinema cinema = new Cinema();
        cinema.setNome("Cinema Novo");

        when(cinemaService.createCinema(any(Cinema.class))).thenReturn(cinema);

        mockMvc.perform(post("/cinemas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Cinema Novo\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Cinema Novo"));

        verify(cinemaService, times(1)).createCinema(any(Cinema.class));
    }
}
