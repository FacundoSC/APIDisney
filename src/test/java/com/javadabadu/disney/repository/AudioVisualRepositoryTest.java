package com.javadabadu.disney.repository;

import com.javadabadu.disney.models.entity.AudioVisual;
import com.javadabadu.disney.models.entity.Genero;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AudioVisualRepositoryTest {
    @Autowired
    private SerieRepository serieRepository;
    private Integer id;
    private Optional<String> tag;
    @BeforeAll
    static void initAll() {
    }

    @AfterAll
    static void tearDownAll() {
    }

    @BeforeEach
    void init(TestInfo testInfo) {
        tag = testInfo.getTags().stream().findFirst();
        if (!tag.isEmpty()) {
            id = serieRepository.lastValueId();
            if (tag.get().equals("LastId"))
                id -= 1;
        }
    }

    @DisplayName("Validar el obtener todas los AudioVisuales")
    @Test
    @Order(1)
    void getAllAudioVisualTest() {
        List<AudioVisual> series = serieRepository.findAll();
        assertFalse(series.isEmpty(), "no se encuentran elementos  en la  lista");
    }


    @DisplayName("Validar borrado de un AudioVisual")
    @Test
    @Order(2)
    @Tag("lastId")
    void softDeleteTest() {
        assertEquals(true, serieRepository.softDelete(id));
    }

    @DisplayName("Validar la existencia de un AudioVisual")
    @Test
    @Order(3)
    @Tag("LastId")
    void existSerieByIdTest() {
        assertTrue(serieRepository.existsById(id), "no existe Serie con ese identificador");
    }


    @DisplayName("Validar la no existencia de u AudioVisual")
    @Test
    @Order(4)
    @Tag("NextID")
    void notExistSerieByIdTest() {
        assertFalse(serieRepository.existsById(id), "Ya existe Serie con ese identificador");
    }


}