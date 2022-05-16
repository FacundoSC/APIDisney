package com.javadabadu.disney.service;

import com.javadabadu.disney.exception.ExceptionBBDD;
import com.javadabadu.disney.models.dto.SerieRequestDTO;
import com.javadabadu.disney.models.dto.SerieResponseDTO;
import com.javadabadu.disney.models.entity.Serie;

public interface SerieService extends BaseServiceRead<SerieResponseDTO, Integer>,
        BaseServiceWrite<Serie, SerieResponseDTO, Integer>, BaseServiceParalelo<SerieRequestDTO, Serie, Integer> {

    SerieResponseDTO getSaveUpdateEntityRD(SerieRequestDTO requestDto,I id) throws ExceptionBBDD;

    SerieResponseDTO saveRD(SerieRequestDTO requestDto) throws ExceptionBBDD;

}
