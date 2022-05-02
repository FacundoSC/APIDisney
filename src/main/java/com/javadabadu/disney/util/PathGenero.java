package com.javadabadu.disney.util;

import com.javadabadu.disney.models.entity.Genero;

import java.lang.reflect.Field;

public class PathGenero {
    public void parcharGenero(String key, Object value, Genero genero) {
        try {
            Class generoClass = Class.forName(genero.getClass().toString().split(" ")[1]);
            Field field = generoClass.getDeclaredField(key);
            field.setAccessible(true);
            field.set(genero, value);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public boolean contains(String key, Genero genero) {
        Class generoClass = null;
        try {
            generoClass = Class.forName(genero.getClass().toString().split(" ")[1]);
            Field field = generoClass.getDeclaredField(key);
            return true;

        } catch (ClassNotFoundException | NoSuchFieldException e) {
            e.printStackTrace();
            return false;
        }
    }
}
