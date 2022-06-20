package com.abn.recipes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDto {


    private Long id;

    private String name;

    private Boolean vegetable;

}
