package com.javadabadu.disney.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GeneroResponseDTO {

    private Integer id;

    private String nombre;

    private String imagen;

    private Boolean alta;
}
