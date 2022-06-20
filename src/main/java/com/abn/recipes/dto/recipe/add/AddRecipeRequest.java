package com.abn.recipes.dto.recipe.add;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddRecipeRequest {


    private String name;

    private String comment;

    @Min(1)
    private Long serveNumber;

    private Long instructionId;

    private Set<AddRecipeDetailRequest> recipeDetails;

}
