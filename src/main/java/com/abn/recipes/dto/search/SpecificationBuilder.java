package com.abn.recipes.dto.search;

import com.abn.recipes.domain.Recipe;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SpecificationBuilder {

    private final List<SearchCriteria> params;

    public SpecificationBuilder() {
        this.params = new ArrayList<>();
    }

    public final SpecificationBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public final SpecificationBuilder with(SearchCriteria searchCriteria) {
        params.add(searchCriteria);
        return this;
    }

    public Specification<Recipe> build() {
        if (params.size() == 0) {
            return null;
        }

        Specification<Recipe> result =
                new RecipeSpecification(params.get(0));
        for (int idx = 1; idx < params.size(); idx++) {
            SearchCriteria criteria = params.get(idx);
            result = SearchOperation.getDataOption(criteria
                    .getDataOption()) == SearchOperation.AND
                    ? Specification.where(result).and(new
                    RecipeSpecification(criteria))
                    : Specification.where(result).or(
                    new RecipeSpecification(criteria));
        }
        return result;
    }
}