package com.example.metaknight.models;

import java.util.ArrayList;
import java.util.List;

public class ModelClasses {
    public static final List<Class<? extends Model>> LIST = new ArrayList<Class<? extends Model>>() {{
        add(Recipe.class);
        add(Category.class);
    }};
}
