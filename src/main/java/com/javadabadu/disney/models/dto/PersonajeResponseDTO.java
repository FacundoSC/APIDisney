package com.javadabadu.disney.models.dto;

import com.javadabadu.disney.models.entity.TipoPersonaje;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PersonajeResponseDTO {
    private Integer id;
    private String nombre;
    private int edad;
    private String historia;
    private String imagen;
    private float peso;
    private TipoPersonaje tipo;
    private boolean estado;
    private List<AudioVisualResponseDTO> audioVisual;

    public boolean getEstado (){
        return this.estado;
    }

    public PersonajeResponseDTO(Integer id, String nombre, int edad, String historia, String imagen, float peso, TipoPersonaje tipo, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.historia = historia;
        this.imagen = imagen;
        this.peso = peso;
        this.tipo = tipo;
        this.estado = estado;
    }
}
