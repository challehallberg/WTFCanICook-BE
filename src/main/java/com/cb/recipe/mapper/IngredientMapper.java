package com.cb.recipe.mapper;

import com.cb.recipe.model.recipe.Ingredient;
import com.cb.recipe.model.spoonacular.recipe.IngredientInner;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class IngredientMapper {
    public static List<Ingredient> map(final List<IngredientInner> ingredients) {
        if(ingredients == null){
            return Collections.emptyList();
        }
        return ingredients.stream()
                .map(IngredientMapper::mapIngredient)
                .toList();
    }

    private static Ingredient mapIngredient(final IngredientInner source) {
        if (source == null) {
            return null;
        }
        return Ingredient.builder()
                .id(source.getId())
                .name(source.getName())
                .amount(BigDecimal.valueOf(source.getAmount()))
                .unit(source.getUnit())
                .image(source.getImage())
                .build();
    }
}
