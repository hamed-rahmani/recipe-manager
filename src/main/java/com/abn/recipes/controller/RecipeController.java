package com.abn.recipes.controller;

import com.abn.recipes.config.ModelMapperConvertor;
import com.abn.recipes.domain.Recipe;
import com.abn.recipes.dto.ResponseEntityDto;
import com.abn.recipes.dto.recipe.add.AddRecipeRequest;
import com.abn.recipes.dto.recipe.get.RecipeDto;
import com.abn.recipes.dto.recipe.update.UpdateRecipeRequest;
import com.abn.recipes.dto.search.SearchCriteria;
import com.abn.recipes.dto.search.SearchDto;
import com.abn.recipes.dto.search.SpecificationBuilder;
import com.abn.recipes.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recipe")
@AllArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;
    private final ModelMapperConvertor modelMapperConvertor;

    @GetMapping
    public ResponseEntity<ResponseEntityDto> getAllRecipe() {

        List<Recipe> recipe = recipeService.findByUserIdIsNull();
        ResponseEntityDto responseEntityDto = ResponseEntityDto.success(modelMapperConvertor.convert(recipe, RecipeDto.class));

        return ResponseEntity.ok(responseEntityDto);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ResponseEntityDto> getAllRecipeByUserId(@PathVariable Long userId) {

        List<Recipe> recipe = recipeService.findByUserId(userId);
        ResponseEntityDto responseEntityDto = ResponseEntityDto.success(modelMapperConvertor.convert(recipe, RecipeDto.class));

        return ResponseEntity.ok(responseEntityDto);
    }

    @DeleteMapping("/{userId}/{recipeId}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseEntityDto> deleteRecipeByIdAndUserId(@PathVariable Long userId, @PathVariable Long recipeId) throws Exception {
        recipeService.delete(userId, recipeId);
        return ResponseEntity.ok(ResponseEntityDto.successWithoutBody());

    }

    @PostMapping("/{userId}")
    public ResponseEntity<ResponseEntityDto> addRecipe(@PathVariable Long userId, @RequestBody AddRecipeRequest addRecipeRequest) throws Exception {
        recipeService.add(userId, addRecipeRequest);
        return ResponseEntity.ok(ResponseEntityDto.successWithoutBody());

    }

    @PutMapping("/{userId}/{recipeId}")
    @ResponseStatus(value = HttpStatus.OK)
    public  ResponseEntity<ResponseEntityDto>  updateRecipe(@PathVariable Long userId, @PathVariable Long recipeId, @RequestBody UpdateRecipeRequest updateRecipeRequest) throws Exception {
        recipeService.update(userId, recipeId, updateRecipeRequest);
        return ResponseEntity.ok(ResponseEntityDto.successWithoutBody());
    }

    @PostMapping("/search")
    public ResponseEntity<ResponseEntityDto> search(@RequestBody SearchDto searchDto) {

        SpecificationBuilder builder = new SpecificationBuilder();
        List<SearchCriteria> criteriaList = searchDto.getSearchCriteriaList();
        if (criteriaList != null) {
            criteriaList.forEach(x ->
            {
                x.setDataOption(searchDto.getDataOption());
                builder.with(x);
            });
        }

        List<Recipe> recipe = recipeService.findBySearchCriteria(builder.build());
        ResponseEntityDto responseEntityDto = ResponseEntityDto.success(modelMapperConvertor.convert(recipe, RecipeDto.class));

        return ResponseEntity.ok(responseEntityDto);

    }

}
