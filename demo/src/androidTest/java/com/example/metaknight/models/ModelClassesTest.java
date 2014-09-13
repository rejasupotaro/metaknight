package com.example.metaknight.models;

import android.test.InstrumentationTestCase;

import com.rejasupotaro.metaknight.PackageScanner;

import java.util.List;

public class ModelClassesTest extends InstrumentationTestCase {
    public void testDefine() {
        List<Class<? extends Model>> definedClasses = ModelClasses.LIST;
        List<Class<?>> foundClasses = PackageScanner.searchSubClasses(
                getInstrumentation().getTargetContext(),
                Model.class);
        assertEquals(definedClasses.size(), foundClasses.size());
        for (Class<?> clazz : foundClasses) {
            if (!definedClasses.contains(clazz)) {
                fail(clazz.getSimpleName() + " is not defined in ModelClasses");
            }
        }
    }
}
