package com.javadabadu.disney.data;

import com.javadabadu.disney.models.dto.GeneroResponseDTO;
import com.javadabadu.disney.models.entity.Genero;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeneroData {

    public static Genero crearPrimerGenero() {
        return new Genero(1, "Terror", "Imagen/Terror", true, new ArrayList<>());
    }

    public static Genero crearSegundoGenero() {
        return new Genero(2, "Comedia", "Imagen/Comedia", true, new ArrayList<>());
    }

    public static List<Genero> crearListaGeneros() {
        return Arrays.asList(crearPrimerGenero(), crearSegundoGenero());
    }

    public static GeneroResponseDTO crearPrimerGeneroDTO() {
        return new GeneroResponseDTO(1, "Terror", "Imagen/Terror", true);
    }

    public static GeneroResponseDTO crearSegundoGeneroDTO() {
        return new GeneroResponseDTO(2, "Comedia", "Imagen/Comedia", true);
    }

    public static List<GeneroResponseDTO> crearListaGenerosDto() {
        return Arrays.asList(crearPrimerGeneroDTO(), crearSegundoGeneroDTO());
    }
}
