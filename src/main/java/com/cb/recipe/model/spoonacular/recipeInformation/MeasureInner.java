package com.cb.recipe.model.spoonacular.recipeInformation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MeasureInner {
    private double amount;
    private String unitLong;
    private String unitShort;
}
