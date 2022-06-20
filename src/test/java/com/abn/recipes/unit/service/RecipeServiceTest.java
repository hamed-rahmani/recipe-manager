package com.abn.recipes.unit.service;

import com.abn.recipes.domain.Ingredient;
import com.abn.recipes.domain.Instruction;
import com.abn.recipes.domain.Recipe;
import com.abn.recipes.domain.User;
import com.abn.recipes.dto.recipe.add.AddRecipeDetailRequest;
import com.abn.recipes.dto.recipe.add.AddRecipeRequest;
import com.abn.recipes.dto.recipe.update.UpdateRecipeDetailRequest;
import com.abn.recipes.dto.recipe.update.UpdateRecipeRequest;
import com.abn.recipes.repository.IngredientRepository;
import com.abn.recipes.repository.InstructionRepository;
import com.abn.recipes.repository.RecipeRepository;
import com.abn.recipes.repository.UserRepository;
import com.abn.recipes.service.RecipeServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.jpa.domain.Specification;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RecipeServiceTest {

    @Mock
    private RecipeRepository repository;
    @Mock
    private  UserRepository userRepository;
    @Mock
    private  IngredientRepository ingredientRepository;
    @Mock
    private  InstructionRepository instructionRepository;

    @InjectMocks
    private RecipeServiceImp serviceImp;

    @Test
    public void check_findByUserIdIsNull() {

        List<Recipe> recipes = new ArrayList<>();

        recipes.add(new Recipe(1L, "test", false, "test", 10L, null, null, null));
        recipes.add(new Recipe(2L, "test2", true, "test2", 20L, null, null, null));

        when(repository.findByUserIdIsNull()).thenReturn(recipes);

        List<Recipe> recipeList = serviceImp.findByUserIdIsNull();
        assertThat(recipeList.size()).isGreaterThan(0);

        verify(repository).findByUserIdIsNull();
    }

    @Test
    public void check_findByUserId() {

        List<Recipe> recipes = new ArrayList<>();

        recipes.add(new Recipe(1L, "test", false, "test", 10L, new User(1L, "test", null), null, null));
        recipes.add(new Recipe(2L, "test2", true, "test2", 20L, null, null, null));

        when(repository.findByUserId(1L)).thenReturn(recipes);

        List<Recipe> recipeList = serviceImp.findByUserId(1L);
        assertThat(recipeList.size()).isGreaterThan(0);

        verify(repository).findByUserId(1L);
    }

    @Test
    public void check_findBySearchCriteria() {

        Specification<Recipe> spec = null;
        List<Recipe> recipes = new ArrayList<>();

        recipes.add(new Recipe(1L, "test", false, "test", 10L, new User(1L, "test", null), null, null));
        recipes.add(new Recipe(2L, "test2", true, "test2", 20L, null, null, null));

        when(repository.findAll(spec)).thenReturn(recipes);

        List<Recipe> recipeList = serviceImp.findBySearchCriteria(spec);
        assertThat(recipeList.size()).isGreaterThan(0);

        verify(repository).findAll(spec);
    }

    @Test
    public void check_delete() throws Exception {

        Recipe recipe = new Recipe(1L, "test", false, "test", 10L, new User(1L, "test", null), null, null);

        when(repository.findByIdAndUserId(1L, 1L)).thenReturn(Optional.of(recipe));

        serviceImp.delete(1L, 1L);

        verify(repository).deleteById(1L);
    }

    @Test
    public void check_add() throws Exception {

        User user = new User(1L, "test", null);
        Instruction instruction = new Instruction(1L, "test2", null);

        AddRecipeRequest recipeRequest = new AddRecipeRequest();
        Set<AddRecipeDetailRequest> recipeDetailRequestSet = new HashSet<>();
        recipeRequest.setRecipeDetails(recipeDetailRequestSet);

        when(instructionRepository.findById(any())).thenReturn(Optional.of(instruction));
        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        serviceImp.add(1L,recipeRequest);

        verify(repository).save(any());
    }
    @Test
    public void check_update() throws Exception {

        User user = new User(1L, "test", null);
        Instruction instruction = new Instruction(1L, "test2", null);
        Recipe recipe = new Recipe(1L, "test", false, "test", 10L, new User(1L, "test", null), new Instruction(), new HashSet<>());
        Ingredient ingredient = new Ingredient(1L, "test2", true, null);

        UpdateRecipeRequest recipeRequest = new UpdateRecipeRequest();
        Set<UpdateRecipeDetailRequest> recipeDetailRequestSet = new HashSet<>();
        recipeDetailRequestSet.add(new UpdateRecipeDetailRequest());
        recipeRequest.setRecipeDetails(recipeDetailRequestSet);

        when(repository.findByIdAndUserId(1L, 1L)).thenReturn(Optional.of(recipe));
        when(instructionRepository.findById(any())).thenReturn(Optional.of(instruction));
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(ingredientRepository.findById(any())).thenReturn(Optional.of(ingredient));

        serviceImp.update(1L,1L,recipeRequest);

        verify(repository).save(any());
    }
}
