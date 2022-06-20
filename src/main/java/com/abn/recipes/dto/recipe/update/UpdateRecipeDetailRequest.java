package com.abn.recipes.dto.recipe.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRecipeDetailRequest {

    private Long ingredientAmount;
    private Long ingredientId;
    private Long id;

}
