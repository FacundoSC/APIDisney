package com.javadabadu.disney.service;

import com.javadabadu.disney.exception.ExceptionBBDD;
import org.springframework.hateoas.Link;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface BaseServiceWrite<E, O, I> {
    E getEntitySave(E entity, I id) throws ExceptionBBDD;
    E getEntity(I id, Map<String, Object> propiedades) throws ExceptionBBDD;

}
