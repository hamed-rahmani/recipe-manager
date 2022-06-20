package com.abn.recipes.dto.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchDto {

    @NotNull
    private List<SearchCriteria> searchCriteriaList;
    private String dataOption;

}