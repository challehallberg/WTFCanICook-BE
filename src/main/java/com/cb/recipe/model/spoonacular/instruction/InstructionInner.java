package com.cb.recipe.model.spoonacular.instruction;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class InstructionInner {
    /**
     * The name of the instruction group.
     */
    private String name;

    /**
     * The list of steps for this instruction.
     */
    private List<InstructionStepEnrichedInner> steps;
}
