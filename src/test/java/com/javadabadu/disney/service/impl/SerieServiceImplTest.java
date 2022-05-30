package com.javadabadu.disney.service.impl;

import com.javadabadu.disney.exception.ExceptionBBDD;
import com.javadabadu.disney.models.dto.response.SerieResponseDTO;
import com.javadabadu.disney.models.entity.AudioVisual;
import com.javadabadu.disney.models.entity.Genero;
import com.javadabadu.disney.models.entity.Serie;
import com.javadabadu.disney.models.mapped.ModelMapperDTO;
import com.javadabadu.disney.repository.SerieRepository;
import com.javadabadu.disney.service.SerieService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SerieServiceImplTest {

    @Autowired
    private SerieService serieService;

    @MockBean
    private SerieRepository serieRepository;

    @MockBean
    private ModelMapperDTO modelMapperDTO;

   /* @Test
    void findAllTest() throws ExceptionBBDD {
        Serie serieUno = new Serie(1, "Serie uno", "/imagen/serie/uno", new Genero(), new ArrayList<>(), (byte) 10, (byte) 100);
        Serie serieDos = new Serie(2, "Serie dos", "/imagen/serie/dos", new Genero(), new ArrayList<>(), (byte) 20, (byte) 200);

     //   SerieResponseDTO serieUnoResponseDTO = new SerieResponseDTO(1, "Serie uno", "/imagen/serie/uno", null, null, null, null );
       // SerieResponseDTO serieDosResponseDTO = new SerieResponseDTO(2, "Serie dos", "/imagen/serie/dos", null, null, null, null );

        List<AudioVisual> series = Arrays.asList(serieUno, serieDos);

        when(serieRepository.findAll()).thenReturn(series);
        when(modelMapperDTO.serieToResponseDTO((Serie) serieUno)).thenReturn(serieUnoResponseDTO);
        when(modelMapperDTO.serieToResponseDTO((Serie) serieDos)).thenReturn(serieDosResponseDTO);

        assertNotNull(serieService.findAll());
        assertTrue(serieService.findAll().size()>1);

        assertEquals(1, serieService.findAll().get(0).getId());
        assertEquals("Serie uno", serieService.findAll().get(0).getTitulo());

        assertEquals(2, serieService.findAll().get(1).getId());
        assertEquals("Serie dos", serieService.findAll().get(1).getTitulo());

        verify(serieRepository, times(6)).findAll();
        verify(modelMapperDTO, times(6)).serieToResponseDTO((Serie) serieUno);
        verify(modelMapperDTO, times(6)).serieToResponseDTO((Serie) serieDos);
    }*/
}