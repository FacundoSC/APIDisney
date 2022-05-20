package com.javadabadu.disney.data;

import com.javadabadu.disney.models.dto.GeneroResponseDTO;
import com.javadabadu.disney.models.entity.Genero;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeneroData {

    public static Genero crearGeneroUno() {
        return new Genero(1, "Terror", "Imagen/Terror", true, new ArrayList<>());
    }

    public static Genero crearGeneroDos() {
        return new Genero(2, "Comedia", "Imagen/Comedia", true, new ArrayList<>());
    }

    public static List<Genero> crearListaGeneros() {
        return Arrays.asList(crearGeneroUno(), crearGeneroDos());
    }

    public static GeneroResponseDTO crearGeneroDTOUno() {
        return new GeneroResponseDTO(1, "Terror", "Imagen/Terror", true);
    }

    public static GeneroResponseDTO crearGeneroDTODos() {
        return new GeneroResponseDTO(2, "Comedia", "Imagen/Comedia", true);
    }

    public static List<GeneroResponseDTO> crearListaGenerosDto() {
        return Arrays.asList(crearGeneroDTOUno(), crearGeneroDTODos());
    }
}
