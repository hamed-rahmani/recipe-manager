package com.abn.recipes.service;

import com.abn.recipes.domain.*;
import com.abn.recipes.dto.recipe.add.AddRecipeDetailRequest;
import com.abn.recipes.dto.recipe.add.AddRecipeRequest;
import com.abn.recipes.dto.recipe.update.UpdateRecipeDetailRequest;
import com.abn.recipes.dto.recipe.update.UpdateRecipeRequest;
import com.abn.recipes.exception.NotFoundException;
import com.abn.recipes.repository.IngredientRepository;
import com.abn.recipes.repository.InstructionRepository;
import com.abn.recipes.repository.RecipeRepository;
import com.abn.recipes.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecipeServiceImp implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final IngredientRepository ingredientRepository;
    private final InstructionRepository instructionRepository;

    @Override
    public List<Recipe> findByUserIdIsNull() {
        return recipeRepository.findByUserIdIsNull();
    }

    @Override
    public List<Recipe> findByUserId(Long userId) {
        return recipeRepository.findByUserId(userId);
    }

    @Override
    public void add(Long userId, AddRecipeRequest addRecipeRequest) throws Exception {

        User user = getUser(userId);
        Instruction instruction = getInstruction(addRecipeRequest.getInstructionId());

        boolean vegetable = true;
        Set<RecipeDetail> recipeDetails = new HashSet<>();

        for (AddRecipeDetailRequest rd : addRecipeRequest.getRecipeDetails()) {
            RecipeDetail recipeDetail = new RecipeDetail();
            recipeDetail.setIngredientAmount(rd.getIngredientAmount());
            Ingredient ingredient = getIngredient(rd.getIngredientId());
            recipeDetail.setIngredient(ingredient);
            if (!ingredient.getVegetable())
                vegetable = false;
            recipeDetails.add(recipeDetail);
        }

        Recipe recipe = new Recipe();
        recipe.setComment(addRecipeRequest.getComment());
        recipe.setName(addRecipeRequest.getName());
        recipe.setServeNumber(addRecipeRequest.getServeNumber());
        recipe.setVegetable(vegetable);
        recipe.setUser(user);
        recipe.setInstruction(instruction);
        recipe.setRecipeDetails(recipeDetails);

        recipeRepository.save(recipe);
    }

    @Override
    public void delete(Long userId, Long recipeId) throws Exception {

        getRecipe(userId, recipeId);

        recipeRepository.deleteById(recipeId);

    }

    @Override
    public void update(Long userId, Long recipeId, UpdateRecipeRequest updateRecipeRequest) throws Exception {

        Recipe recipe = getRecipe(userId, recipeId);

        if (recipe.getInstruction().getId() != null && recipe.getInstruction().getId() != updateRecipeRequest.getInstructionId())
            recipe.setInstruction(getInstruction(updateRecipeRequest.getInstructionId()));


        boolean vegetable = true;

        Set<RecipeDetail> recipeDetails = new HashSet<>();

        for (UpdateRecipeDetailRequest rd : updateRecipeRequest.getRecipeDetails()) {

            RecipeDetail recipeDetail = new RecipeDetail();

            if (rd.getId() == null) { //new
                recipeDetail.setIngredientAmount(rd.getIngredientAmount());
                Ingredient ingredient = getIngredient(rd.getIngredientId());
                recipeDetail.setIngredient(ingredient);
                if (!ingredient.getVegetable())
                    vegetable = false;
            } else {
                recipeDetail = recipe.getRecipeDetails().stream().filter(d -> d.getId() == rd.getId()).findAny().orElseThrow(() -> new NotFoundException("There is no RecipeDetail with provided Id"));
                if (recipeDetail.getIngredient().getId() != rd.getIngredientId()) {
                    Ingredient ingredient = getIngredient(rd.getIngredientId());
                    recipeDetail.setIngredient(ingredient);
                    if (!ingredient.getVegetable())
                        vegetable = false;
                }
                if (rd.getIngredientAmount() != null)
                    recipeDetail.setIngredientAmount(rd.getIngredientAmount());

            }
            recipeDetails.add(recipeDetail);
        }
        if (updateRecipeRequest.getComment() != null && !updateRecipeRequest.getComment().isEmpty())
            recipe.setComment(updateRecipeRequest.getComment());
        if (updateRecipeRequest.getName() != null && !updateRecipeRequest.getName().isEmpty())
            recipe.setName(updateRecipeRequest.getName());
        if (updateRecipeRequest.getServeNumber() != null)
            recipe.setServeNumber(updateRecipeRequest.getServeNumber());

        recipe.setVegetable(vegetable);

        recipe.getRecipeDetails().clear();
        recipe.getRecipeDetails().addAll(recipeDetails);

        recipeRepository.save(recipe);
    }

    @Override
    public List<Recipe> findBySearchCriteria(Specification<Recipe> spec) {

        List<Recipe> recipes = recipeRepository.findAll(spec);
        return recipes.stream().distinct().collect(Collectors.toList());
    }

    private Instruction getInstruction(Long instructionId) throws Exception {
        return instructionRepository.findById(instructionId).orElseThrow(() -> new NotFoundException("There is no instruction with provided instructionId."));
    }

    private Ingredient getIngredient(Long ingredientId) throws Exception {
        return ingredientRepository.findById(ingredientId).orElseThrow(() -> new NotFoundException("There is no ingredient with provided ingredientId."));
    }

    private Recipe getRecipe(Long userId, Long recipeId) throws Exception {
        return recipeRepository.findByIdAndUserId(recipeId, userId).orElseThrow(() -> new NotFoundException("There is no Recipe with this information for current user."));
    }

    private User getUser(Long userId) throws Exception {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("There is no user with provided userId."));
    }
}
