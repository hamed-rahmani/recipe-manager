package com.abn.recipes.repository;

import com.abn.recipes.domain.RecipeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeDetailRepository extends JpaRepository<RecipeDetail, Long> {
}
