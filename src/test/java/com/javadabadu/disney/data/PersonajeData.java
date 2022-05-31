package com.javadabadu.disney.data;

import com.javadabadu.disney.models.dto.response.PersonajeResponseDTO;
import com.javadabadu.disney.models.entity.Personaje;
import com.javadabadu.disney.models.entity.TipoPersonaje;

import java.util.Arrays;
import java.util.List;

public class PersonajeData {

    public static Personaje crearPersonajeUno(){
        return new Personaje(1,"Personaje nombre uno", 10, "Personaje historia uno", "/imagen/uno", 60, TipoPersonaje.REAL);
    }
    public static Personaje crearPersonajeDos(){
        return new Personaje(2,"Personaje nombre dos", 20, "Personaje historia dos", "/imagen/dos", 60, TipoPersonaje.ANIMADO);
    }

    public static Personaje crearPersonajeTres(){
        return new Personaje(3,"Personaje nombre tres", 20, "Personaje historia tres", "/imagen/tres", 60, TipoPersonaje.ANIMADO);
    }
    public static List<Personaje> crearListaPersonajes(){
        return Arrays.asList(crearPersonajeUno(), crearPersonajeDos());
    }

    public static PersonajeResponseDTO crearPersonajeDTOUno(){
        return new PersonajeResponseDTO(1,"Personaje nombre uno", 20, "Personaje historia uno", "/imagen/uno", 60, TipoPersonaje.REAL, true);
    }

    public static PersonajeResponseDTO crearPersonajeDTODos(){
        return new PersonajeResponseDTO(2,"Personaje nombre dos", 20, "Personaje historia dos", "/imagen/dos", 60, TipoPersonaje.ANIMADO,true);
    }


    public static List<PersonajeResponseDTO> crearListaPersonajesDto(){
        return Arrays.asList(crearPersonajeDTOUno(), crearPersonajeDTODos());
    }
    public static List<Personaje> crearListaPersonajesTres(){
        return Arrays.asList(crearPersonajeUno(), crearPersonajeDos(),crearPersonajeTres());
    }
    public static List<Personaje> crearListaPersonajesUno(){
        return Arrays.asList(crearPersonajeTres());
    }
}
