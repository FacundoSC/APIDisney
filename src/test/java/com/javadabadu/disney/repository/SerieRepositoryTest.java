package com.javadabadu.disney.repository;

import com.javadabadu.disney.models.entity.AudioVisual;
import com.javadabadu.disney.models.entity.Genero;
import com.javadabadu.disney.models.entity.Serie;
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
class SerieRepositoryTest {
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

    @DisplayName("Validar referencia no nula serieRepository")
    @Test
    @Order(10)
    void serieRepositoryNotNullTest() {
        assertNotNull(serieRepository, "la referencia al  repositorio Serie es null");
    }

    @DisplayName("Validar obtencion del proximo id de Serie")
    @Test
    @Order(2)
    @Tag("NextID")
    void nextIdNotNullTest() {
        assertTrue(id instanceof Integer, "no es posible obtener el ultimo identificador");
    }

    @DisplayName("Validar borrado de un an Serie")
    @Test
    @Order(3)
    @Tag("lastId")
    void softDeleteTest() {
        assertEquals(true, serieRepository.softDelete(id));
    }

    @DisplayName("Validar la existencia de una Serie")
    @Test
    @Order(4)
    @Tag("LastId")
    void existSerieByIdTest() {
        assertTrue(serieRepository.existsById(id), "no existe Serie con ese identificador");
    }


    @DisplayName("Validar la no existencia de una Serie")
    @Test
    @Order(5)
    @Tag("NextID")
    void notExistSerieByIdTest() {
        assertFalse(serieRepository.existsById(id), "Ya existe Serie con ese identificador");
    }

    @DisplayName("Validar el encontrar Serie  por Id")
    @Test
    @Order(6)
    void findSerieForIdTest() {
        Optional<AudioVisual> audiovisual = serieRepository.findById(29);
        assertTrue(audiovisual.isPresent(), " no se encontro la Serie buscada por id");
        assertTrue(audiovisual.get() instanceof Serie, "El id encontrado no es una Serie");

    }

    @DisplayName("Validar el obtener todas los AudioVisuales")
    @Test
    @Order(8)
    void getAllAudioVisualTest() {
        List<AudioVisual> series = serieRepository.findAll();
        assertFalse(series.isEmpty(), "no se encuentran elementos  en la  lista");
    }

    @DisplayName("Validar el guardar una Serie")
    @Test
    @Order(1)
    @Tag("NextID")
    void SaveSerieTest() {
        Serie serie = new Serie();
        Genero genero = new Genero();
        genero.setId(1);
        serie.setId(id);
        serie.setTitulo("Mickey");
        serie.setEstado(true);
        serie.setGenero(genero);
        serie.setCapitulos((byte) 12);
        serie.setTemporadas((byte) 2);
        serie.setImagen("serie.img");
        serie.setFechaCreacion(Calendar.getInstance());
        Serie actual = serieRepository.save(serie);
        assertTrue(actual instanceof Serie, "no se pudo crear la nueva entidad");
    }
}