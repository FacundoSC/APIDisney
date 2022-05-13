package com.javadabadu.disney.repository;

import com.javadabadu.disney.models.entity.Personaje;
import com.javadabadu.disney.models.entity.TipoPersonaje;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PersonajeRepositoryTest {

    @Autowired
    private PersonajeRepository personajeRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    void findByIdTest() {
        Optional<Personaje> personaje = personajeRepository.findById(1);

        assertNotNull(personaje);
        assertEquals("Aladdin", personaje.orElseThrow().getNombre());

        Optional<Personaje> personaje2 = personajeRepository.findById(2);

        assertThrows(NoSuchElementException.class, () -> {
            personaje2.orElseThrow();
        });
    }


    @Test
    void findAllTest() {
        List<Personaje> personajes = personajeRepository.findAll();
        assertFalse(personajes.isEmpty());
        assertEquals(1, personajes.size());
    }

    @Test
    void saveTest() {
        Personaje personaje = new Personaje(null, "Personaje test", 20, "Historia test", "/imagen/test",50, TipoPersonaje.REAL);

        Personaje personajeGuardado = personajeRepository.save(personaje);

        System.out.println(personajeGuardado);

        assertNotNull(personajeGuardado);
        assertNotNull(personajeGuardado.getId());
        assertEquals("Personaje test", personajeGuardado.getNombre());
    }

/*    @Test
    void saveTest() {
        Personaje personaje = new Personaje(null, "Personaje test", 20, "Historia test", "/imagen/test",50, TipoPersonaje.REAL);

        Personaje personajeGuardado = personajeRepository.save(personaje);

        System.out.println(personajeGuardado);

        assertNotNull(personajeGuardado);
        assertNotNull(personajeGuardado.getId());
        assertEquals("Personaje test", personajeGuardado.getNombre());
    }*/

    @Test
    void exitsByIdTest() {
        Boolean fueEncotrado = personajeRepository.existsById(1);

        assertNotNull(fueEncotrado);
        assertTrue(fueEncotrado);
    }

    @Test
    void exitsByIdTestIdNoEncontrado() {
        Boolean noFueEncotrado = personajeRepository.existsById(20);

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
        Boolean fueBorrado = personajeRepository.softDelete(1);

        assertNotNull(fueBorrado);
        assertTrue(fueBorrado);
    }

    @Test
    @Disabled
    void softDeleteTestIdNoEncontrado() {
        Boolean fueBorrado = personajeRepository.softDelete(12);
        
        assertNotNull(fueBorrado);
        assertFalse(fueBorrado);
    }
}