package com.javadabadu.disney.repository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class GeneroRepositoryTest {
    @Autowired
    private GeneroRepository generoRepository;

    @DisplayName("Validar referencia no nula generoRepository")
    @Test
    void generoRepositoryNotNullTest() {
        assertNotNull(generoRepository, "la referencia al  repositorio genero es null");
    }

    @Test
    void lastValueIdTest() {
        int esperado = generoRepository.lastValueId();
        int actual = generoRepository.lastValueId();
        assertEquals(esperado, actual, "los identificadores no son iguales");

    }

    @Test
    @Disabled
    void softDeleteTest() {

        boolean actual = generoRepository.softDelete(1);
        assertEquals(true, actual, "fallo al intentar deshabilitar genero");
    }

    @Test
    void existGeneroByIdTest() {
        boolean actual = generoRepository.existsById(1);
        assertEquals(true, actual, "no existe genero con ese identificador");

    }


}