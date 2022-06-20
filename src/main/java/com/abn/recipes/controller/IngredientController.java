package com.abn.recipes.controller;

import com.abn.recipes.domain.Ingredient;
import com.abn.recipes.dto.IngredientDto;
import com.abn.recipes.dto.ResponseEntityDto;
import com.abn.recipes.service.IngredientService;
import com.abn.recipes.config.ModelMapperConvertor;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ingredient")
@AllArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;
    private final ModelMapperConvertor modelMapperConvertor;


    @GetMapping
    public ResponseEntity<ResponseEntityDto> findAll() {

        List<Ingredient> ingredients = ingredientService.findAll();
        ResponseEntityDto responseEntityDto = ResponseEntityDto.success(modelMapperConvertor.convert(ingredients, IngredientDto.class));

        return ResponseEntity.ok(responseEntityDto);
    }

}
