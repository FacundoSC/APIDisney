package com.javadabadu.disney.service.impl;

import com.javadabadu.disney.exception.ExceptionBBDD;
import com.javadabadu.disney.repository.PersonajeRepository;
import com.javadabadu.disney.service.PersonajeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class PersonajeServiceImplTestMirna {
    @MockBean
    private PersonajeRepository personajeRepository;

    @Autowired
    private PersonajeService personajeService;

    @Test
    void puebaTest() {
        assertTrue(true);
        String test ="test";
        assertNotNull(test);
        assertEquals("test", test);
    }

    @Test
    void existByIdTest() throws ExceptionBBDD {
        when(personajeRepository.existsById(1)).thenReturn(true);
        assertTrue(personajeService.existsById(1));
        assertTrue(personajeService.existsById(1));
        verify(personajeRepository,times(2)).existsById(1);
    }
}