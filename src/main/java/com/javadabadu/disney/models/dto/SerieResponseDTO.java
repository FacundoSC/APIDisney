package com.javadabadu.disney.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Calendar;

@Data
public class SerieResponseDTO {
    private Integer id;
    private String titulo;
    private String imagen;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Calendar fechaCreacion;
    @JsonProperty(value = "genero")
    private String generoNombre;
    private Byte temporadas;
    private Byte capitulos;
    private boolean estado =true;

    public SerieResponseDTO() {
    }

    public SerieResponseDTO(Integer id, String titulo, String imagen, Calendar fechaCreacion, String generoNombre, Byte temporadas, Byte capitulos) {
        this.id = id;
        this.titulo = titulo;
        this.imagen = imagen;
        this.fechaCreacion = fechaCreacion;
        this.generoNombre = generoNombre;
        this.temporadas = temporadas;
        this.capitulos = capitulos;
    }
}
