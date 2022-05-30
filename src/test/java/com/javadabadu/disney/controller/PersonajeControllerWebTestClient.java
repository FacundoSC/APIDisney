package com.javadabadu.disney.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javadabadu.disney.models.entity.Personaje;
import com.javadabadu.disney.models.entity.TipoPersonaje;
import com.javadabadu.disney.util.Uri;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonajeControllerWebTestClient {

    @Autowired
    private WebTestClient client;

    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
    }

    @Test
    @Order(1)
    void findByIdTest() {
        client.get()
                .uri(Uri.PERSONAJES + "/1")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.nombre").isEqualTo("Nombre uno")
                .jsonPath("$.id").isEqualTo(1);

    }

    @Test
    @Order(2)
    void findAllTest() {
        client.get()
                .uri(Uri.PERSONAJES + "/")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$._embedded.personajeResponseDTOList[0].id").isEqualTo(1)
                .jsonPath("$._embedded.personajeResponseDTOList[0].nombre").isEqualTo("Nombre uno")
                .jsonPath("$._embedded.personajeResponseDTOList[1].id").isEqualTo(2)
                .jsonPath("$._embedded.personajeResponseDTOList[1].nombre").isEqualTo("Nombre dos")
        ;
    }

    @Test
    @Order(3)
    void saveTest() {
        Personaje personaje = new Personaje(null, "Nombre tres", 30, "Historia personaje tres", "/imagen/tres", 70, TipoPersonaje.REAL);

        client.put()
                .uri(Uri.PERSONAJES + "/3")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(personaje)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(3)
                .jsonPath("$.nombre").isEqualTo("Nombre tres");
    }

    @Test
    @Order(4)
    void updateTest() {
        Map<String, Object> propiedades = new HashMap<>();
        propiedades.put("nombre", "Nombre 3 modificado con patcht");

        client.patch()
                .uri(Uri.PERSONAJES + "/3")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(propiedades)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(3)
                .jsonPath("$.nombre").isEqualTo("Nombre 3 modificado con patcht");
    }

    @Test
    @Order(5)
    void deleteTest() {
        client.delete()
                .uri(Uri.PERSONAJES + "/3")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath(".message").isEqualTo("Se elimino el personaje seleccionado");
    }

    @Test
    @Order(6)
    void findAllFilterFindByNombreTest() {
        client.get()
                .uri(uriBuilder -> uriBuilder
                        .path(Uri.PERSONAJES + "/filter")
                        .queryParam("name", "Nombre uno")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$._embedded.personajeResponseDTOList[0].id").isEqualTo(1)
                .jsonPath("$._embedded.personajeResponseDTOList[0].nombre").isEqualTo("Nombre uno")
        ;
    }

    @Test
    @Order(7)
    void findAllFilterFindByEdadYNombreTest() {
        client.get()
                .uri(uriBuilder -> uriBuilder
                        .path(Uri.PERSONAJES + "/filter")
                        .queryParam("name", "Nombre uno")
                        .queryParam("age",20)
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$._embedded.personajeResponseDTOList[0].id").isEqualTo(1)
                .jsonPath("$._embedded.personajeResponseDTOList[0].nombre").isEqualTo("Nombre uno")
        ;
    }

    @Test
    @Order(8)
    void findAllFilterFindByEdadTest() {
        client.get()
                .uri(uriBuilder -> uriBuilder
                        .path(Uri.PERSONAJES + "/filter")
                        .queryParam("age",30)
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$._embedded.personajeResponseDTOList[0].id").isEqualTo(2)
                .jsonPath("$._embedded.personajeResponseDTOList[0].nombre").isEqualTo("Nombre dos")
        ;
    }

    @Test
    @Order(9)
    void findAllFilterFindByMovieIdTest() {
        client.get()
                .uri(uriBuilder -> uriBuilder
                        .path(Uri.PERSONAJES + "/filter")
                        .queryParam("movies",1)
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$._embedded.personajeResponseDTOList[0].id").isEqualTo(1)
                .jsonPath("$._embedded.personajeResponseDTOList[0].audioVisual[0].id").isEqualTo(1)
        ;
    }

}
