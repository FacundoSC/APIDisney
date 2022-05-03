package com.javadabadu.disney.controller;


import com.javadabadu.disney.exception.ExceptionBBDD;
import com.javadabadu.disney.models.dto.ResponseInfoDTO;
import com.javadabadu.disney.models.entity.Genero;
import com.javadabadu.disney.service.GeneroService;
import com.javadabadu.disney.util.PathGenero;
import com.javadabadu.disney.util.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@CrossOrigin("*")
@RequestMapping(value = Uri.GENEROS)
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable Integer id, HttpServletRequest request) {
        try {
            Genero genero = generoService.findById(id);

            return ResponseEntity.ok().body(EntityModel.of(genero, linkTo(methodOn(GeneroController.class).findById(id, request)).withSelfRel(), linkTo(methodOn(GeneroController.class).findAll(request)).withRel("Generos:")));
        } catch (ExceptionBBDD e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseInfoDTO(e.getMessage(), request.getRequestURI(), HttpStatus.NOT_FOUND.value()));
        }
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll(HttpServletRequest request) {
        List<EntityModel<Genero>> generos = generoService.findAll().stream().map(genero -> EntityModel.of(genero, linkTo(methodOn(GeneroController.class).findById(genero.getId(), request)).withSelfRel())).collect(Collectors.toList());
        return ResponseEntity.ok().body(CollectionModel.of(generos, linkTo(methodOn(GeneroController.class).findAll(request)).withSelfRel()));
    }

    @PostMapping("/")
    public ResponseEntity<?> lastId(HttpServletRequest request) {
        return ResponseEntity.created(URI.create(request.getRequestURI() + generoService.lastValueId())).body("se creo un registro");
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@PathVariable Integer id, @RequestBody Genero genero, HttpServletRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(EntityModel.of(generoService.save(genero, id), linkTo(methodOn(GeneroController.class).findById(id, request)).withSelfRel()));
        } catch (ExceptionBBDD ebd) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseInfoDTO(ebd.getMessage(), request.getRequestURI(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    @PatchMapping(path = "/{id}", consumes = "application/merge-patch+json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateCustomer(@PathVariable Integer id, @RequestBody Map<String, Object> propiedades, HttpServletRequest request) {
        try {
            Genero searchedGenero = generoService.findById(id);
            PathGenero pathGenero = new PathGenero();
            propiedades.forEach((k, v) -> {
                if (pathGenero.contains(k, searchedGenero))
                    pathGenero.parcharGenero(k, v, searchedGenero);
            });
            return ResponseEntity.status(HttpStatus.OK).body(EntityModel.of(generoService.save(searchedGenero, id), linkTo(methodOn(GeneroController.class).findById(id, request)).withSelfRel()));

        } catch (ExceptionBBDD ebd) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseInfoDTO(ebd.getMessage(), request.getRequestURI(), HttpStatus.NOT_FOUND.value()));
    }}


    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok().body(generoService.softDelete(generoService.findById(id).getId()));

    }

}
