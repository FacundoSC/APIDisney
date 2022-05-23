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
    private AudioVisualRepository audioVisualRepository;
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
            id = audioVisualRepository.lastValueId();
            if (tag.get().equals("LastId"))
                id -= 1;
        }
    }

    @DisplayName("Validar referencia no nula AudioVisualRepository")
    @Test
    @Order(1)
    void audioVisualRepositoryNotNullTest() {
        assertNotNull(audioVisualRepository, "la referencia al  repositorio Serie es null");
    }

    @DisplayName("Validar obtencion del proximo id de AudioVisual")
    @Test
    @Order(2)
    @Tag("NextID")
    void nextIdNotNullTest() {
        assertTrue(id instanceof Integer, "no es posible obtener el ultimo identificador");
    }

    @DisplayName("Validar borrado de un an AudioVisual")
    @Test
    @Order(3)
    @Tag("lastId")
    void softDeleteTest() {
        assertEquals(true, audioVisualRepository.softDelete(id));
    }

    @DisplayName("Validar la existencia de una AudioVisual")
    @Test
    @Order(4)
    @Tag("LastId")
    void existAudiovisualByIdTest() {
        assertTrue(audioVisualRepository.existsById(id), "no existe AudioVisual con ese identificador");
    }


    @DisplayName("Validar la no existencia de un AudioVisual")
    @Test
    @Order(5)
    @Tag("NextID")
    void notExistAudiovisualByIdTest() {
        assertFalse(audioVisualRepository.existsById(id), "Ya existe AudioVisual con ese identificador");
    }

    @DisplayName("Validar el encontrar AudioVisual  por Id")
    @Test
    @Order(6)
    @Tag("NextId")
    void findAudioVisualTestForIdTest() {
        Optional<AudioVisual> audiovisual = audioVisualRepository.findById(id);
        assertTrue(audiovisual.isPresent(), " no se encontro la AudioVisual buscada por id");
    }

    @DisplayName("Validar el obtener todas los AudioVisuales")
    @Test
    @Order(8)
    void getAllAudioVisualTest() {
        List<AudioVisual> audioVisuals = audioVisualRepository.findAll();
        assertFalse(audioVisuals.isEmpty(), "no se encuentran elementos  en la  lista");
    }

    @DisplayName("Validar el guardar una Serie")
    @Test
    @Order(1)
    @Tag("NextID")
    void SaveAudioVisualTest() {
        AudioVisual audioVisual = new AudioVisual();
        Genero genero = new Genero();
        audioVisual.setId(1);
        audioVisual.setId(id);
        audioVisual.setTitulo("Mickey");
        audioVisual.setEstado(true);
        audioVisual.setImagen("audioV.img");
        audioVisual.setFechaCreacion(Calendar.getInstance());
        AudioVisual actual = audioVisualRepository.save(audioVisual);
        assertTrue(actual instanceof AudioVisual, "no se pudo crear la nueva entidad");
    }

}