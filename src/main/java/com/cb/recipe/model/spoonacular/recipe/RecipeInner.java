package com.cb.recipe.model.spoonacular.recipe;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RecipeInner {
    private int id;
    private String image;
    private String imageType;
    private int likes;
    private int missedIngredientCount;
    private List<IngredientInner> missedIngredients;
    private String title;
    private List<IngredientInner> unusedIngredients;
    private int usedIngredientCount;
    private List<IngredientInner> usedIngredients;
}
