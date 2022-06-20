package com.abn.recipes.service;


import com.abn.recipes.domain.Recipe;
import com.abn.recipes.dto.recipe.add.AddRecipeRequest;
import com.abn.recipes.dto.recipe.update.UpdateRecipeRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface RecipeService {
    List<Recipe> findByUserIdIsNull();

    List<Recipe> findByUserId(Long userId);

    void add(Long userId, AddRecipeRequest addRecipeRequest) throws Exception;

    void delete(Long userId, Long recipeId) throws Exception;

    void update(Long userId, Long recipeId, UpdateRecipeRequest updateRecipeRequest) throws Exception;

    List<Recipe> findBySearchCriteria(Specification<Recipe> spec);
}
