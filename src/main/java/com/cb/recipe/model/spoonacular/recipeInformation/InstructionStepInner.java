package com.cb.recipe.model.spoonacular.recipeInformation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InstructionStepInner {
    private int number;
    private String step;
}
