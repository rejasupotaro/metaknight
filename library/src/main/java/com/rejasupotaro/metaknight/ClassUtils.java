package com.rejasupotaro.metaknight;

import java.lang.reflect.Field;

public class ClassUtils {
    public static boolean isSubclassOf(Class<?> targetClass, Class<?> superClass) {
        if (targetClass.getSuperclass() != null) {
            return targetClass.getSuperclass().equals(superClass) || isSubclassOf(targetClass.getSuperclass(), superClass);
        }
        return false;
    }

    public static void overwriteField(Object receiver, String name, Object value) {
        try {
            Field field = receiver.getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(receiver, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
