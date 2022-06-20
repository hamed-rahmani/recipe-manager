package com.abn.recipes.dto.recipe.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDetailDto {

    private Long id;
    private Long ingredientId;
    private String ingredientName;
    private Long ingredientAmount;

}
