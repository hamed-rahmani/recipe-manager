package com.abn.recipes.dto.search;

import com.abn.recipes.domain.Ingredient;
import com.abn.recipes.domain.Recipe;
import com.abn.recipes.domain.RecipeDetail;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Objects;

public class RecipeSpecification implements Specification<Recipe> {

    private final SearchCriteria searchCriteria;

    public RecipeSpecification(final SearchCriteria searchCriteria) {
        super();
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Recipe> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

        switch (Objects.requireNonNull(SearchOperation.getSimpleOperation(searchCriteria.getOperation()))) {
            case EQUAL:
                if (searchCriteria.getFilterKey().equals("instruction")) {
                    return cb.equal(instructionJoin(root).<String>get("name"), searchCriteria.getValue());
                } else if (searchCriteria.getFilterKey().equals("ingredient")) {
                    return cb.equal(recipeDetailJoin(root).<String>get("name"), searchCriteria.getValue());
                } else if (searchCriteria.getFilterKey().equals("vegetable")) {
                    return cb.equal(root.get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString().equalsIgnoreCase("True") ? Boolean.TRUE : Boolean.FALSE);
                }
                return cb.equal(root.get(searchCriteria.getFilterKey()), searchCriteria.getValue());

            case NOT_EQUAL:
                if (searchCriteria.getFilterKey().equals("instruction")) {
                    return cb.notEqual(instructionJoin(root).<String>get("name"), searchCriteria.getValue());
                } else if (searchCriteria.getFilterKey().equals("ingredient")) {
                    return cb.notEqual(recipeDetailJoin(root).<String>get("name"), searchCriteria.getValue());
                } else if (searchCriteria.getFilterKey().equals("vegetable")) {
                    return cb.notEqual(root.get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString().equalsIgnoreCase("true") ? Boolean.TRUE : Boolean.FALSE);
                }
                return cb.notEqual(root.get(searchCriteria.getFilterKey()), searchCriteria.getValue());

            case GREATER_THAN:
                if (searchCriteria.getFilterKey().equals("serveNumber"))
                    return cb.greaterThan(root.<String>get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());

            case GREATER_THAN_EQUAL:
                if (searchCriteria.getFilterKey().equals("serveNumber"))
                    return cb.greaterThanOrEqualTo(root.<String>get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());

        }
        return null;
    }

    private Join<Recipe, RecipeDetail> recipeDetailJoin(Root<Recipe> root) {
        return root.join("recipeDetails").join("ingredient");
    }

    private Join<RecipeDetail, Ingredient> instructionJoin(Root<Recipe> root) {
        return root.join("instruction");
    }
}