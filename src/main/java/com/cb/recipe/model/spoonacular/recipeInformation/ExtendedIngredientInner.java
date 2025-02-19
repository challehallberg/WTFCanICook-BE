package com.cb.recipe.model.spoonacular.recipeInformation;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ExtendedIngredientInner {
    private String aisle;
    private double amount;
    private String consistency;
    private int id;
    private String image;
    private MeasuresInner measuresInner;
    private List<String> meta;
    private String name;
    private String original;
    private String originalName;
    private String unit;
}
