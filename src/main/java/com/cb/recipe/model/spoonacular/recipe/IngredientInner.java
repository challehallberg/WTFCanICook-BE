package com.cb.recipe.model.spoonacular.recipe;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class IngredientInner {
    private String aisle;
    private double amount;
    private int id;
    private String image;
    private List<String> meta;
    private String name;
    private String original;
    private String originalName;
    private String unit;
    private String unitLong;
    private String unitShort;
    // Optional field that may not be present in all JSON objects
    private String extendedName;
}
