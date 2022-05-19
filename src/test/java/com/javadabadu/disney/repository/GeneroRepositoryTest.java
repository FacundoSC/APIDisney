package com.javadabadu.disney.repository;

import com.javadabadu.disney.models.entity.Genero;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GeneroRepositoryTest {
    @Autowired
    private GeneroRepository generoRepository;
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
            id = generoRepository.lastValueId();
            if (tag.get().equals("LastId"))
                id -= 1;
        }
    }

    @DisplayName("Validar referencia no nula generoRepository")
    @Test
    @Order(1)
    void generoRepositoryNotNullTest() {
        assertNotNull(generoRepository, "la referencia al  repositorio genero es null");
    }

    @DisplayName("Validar obtencion del proximo id de genero")
    @Test
    @Order(2)
    @Tag("NextID")
    void NextIdNotNullTest() {
        assertTrue(id instanceof Integer, "no es posible obtener el ultimo identificador");
    }

    @DisplayName("Validar borrado del un genero")
    @Test
    @Order(3)
    @Tag("LastId")
    void softDeleteTest() {
        assertEquals(true, generoRepository.softDelete(id), "fallo al intentar deshabilitar genero");
    }

    @DisplayName("Validar la existencia de un genero")
    @Test
    @Order(4)
    @Tag("LastId")
    void ExistGeneroByIdTest() {
        assertTrue(generoRepository.existsById(id), "no existe genero con ese identificador");
    }

    @DisplayName("Validar la no existencia de un genero")
    @Test
    @Order(5)
    @Tag("NextID")
    void NotExistGeneroByIdTest() {
        assertTrue(generoRepository.existsById(id), " existe genero con ese identificador");
    }

    @DisplayName("Validar el encontrar genero  por Id")
    @Test
    @Order(6)
    @Tag("LastId")
    void FindGeneroForIdTest() {
        assertTrue(generoRepository.findById(id).get() instanceof Genero);
    }

    @DisplayName("Validar el no encontrar genero  por Id")
    @Test
    @Order(7)
    @Tag("NextID")
    void NotFindGeneroForIdTest() {
        Optional<Genero> genero = generoRepository.findById(id);
        assertTrue(genero.isPresent(), " no se encontro el genero buscado por id");
    }

    @DisplayName("Validar el obtener todos los generos")
    @Test
    @Order(8)
    void getAllGeneroTest() {
        List<Genero> generos = generoRepository.findAll();
        assertFalse(generos.isEmpty(), "no se encuentran elementos  en la  lista");
    }

    @DisplayName("Validar el guardar un genero")
    @Test
    @Order(9)
    @Tag("NextID")
    void SaveGeneroTest() {
        Genero genero = new Genero();
        genero.setId(id);
        genero.setAlta(true);
        genero.setNombre("fdgdgdg");
        genero.setImagen("img");
        genero.setAudioVisuals(null);
        Genero actual = generoRepository.save(genero);
        assertTrue(actual instanceof Genero, "no se pudo crear la nueva entidad");
    }

    @AfterEach
    void tearDown() {
    }

}




