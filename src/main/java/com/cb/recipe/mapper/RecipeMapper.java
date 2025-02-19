package com.cb.recipe.mapper;

import com.cb.recipe.model.Recipe;
import com.cb.recipe.model.spoonacular.recipe.RecipeInner;

import java.math.BigDecimal;
import java.util.List;

public class RecipeMapper {

    public static List<Recipe> map(final List<RecipeInner> recipesByIngredients) {
        return recipesByIngredients.stream()
                .map(recipeInner -> Recipe.builder()
                        .id(recipeInner.getId())
                        .title(recipeInner.getTitle())
                        .image(recipeInner.getImage())
                        .imageType(recipeInner.getImageType())
                        .likes(recipeInner.getLikes())
                        .missedIngredientCount(recipeInner.getMissedIngredientCount())
                        .missedIngredients(IngredientMapper.map(recipeInner.getMissedIngredients()))
                        .usedIngredientCount(BigDecimal.valueOf(recipeInner.getUsedIngredientCount()))
                        .usedIngredients(IngredientMapper.map(recipeInner.getUsedIngredients()))
                        .build())
                .toList();
    }
}
