package com.cb.recipe.model.spoonacular.recipeInformation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MeasuresInner {
    private MeasureInner metric;
    private MeasureInner us;
}
