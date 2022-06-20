package com.abn.recipes.dto.recipe.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDto {

    private Long id;

    private String name;

    private String comment;

    private Long serveNumber;

    private String userName;

    private Long instructionId;

    private String instructionName;

    private Set<RecipeDetailDto> recipeDetails;

}
