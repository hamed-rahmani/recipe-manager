package com.abn.recipes.unit.repository;


import com.abn.recipes.domain.Recipe;
import com.abn.recipes.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class RecipeRepositoryTest {

    @Autowired
    private RecipeRepository repository;

    @Test
    void findAll_success() {
        List<Recipe> recipes = repository.findAll();
        assertThat(recipes.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    void findByUserIdIsNull_success() {
        List<Recipe> recipes = repository.findByUserIdIsNull();
        assertThat(recipes.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    void findByUserId_success() {
        List<Recipe> recipes = repository.findByUserId(1L);
        assertThat(recipes.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    void findByIdAndUserId_success() {
        Optional<Recipe> recipes = repository.findByIdAndUserId(1L, 1L);
        assertThat(recipes.isPresent()).isTrue();
    }
    @Test
    void save_success() {
         Recipe recipe = repository.save(new Recipe());
        assertThat(recipe.getId()).isGreaterThanOrEqualTo(1L);
    }
}