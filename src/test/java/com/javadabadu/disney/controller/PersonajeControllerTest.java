package com.javadabadu.disney.controller;

import com.javadabadu.disney.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(controllers = PersonajeController.class)
class PersonajeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonajeService personajeService;
}