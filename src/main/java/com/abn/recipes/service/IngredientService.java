package com.abn.recipes.service;

import com.abn.recipes.domain.Ingredient;
import java.util.List;

public interface IngredientService {
    List<Ingredient> findAll();
}
