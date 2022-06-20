package com.abn.recipes.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ingredient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Boolean vegetable;

    @OneToMany(mappedBy = "ingredient")
    private Set<RecipeDetail> recipeDetails;
}
