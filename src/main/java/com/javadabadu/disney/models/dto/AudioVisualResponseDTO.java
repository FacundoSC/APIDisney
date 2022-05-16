package com.javadabadu.disney.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Calendar;

@Data
public class AudioVisualResponseDTO {
    private Integer id;

    private String titulo;

    private String imagen;

    private Boolean estado;

    private Calendar fechaCreacion;

    @JsonProperty(value="genero")
    private GeneroResponseDTO genero;
}
