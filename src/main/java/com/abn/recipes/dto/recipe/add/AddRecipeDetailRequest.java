package com.abn.recipes.dto.recipe.add;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddRecipeDetailRequest {

    @Min(1)
    private Long ingredientAmount;
    private Long ingredientId;

}
