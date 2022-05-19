package com.javadabadu.disney.service.impl;

import com.javadabadu.disney.data.PersonajeData;
import com.javadabadu.disney.exception.ExceptionBBDD;
import com.javadabadu.disney.models.entity.Personaje;
import com.javadabadu.disney.models.mapped.ModelMapperDTOImp;
import com.javadabadu.disney.repository.PersonajeRepository;
import com.javadabadu.disney.service.PersonajeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;

import java.util.Optional;

import static com.javadabadu.disney.data.PersonajeData.crearPersonajeUno;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class PersonajeServiceImplTest {

    @Autowired
    private PersonajeService personajeService;

    @MockBean
    private ModelMapperDTOImp mapperDTO;

    @MockBean
    private PersonajeRepository personajeRepository;

    @Test
    void findByIdTest() throws ExceptionBBDD {

        Optional<Personaje> personajeOptional = Optional.of(crearPersonajeUno());
        when(personajeRepository.findById(1)).thenReturn(personajeOptional);
        when(mapperDTO.personajeToResponseDTO(crearPersonajeUno())).thenReturn(PersonajeData.crearPersonajeDTOUno());
        assertNotNull(personajeService.findById(1));
        assertEquals(1, personajeService.findById(1).getId());
        assertEquals("Personaje nombre uno", personajeService.findById(1).getNombre());

        assertThrows(ExceptionBBDD.class, () -> {
            personajeService.findById(100);
        });
    }

    @Test
    void findAllTest() throws ExceptionBBDD {
        when(personajeRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))).thenReturn(PersonajeData.crearListaPersonajes());
        when(mapperDTO.listPersonajeToResponseDTO(PersonajeData.crearListaPersonajes())).thenReturn(PersonajeData.crearListaPersonajesDto());

        assertNotNull(personajeService.findAll());
        assertEquals(1, personajeService.findAll().get(0).getId());
        assertEquals(2, personajeService.findAll().get(1).getId());
        assertEquals("Personaje nombre uno", personajeService.findAll().get(0).getNombre());
        assertEquals("Personaje nombre dos", personajeService.findAll().get(1).getNombre());
        assertTrue(personajeService.findAll().size() > 1);

        verify(personajeRepository, times(6)).findAll(Sort.by(Sort.Direction.ASC, "id"));
        //verify(mapperDTO, times(6)).personajeToResponseDTO(any(Personaje.class));
    }

    @Test
    void saveTest() throws ExceptionBBDD {
        when(personajeRepository.save(any())).thenReturn(PersonajeData.crearPersonajeUno());
        when(mapperDTO.personajeToResponseDTO(any())).thenReturn(PersonajeData.crearPersonajeDTOUno());

        assertEquals("Personaje nombre uno", personajeService.save(crearPersonajeUno()).getNombre());
        assertEquals(1, personajeService.save(crearPersonajeUno()).getId());
        verify(personajeRepository, times(2)).save(any());
        verify(mapperDTO, times(2)).personajeToResponseDTO(any());
    }

    @Test
    void softDeleteTest() throws ExceptionBBDD {
        //when(personajeRepository.softDelete(any(Integer.class))).thenReturn(true);
        when(personajeRepository.softDelete(1)).thenReturn(true);

        assertEquals("Se elimino el personaje seleccionado", personajeService.softDelete(1));

        assertThrows(ExceptionBBDD.class, () -> {
            personajeService.softDelete(2);
        });
    }

    @Test
    void exitsByIdTest() throws ExceptionBBDD {
        when(personajeRepository.existsById(1)).thenReturn(true);

        assertTrue(personajeService.existsById(1));
        assertFalse(personajeService.existsById(2000));
    }

    @Test
    void lastValueTest() throws ExceptionBBDD {
        when(personajeRepository.lastValueId()).thenReturn(10);
        assertEquals(10, personajeService.lastValueId());

        when(personajeRepository.lastValueId()).thenReturn(-1);
        assertThrows(ExceptionBBDD.class, () -> {
            personajeService.lastValueId();
        });
    }

    @Test
    void filterCharacterFindByNombreTest() throws ExceptionBBDD {
        when(personajeRepository.findByNombre("Personaje nombre uno")).thenReturn(PersonajeData.crearListaPersonajes());
        when(mapperDTO.listPersonajeToResponseDTO(PersonajeData.crearListaPersonajes())).thenReturn(PersonajeData.crearListaPersonajesDto());
        assertNotNull(personajeService.filterCharacter("Personaje nombre uno", null, null));
        assertTrue(personajeService.filterCharacter("Personaje nombre uno", null, null).size() > 0);
    }

    @Test
    void filterCharacterFindByEdadTest() throws ExceptionBBDD {
        when(personajeRepository.findByEdad(10)).thenReturn(PersonajeData.crearListaPersonajes());
        when(mapperDTO.listPersonajeToResponseDTO(PersonajeData.crearListaPersonajes())).thenReturn(PersonajeData.crearListaPersonajesDto());

        assertNotNull(personajeService.filterCharacter(null, 10, null));
        assertTrue(personajeService.filterCharacter(null, 10, null).size() > 0);
    }

    @Test
    void filterCharacterFindByEdadYNombreTest() throws ExceptionBBDD {
        when(personajeRepository.findByEdadYNombre("Personaje nombre uno", 20)).thenReturn(PersonajeData.crearListaPersonajes());
        when(mapperDTO.listPersonajeToResponseDTO(PersonajeData.crearListaPersonajes())).thenReturn(PersonajeData.crearListaPersonajesDto());

        assertNotNull(personajeService.filterCharacter("Personaje nombre uno", 20, null));
        assertTrue(personajeService.filterCharacter("Personaje nombre uno", 20, null).size() > 0);
    }

    @Test
    void filterCharacterFindByMovieIdTest() throws ExceptionBBDD {
        when(personajeRepository.findByMovieId(1)).thenReturn(PersonajeData.crearListaPersonajes());
        when(mapperDTO.listPersonajeToResponseDTO(PersonajeData.crearListaPersonajes())).thenReturn(PersonajeData.crearListaPersonajesDto());
        assertNotNull(personajeService.filterCharacter(null, null, 1));
        assertTrue(personajeService.filterCharacter(null, null, 1).size() > 0);
    }
}