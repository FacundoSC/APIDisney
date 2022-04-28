package com.javadabadu.disney.service;

import com.javadabadu.disney.models.entity.Genero;
import com.javadabadu.disney.repository.GeneroRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class GeneroServiceImpl implements GeneroService{

    @Autowired
    private GeneroRepository generoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Genero> findById(Integer id) {
        return generoRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genero> findAll() {
        return generoRepository.findAll();
    }

    @Override
    @Transactional
    public Genero save(Genero genero) {
        return generoRepository.save(genero);
    }

    @Override
    @Transactional(readOnly = true)
    public Integer lastValueId() {
        return generoRepository.lastValueId();
    }

    @Override
    @Transactional
    public Genero update(Genero genero, Integer id) {
        Genero generoEncontrado = generoRepository.findById(id).orElseThrow();
        generoEncontrado.setImagen(genero.getImagen());
        generoEncontrado.setNombre(genero.getNombre());

        return save(generoEncontrado);
    }

    @Override
    @Transactional
    public Boolean softDelete(Integer id) {
        return generoRepository.changeStatus(id);
    }

    @Override
    public Genero updateV2(Integer id, String patch) throws Exception {
        
        Genero aModificar = this.findById(id).orElseThrow(() -> new Exception());
        JSONObject cambios = new JSONObject(patch);
        String llave = null;
        String valor;

        try {
            Iterator<String> it = cambios.keys();

        while (it.hasNext()) {
            llave = it.next();
            valor = (String) cambios.get(llave);

                Field nameField = aModificar.getClass().getDeclaredField(llave);
                nameField.setAccessible(true);
                nameField.set(aModificar, valor);
            
            }
        }catch(NoSuchFieldException e) {
            throw new Exception("El campo (" +llave+ ")indicado no existe");
        }
        return this.save(aModificar);
    }

}
