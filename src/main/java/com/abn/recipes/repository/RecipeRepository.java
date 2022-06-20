package com.abn.recipes.repository;

import com.abn.recipes.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>, JpaSpecificationExecutor<Recipe> {
    List<Recipe> findByUserIdIsNull();

    List<Recipe> findByUserId(Long userId);

    Optional<Recipe> findByIdAndUserId(Long id, Long userId);
}
