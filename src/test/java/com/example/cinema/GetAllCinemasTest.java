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

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class GetAllCinemasTest {

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
    public void testGetAllCinemas() throws Exception {
        Cinema cinema1 = new Cinema();
        cinema1.setId(1L);
        cinema1.setNome("Cinema 1");

        Cinema cinema2 = new Cinema();
        cinema2.setId(2L);
        cinema2.setNome("Cinema 2");

        List<Cinema> cinemas = Arrays.asList(cinema1, cinema2);

        when(cinemaService.getAllCinemas()).thenReturn(cinemas);

        mockMvc.perform(get("/cinemas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nome").value("Cinema 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].nome").value("Cinema 2"));

        verify(cinemaService, times(1)).getAllCinemas();
    }
}
