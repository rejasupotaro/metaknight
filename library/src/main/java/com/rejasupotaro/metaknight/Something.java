package com.rejasupotaro.metaknight;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Something {
    private Map<String, Object> fieldMap = new HashMap<>();

    public Something set(String name, Object value) {
        fieldMap.put(name, value);
        return this;
    }

    public <T> T realize(Class<T> clazz) {
        try {
            T thing = clazz.newInstance();

            for (String name : fieldMap.keySet())
                try {
                    Field field = clazz.getDeclaredField(name);
                    field.setAccessible(true);
                    field.set(thing, fieldMap.get(name));
                } catch (NoSuchFieldException e) {
                    throw new RuntimeException(e);
                }

            return thing;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
