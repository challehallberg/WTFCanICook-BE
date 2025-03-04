package com.cb.recipe.model.recipe;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class Recipe {
    private int id;
    private String title;
    private String image;
    private String imageType;
    private int likes;

    private int missedIngredientCount;
    private List<Ingredient> missedIngredients;

    private BigDecimal usedIngredientCount;
    private List<Ingredient> usedIngredients;

    private int readyInMinutes;
    private boolean vegan;
    private boolean vegetarian;
    private boolean glutenFree;
    private int servings;
}
