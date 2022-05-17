package com.javadabadu.disney.service;

import com.javadabadu.disney.exception.ExceptionBBDD;
import com.javadabadu.disney.models.dto.SerieRequestDTO;
import com.javadabadu.disney.models.dto.SerieResponseDTO;
import com.javadabadu.disney.models.entity.Serie;

import java.util.Map;

public interface SerieService extends BaseServiceRead<SerieResponseDTO, Integer>,
        BaseService<Serie, SerieResponseDTO, Integer>, BaseServiceParalelo<SerieRequestDTO, Serie, Integer> {

    SerieResponseDTO getPersistenceEntity(SerieRequestDTO serierequestDto,Integer id) throws ExceptionBBDD;
    SerieResponseDTO updatePartial(Integer id, Map<String, Object> propiedades)throws ExceptionBBDD ;

}
