package com.abn.recipes.dto.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchDto {


    private List<SearchCriteria> searchCriteriaList;
    private String dataOption;

}