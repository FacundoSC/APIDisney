package com.javadabadu.disney.repository;

import com.javadabadu.disney.models.entity.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Calendar;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PeliculaRepositoryTest {
    @Autowired
    private PeliculaRepository peliculaRepository;
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
            id = peliculaRepository.lastValueId();
            if (tag.get().equals("LastId"))
                id -= 1;
        }
    }

    @DisplayName("Validar referencia no nula a repositorio.")
    @Test
    @Order(1)
    void peliculaRepositoryNotNullTest() {

        assertNotNull(peliculaRepository, "la referencia al repositorio es nula");
    }

    @DisplayName("Validar obtencion del proximo id.")
    @Test
    @Order(2)
    @Tag("NextID")
    void nextIdNotNullTest() {
        assertNotNull(id, "no es posible obtener el ultimo identificador");
    }

    @DisplayName("Validar el encontrar Pelicula por Id")
    @Test
    @Order(3)
    void findPeliculaForIdTest() {
        Optional<AudioVisual> audiovisual = peliculaRepository.findById(1);
        assertTrue(audiovisual.isPresent(), "No se encontro la pelicula.");
        assertTrue(audiovisual.get() instanceof Pelicula, "El id encontrado no es una pelicula");

    }

    @DisplayName("Validar el guardar una pelicula")
    @Test
    @Order(4)
    @Tag("NextID")
    void savePeliculaTest() {
        Pelicula pelicula = new Pelicula();
        Genero genero = new Genero();
        genero.setId(1);
        pelicula.setId(id);
        pelicula.setTitulo("Mickey");
        pelicula.setEstado(true);
        pelicula.setGenero(genero);
        pelicula.setCalificacion(CalificacionPelicula.CUATRO);
        pelicula.setDuracion("90");
        pelicula.setImagen("pelicula.img");
        pelicula.setFechaCreacion(Calendar.getInstance());
        Pelicula actual = peliculaRepository.save(pelicula);
        assertTrue(actual instanceof Pelicula, "No se pudo crear la nueva entidad");
    }
    
}
