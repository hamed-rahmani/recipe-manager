package com.abn.recipes.unit.service;

import com.abn.recipes.domain.Ingredient;
import com.abn.recipes.domain.User;
import com.abn.recipes.repository.IngredientRepository;
import com.abn.recipes.repository.UserRepository;
import com.abn.recipes.service.IngredientServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IngredientServiceTest {

    @Mock
    private IngredientRepository repository;

    @InjectMocks
    private IngredientServiceImp serviceImp;

    @Test
    public void check_GetIngredient() {

        List<Ingredient> ingredientList = new ArrayList<>();

        ingredientList.add(new Ingredient(1L, "test", false, null));
        ingredientList.add(new Ingredient(1L, "test2", true, null));

        when(repository.findAll()).thenReturn(ingredientList);

        List<Ingredient> users = serviceImp.findAll();
        assertThat(users.size()).isGreaterThan(0);

        verify(repository).findAll();
    }
}
