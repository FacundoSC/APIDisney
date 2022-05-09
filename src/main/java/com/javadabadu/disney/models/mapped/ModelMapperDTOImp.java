package com.javadabadu.disney.models.mapped;

import com.javadabadu.disney.models.dto.*;
import com.javadabadu.disney.models.entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ModelMapperDTOImp implements ModelMapperDTO {
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public PeliculaResponseDTO peliculaToResponseDTO(Pelicula pelicula) {
        PeliculaResponseDTO peliculaDTO = modelMapper.map(pelicula, PeliculaResponseDTO.class);
        return peliculaDTO;
    }

    @Override
    public PersonajeResponseDTO personajeToResponseDTO(Personaje personaje) {
        PersonajeResponseDTO personajeDTO = modelMapper.map(personaje, PersonajeResponseDTO.class);
        return personajeDTO;
    }

    @Override
    public Personaje personajeResponseDTOtoPersonaje(PersonajeResponseDTO personajeDTO) {

      /*  Personaje personaje = new Personaje();
        personaje.setEdad(personajeDTO.getEdad());
        personaje.setNombre(personajeDTO.getNombre());
        personaje.setImagen(personajeDTO.getImagen());
        personaje.setHistoria(personajeDTO.getHistoria());
        personaje.setPeso(personajeDTO.getPeso());
        personaje.setTipo(personajeDTO.getTipo());
        personaje.setEstado(personajeDTO.getEstado());
        personaje.setId(personajeDTO.getId());
        personaje.setAudioVisual(listAudiovisualResponseToAudiovisual(personajeDTO.getAudioVisual()));*/

        Personaje personaje = modelMapper.map(personajeDTO, Personaje.class);


        return personaje;
    }

    @Override
    public List<PersonajeResponseDTO> listPersonajeToResponseDTO(List<Personaje> listPersonaje) {
        return listPersonaje.stream()
                .map(this::personajeToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GeneroResponseDTO generoToResponseDTO(Genero genero) {
        GeneroResponseDTO generoDTO = modelMapper.map(genero, GeneroResponseDTO.class);
        return generoDTO;
    }

    @Override
    public List<GeneroResponseDTO> listGeneroToResponseDTO(List<Genero> listGenero) {
        return listGenero.stream()
                .map(this::generoToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AudioVisualResponseDTO audioVisualToResponseDTO(AudioVisual audiovisual) {
        AudioVisualResponseDTO audioVisualRespondeDTO = modelMapper.map(audiovisual, AudioVisualResponseDTO.class);
        return audioVisualRespondeDTO;
    }

    @Override
    public List<AudioVisualResponseDTO> listAudiovisualToResponseDTO(List<AudioVisual> listAudiovisual) {
        return listAudiovisual.stream()
                .map(this::audioVisualToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AudioVisual audioVisualResponseToAudiovisual(AudioVisualResponseDTO audiovisualDTO) {
        AudioVisual audiovisual = modelMapper.map(audiovisualDTO, AudioVisual.class);
        return audiovisual;
    }

    @Override
    public List<AudioVisual> listAudiovisualResponseToAudiovisual(List<AudioVisualResponseDTO> listAudiovisual) {
        return listAudiovisual.stream()
                .map(this::audioVisualResponseToAudiovisual)
                .collect(Collectors.toList());
    }

    @Override
    public SerieResponseDTO serieToResponseDTO(Serie serie) {
        SerieResponseDTO serieDTO = modelMapper.map(serie, SerieResponseDTO.class);
        return serieDTO;
    }

    @Override
    public Pelicula responseDtoToPelicula(PeliculaResponseDTO peliculaResponseDTO) {
        return modelMapper.map(peliculaResponseDTO, Pelicula.class);
    }
}
