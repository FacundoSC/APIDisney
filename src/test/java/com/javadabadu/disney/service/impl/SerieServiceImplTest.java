package com.javadabadu.disney.service.impl;

import com.javadabadu.disney.data.PersonajeData;
import com.javadabadu.disney.exception.ExceptionBBDD;
import com.javadabadu.disney.models.dto.response.SerieResponseDTO;
import com.javadabadu.disney.models.entity.AudioVisual;
import com.javadabadu.disney.models.entity.Genero;
import com.javadabadu.disney.models.entity.Personaje;
import com.javadabadu.disney.models.entity.Serie;
import com.javadabadu.disney.models.mapped.ModelMapperDTO;
import com.javadabadu.disney.repository.PersonajeRepository;
import com.javadabadu.disney.repository.SerieRepository;
import com.javadabadu.disney.service.SerieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SerieServiceImplTest {


    @Autowired
    private SerieService serieService;

    @MockBean
    private SerieRepository serieRepository;
    @MockBean
    private PersonajeRepository personajeRepository;

    @MockBean
    private ModelMapperDTO modelMapperDTO;

    @Test
    void findAll() throws ExceptionBBDD {
        Serie serieUno = new Serie(1, "Serie uno", "/imagen/serie/uno", new Genero(), new ArrayList<>(), (byte) 10, (byte) 100);
        Serie serieDos = new Serie(2, "Serie dos", "/imagen/serie/dos", new Genero(), new ArrayList<>(), (byte) 20, (byte) 200);

        SerieResponseDTO serieUnoResponseDTO = getSerieResponseDTO(1, "Serie uno", "/imagen/serie/uno", 10, 100);

        SerieResponseDTO serieDosResponseDTO = getSerieResponseDTO(2, "Serie dos", "/imagen/serie/dos", 20, 200);

        List<AudioVisual> series = Arrays.asList(serieUno, serieDos);

        when(serieRepository.findAll()).thenReturn(series);
        when(modelMapperDTO.serieToResponseDTO((Serie) serieUno)).thenReturn(serieUnoResponseDTO);
        when(modelMapperDTO.serieToResponseDTO((Serie) serieDos)).thenReturn(serieDosResponseDTO);

        assertNotNull(serieService.findAll());

        assertTrue(serieService.findAll().size() > 1);


        assertEquals(1, serieService.findAll().get(0).getId());
        assertEquals("Serie uno", serieService.findAll().get(0).getTitulo());

        assertEquals(2, serieService.findAll().get(1).getId());
        assertEquals("Serie dos", serieService.findAll().get(1).getTitulo());

        verify(serieRepository, times(6)).findAll();
        verify(modelMapperDTO, times(6)).serieToResponseDTO((Serie) serieUno);
        verify(modelMapperDTO, times(6)).serieToResponseDTO((Serie) serieDos);


    }

    private SerieResponseDTO getSerieResponseDTO(int id, String Serie_uno, String imagen, int x, int x1) {
        SerieResponseDTO serieUnoResponseDTO = new SerieResponseDTO();
        serieUnoResponseDTO.setId(id);
        serieUnoResponseDTO.setTitulo(Serie_uno);
        serieUnoResponseDTO.setImagen(imagen);
        serieUnoResponseDTO.setFechaCreacion(Calendar.getInstance());
        serieUnoResponseDTO.setGeneroNombre("Terror");
        serieUnoResponseDTO.setTemporadas((byte) x);
        serieUnoResponseDTO.setCapitulos((byte) x1);
        return serieUnoResponseDTO;
    }

    @Test
    void findById() {
        Serie serieUno = new Serie(1, "Serie uno", "/imagen/serie/uno", new Genero(), new ArrayList<>(), (byte) 10, (byte) 100);
        SerieResponseDTO serieUnoResponseDTO = getSerieResponseDTO(1, "Serie uno", "/imagen/serie/uno", 10, 100);
        when(modelMapperDTO.serieToResponseDTO(serieUno)).thenReturn(serieUnoResponseDTO);

    }

    @Test
    void existsById() {
        when(serieRepository.existsById(1)).thenReturn(true);
        assertTrue(serieRepository.existsById(1), String.valueOf(false));


    }

    @Test
    // El service no lanza excepcion !
    void lastValueId(){
        when(serieRepository.lastValueId()).thenReturn(5);
        assertEquals(5, serieRepository.lastValueId());
    }

    @Test
    void save() {
        Serie serieUno = new Serie(1, "Serie uno", "/imagen/serie/uno", new Genero(), new ArrayList<>(), (byte) 10, (byte) 100);
        SerieResponseDTO serieUnoResponseDTO = getSerieResponseDTO(1, "Serie uno", "/imagen/serie/uno", 10, 100);
        when(serieRepository.save(any())).thenReturn(serieUno);
        when((modelMapperDTO.serieToResponseDTO(any()))).thenReturn(serieUnoResponseDTO);
        assertEquals(1, serieRepository.save(serieUno).getId());
        assertEquals("Serie uno", serieRepository.save(serieUno).getTitulo());
    }


    @Test
    void softDelete() throws ExceptionBBDD {
        //when(personajeRepository.softDelete(any(Integer.class))).thenReturn(true);
        when(serieRepository.softDelete(1)).thenReturn(true);
        assertEquals("Se elimino la serie seleccionada", serieService.softDelete(1));
        when(serieRepository.softDelete(2)).thenReturn(false);
        assertThrows(ExceptionBBDD.class, () -> {
            serieService.softDelete(2);
        });
    }

    @Test
    void findSerie() {
        Serie serieUno = new Serie(1, "Serie uno", "/imagen/serie/uno", new Genero(), new ArrayList<>(), (byte) 10, (byte) 100);
        Optional<AudioVisual> serieOptional = Optional.of(serieUno);
        when(serieRepository.findById(1)).thenReturn(serieOptional);
        assertNotNull(serieRepository.findById(1));
        assertEquals(1, serieRepository.findById(1).get().getId());
        assertEquals("Serie uno", serieRepository.findById(1).get().getTitulo());
        assertTrue(serieOptional.get() instanceof Serie, "id.not.serie");

    }

    @Test
    void joinPersonajes() {

        List<Integer> idPersonajes = new ArrayList<>();
        idPersonajes.add(1);
        idPersonajes.add(2);
        List<Personaje>listaPersonaje = PersonajeData.crearListaPersonajes();
        Serie serieUno = new Serie(1, "Serie uno", "/imagen/serie/uno", new Genero(), listaPersonaje, (byte) 10, (byte) 100);
        serieUno.setPersonajes(listaPersonaje);
        when(personajeRepository.getByIdIn(any())).thenReturn(listaPersonaje);
        when(serieRepository.save(any())).thenReturn(serieUno);
        assertEquals(listaPersonaje, serieRepository.save(serieUno).getPersonajes());
        assertEquals("Personaje nombre uno", serieRepository.save(serieUno).getPersonajes().get(0).getNombre());
           }


}