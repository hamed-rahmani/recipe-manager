package com.abn.recipes.dto.recipe.update;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateRecipeRequest {

    private String name;

    private String comment;

    private Long serveNumber;

    private Long instructionId;

    @NotNull
    private Set<UpdateRecipeDetailRequest> recipeDetails;
}
