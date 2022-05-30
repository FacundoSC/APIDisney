package com.javadabadu.disney.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.javadabadu.disney.models.entity.Genero;
import com.javadabadu.disney.util.Uri;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GeneroControllerWebTestClient {

    @Autowired
    private WebTestClient client;

    private ObjectMapper mapper;

    @BeforeEach
    void setUpTests() {
        mapper = new ObjectMapper();
    }

    @Test
    @Order(1)
    void findByIdTest() {
        client.get()
                .uri(Uri.GENEROS + "/2")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.nombre").isEqualTo("Drama")
                .jsonPath("$.id").isEqualTo(2)
                .jsonPath("$.imagen").isEqualTo("imagen/Drama");

    }

    @Test
    @Order(2)
    void findAllTest() {
        client.get()
                .uri(Uri.GENEROS + "/")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$._embedded.generoResponseDTOList[1].id").isEqualTo(2)
                .jsonPath("$._embedded.generoResponseDTOList[1].nombre").isEqualTo("Drama")
                .jsonPath("$._embedded.generoResponseDTOList[1].imagen").isEqualTo("imagen/Drama")
                .jsonPath("$._embedded.generoResponseDTOList[2].id").isEqualTo(3)
                .jsonPath("$._embedded.generoResponseDTOList[2].nombre").isEqualTo("Comedia")
                .jsonPath("$._embedded.generoResponseDTOList[2].imagen").isEqualTo("imagen/Comedia")
        ;
    }

    @Test
    @Order(3)
    void saveTest() {
        Genero genero = new Genero(null, "Psicológico", "Imagen/Psicológico", true, new ArrayList<>());

        client.put()
                .uri(Uri.GENEROS + "/13")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(genero)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(13)
                .jsonPath("$.nombre").isEqualTo("Psicológico")
                .jsonPath("$.imagen").isEqualTo("Imagen/Psicológico");
    }

    @Test
    @Order(4)
    void updateTest() {
        Map<String, Object> propiedades = new HashMap<>();
        propiedades.put("imagen", "Imagen/PsicológicoModify");
        propiedades.put("nombre", "PsicológicoModify");

        client.patch()
                .uri(Uri.GENEROS + "/12")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(propiedades)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(12)
                .jsonPath("$.nombre").isEqualTo("PsicológicoModify")
                .jsonPath("$.imagen").isEqualTo("Imagen/PsicológicoModify");
    }

    @Test
    @Order(5)
    void deleteTest() {
        client.delete()
                .uri(Uri.GENEROS + "/4")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath(".message").isEqualTo("Se elimino el genero seleccionado");
    }
}
