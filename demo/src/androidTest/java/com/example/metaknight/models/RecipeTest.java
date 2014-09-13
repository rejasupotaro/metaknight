package com.example.metaknight.models;

import android.test.AndroidTestCase;

import com.rejasupotaro.metaknight.ClassUtils;
import com.rejasupotaro.metaknight.PackageScanner;
import com.rejasupotaro.metaknight.Something;

public class RecipeTest extends AndroidTestCase {
    public void testInstantiation() {
        Recipe recipe = new Something()
                .set("id", 1)
                .set("name", "Monkey Bread")
                .realize(Recipe.class);
        assertEquals(1, recipe.getId());
        assertEquals("Monkey Bread", recipe.getName());
    }

    public void testOverwriteField() {
        Recipe recipe = new Recipe();
        ClassUtils.overwriteField(recipe, "name", "Perfect Salmon");
        assertEquals("Perfect Salmon", recipe.getName());
        ClassUtils.overwriteField(recipe, "name", "Texas Crab Salad");
        assertEquals("Texas Crab Salad", recipe.getName());
    }

    public void testIsSubClassOf() {
        ClassUtils.isSubclassOf(Recipe.class, Model.class);
    }
}
