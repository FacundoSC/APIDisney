package com.javadabadu.disney.repository;

import com.javadabadu.disney.exception.ExceptionBBDD;
import com.javadabadu.disney.models.entity.Genero;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class GeneroRepositoryTest {
    @Autowired
    private GeneroRepository generoRepository;
    @Autowired
    private MessageSource message;

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
        assertTrue(actual, "no existe genero con ese identificador");
    }

    @Test
    void noExistGeneroByIdTest() {
        assertFalse(generoRepository.existsById(-1), "existe genero con ese identificador");
    }

    @Test
    void getGeneroForIdTest() {
        assertTrue(generoRepository.findById(1).get() instanceof Genero);
    }

    @Test
    void NotGetGeneroForIdTest() {
        Integer id = -1;
        ExceptionBBDD thrown = assertThrows(ExceptionBBDD.class, () -> {
            generoRepository.findById(id).orElseThrow(() -> new ExceptionBBDD(message.getMessage("id.not.found", new String[]{Integer.toString(id)}, Locale.US), HttpStatus.NOT_FOUND));
        });
        assertEquals(message.getMessage("id.not.found", new String[]{Integer.toString(id)}, Locale.US), thrown.getMessage(), "El genero se ha encontrado");
    }
}