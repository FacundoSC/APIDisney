package com.javadabadu.disney.repository;

import com.javadabadu.disney.models.entity.Personaje;
import com.javadabadu.disney.models.entity.TipoPersonaje;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PersonajeRepositoryTest {

    @Autowired
    private PersonajeRepository personajeRepository;

    @Test
    void findByIdTest() {
        Optional<Personaje> personaje = personajeRepository.findById(1);
        assertNotNull(personaje);
        assertEquals(1, personaje.orElseThrow().getId());
    }


    @Test
    void findAllTest() {
        List<Personaje> personajes = personajeRepository.findAll();
        assertFalse(personajes.isEmpty());
        assertTrue(personajes.size()>1);
    }

    @Test
    void saveTest() {
        Personaje personaje = new Personaje(personajeRepository.lastValueId(), "Personaje test", 20, "Historia test", "/imagen/test",50, TipoPersonaje.REAL);

        Personaje personajeGuardado = personajeRepository.save(personaje);
        assertNotNull(personajeGuardado);
        assertNotNull(personajeGuardado.getId());
        assertTrue(personajeGuardado.getId()>0);
        assertEquals("Personaje test", personajeGuardado.getNombre());
    }
    
    @Test
    void exitsByIdTest() {
        boolean fueEncotrado = personajeRepository.existsById(1);

        assertNotNull(fueEncotrado);
        assertTrue(fueEncotrado);
    }

    @Test
    void exitsByIdTestIdNoEncontrado() {
        Boolean noFueEncotrado = personajeRepository.existsById(2000);

        assertNotNull(noFueEncotrado);
        assertFalse(noFueEncotrado);
    }

    @Test
    void lastValueTest() {
        Integer idValue = personajeRepository.lastValueId();

        assertNotNull(idValue);
        assertTrue(idValue>0);
    }

    @Test
    void softDeleteTest() {
        boolean fueBorrado = personajeRepository.softDelete(1);
        assertNotNull(fueBorrado);
        assertTrue(fueBorrado);
    }

    //Todo esta implementación no es correcta para esta condición
    @Disabled
    @Test
    void softDeleteTestIdNoEncontrado() {
        boolean fueBorrado = personajeRepository.softDelete(12222);
        assertNotNull(fueBorrado);
        assertFalse(fueBorrado);
    }
}