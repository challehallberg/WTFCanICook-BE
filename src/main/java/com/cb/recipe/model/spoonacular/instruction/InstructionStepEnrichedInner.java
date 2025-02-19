package com.cb.recipe.model.spoonacular.instruction;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class InstructionStepEnrichedInner {
    /**
     * The sequential number of this step.
     */
    private int number;

    /**
     * The detailed instruction text.
     */
    private String step;

    /**
     * Optional duration information for this step.
     */
    private StepLengthInner length;

    /**
     * The list of equipment used in this step.
     */
    private List<EquipmentInner> equipment;

    /**
     * The list of ingredients used in this step.
     */
    private List<IngredientLeanInner> ingredients;
}
