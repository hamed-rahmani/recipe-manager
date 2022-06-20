package com.abn.recipes.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "recipe_detail")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "ingredient_amount")
    private Long ingredientAmount;

    @ManyToOne()
    @JoinColumn(name = "recipe_id", referencedColumnName = "id")
    private Recipe recipe;

    @ManyToOne()
    @JoinColumn(name = "ingredient_id", referencedColumnName = "id")
    private Ingredient ingredient;

}
