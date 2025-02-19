package com.cb.recipe.model.spoonacular.instruction;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StepLengthInner {
    /**
     * The duration value.
     */
    private double number;

    /**
     * The unit for the duration (e.g. "minutes").
     */
    private String unit;
}
