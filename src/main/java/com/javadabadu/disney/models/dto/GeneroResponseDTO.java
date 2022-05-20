package com.javadabadu.disney.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GeneroResponseDTO {

    private Integer id;

    private String nombre;

    private String imagen;

    private Boolean alta;
}
