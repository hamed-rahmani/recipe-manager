package com.abn.recipes.unit.repository;


import com.abn.recipes.domain.Ingredient;
import com.abn.recipes.repository.IngredientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class IngredientRepositoryTest {

    @Autowired
    private IngredientRepository repository;

    @Test
    void findAll_success() {
        List<Ingredient> ingredients = repository.findAll();
        assertThat(ingredients.size()).isGreaterThanOrEqualTo(1);
    }
}