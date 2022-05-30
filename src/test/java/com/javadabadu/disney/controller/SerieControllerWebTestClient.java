package com.javadabadu.disney.controller;

import com.javadabadu.disney.models.dto.request.GeneroRequestDTO;
import com.javadabadu.disney.models.dto.request.SerieRequestDTO;
import com.javadabadu.disney.models.dto.response.GeneroResponseDTO;
import com.javadabadu.disney.models.mapped.ModelMapperDTO;
import com.javadabadu.disney.repository.GeneroRepository;
import com.javadabadu.disney.repository.SerieRepository;
import com.javadabadu.disney.util.Uri;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SerieControllerWebTestClient {

    @Autowired
    private WebTestClient client;
    @Autowired
    private GeneroRepository generoRepository;
    @Autowired
    private SerieRepository serieRepository;
    @Autowired
    private ModelMapperDTO mm ;
    @Test
    void findAll() {
        client.get()
                .uri(Uri.SERIES + "/")
                .exchange()
                .expectStatus().isOk()
                //.expectHeader().contentType(MediaType.toString("application/hal+json"))
                .expectBody()
                .jsonPath("$._embedded.serieResponseDTOList[0].id").isEqualTo(1)
                .jsonPath("$._embedded.serieResponseDTOList[0].titulo").isEqualTo("Titulo uno")
                .jsonPath("$._embedded.serieResponseDTOList[1].id").isEqualTo(2)
                .jsonPath("$._embedded.serieResponseDTOList[1].titulo").isEqualTo("Titulo dos")
        ;
    }

    @Test
    void findById() {
        client.get()
                .uri(Uri.SERIES + "/1")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.titulo").isEqualTo("Titulo uno")
                .jsonPath("$.id").isEqualTo(1);
    }

    @Test
    void lastId() {
        client.post()
                .uri(Uri.SERIES + "/")
                .exchange()
                .expectStatus().isCreated()
                         ;
    }

   @Test
    void crear() {
      Byte num=10;
      GeneroRequestDTO generoRequestDTO = new GeneroRequestDTO();
      generoRequestDTO.setId(1);
      generoRequestDTO.setNombre("terror");
      generoRequestDTO.setImagen("imagen/terror");
       SerieRequestDTO serieRequestDTO= new SerieRequestDTO();
       serieRequestDTO.setTitulo("Titulo cuatro");
       serieRequestDTO.setImagen("imagen/cuatro");
       serieRequestDTO.setGenero(generoRequestDTO);
       serieRequestDTO.setCapitulos(num);
       serieRequestDTO.setTemporadas(num);


       client.put()
               .uri(Uri.SERIES + "/4")
               .contentType(MediaType.APPLICATION_JSON)
               .bodyValue(serieRequestDTO)
               .exchange()
               .expectStatus().isOk()
               .expectBody()
               .jsonPath("$.id").isEqualTo(4)
               .jsonPath("$.titulo").isEqualTo("Titulo cuatro");
    }

    @Test
    void update() {

        Map<String, Object> propiedades = new HashMap<>();
        propiedades.put("titulo", "Titulo 4 modificado con patcht");

        client.patch()
                .uri(Uri.SERIES + "/4")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(propiedades)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(4)
                .jsonPath("$.titulo").isEqualTo("Titulo 4 modificado con patcht");
    }

    @Test
    void delete() {
        client.delete()
                .uri(Uri.SERIES + "/4")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath(".message").isEqualTo("Se elimino la serie seleccionada");
    }
}