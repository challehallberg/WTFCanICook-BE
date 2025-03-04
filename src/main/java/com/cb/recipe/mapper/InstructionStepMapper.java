package com.cb.recipe.mapper;

import com.cb.recipe.model.instruction.InstructionStep;
import com.cb.recipe.model.spoonacular.instruction.IngredientLeanInner;
import com.cb.recipe.model.spoonacular.instruction.InstructionStepEnrichedInner;
import com.cb.recipe.model.spoonacular.instruction.StepLengthInner;
import com.cb.recipe.model.instruction.StepLength;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class InstructionStepMapper {
    public static List<InstructionStep> map(final List<InstructionStepEnrichedInner> steps) {
        if (steps == null) {
            return Collections.emptyList();
        }
        return steps.stream()
                .map(InstructionStepMapper::map)
                .collect(Collectors.toList());
    }

    private static InstructionStep map(final InstructionStepEnrichedInner responseStep) {
        return InstructionStep.builder()
                .number(responseStep.getNumber())
                .step(responseStep.getStep())

                // Map the optional length if present
                .length(responseStep.getLength() != null ? mapStepLength(responseStep.getLength()) : null)

                // Map equipment list; if null, return an empty list
                .equipment(responseStep.getEquipment() != null
                        ? responseStep.getEquipment().stream()
                        .map(EquipmentMapper::map)
                        .collect(Collectors.toList()) : Collections.emptyList())
                // Map ingredients to a list of names; if null, return an empty list
                .ingredients(responseStep.getIngredients() != null
                        ? responseStep.getIngredients().stream()
                        .map(IngredientLeanInner::getName)
                        .collect(Collectors.toList())
                        : Collections.emptyList())
                .build();
    }

    private static StepLength mapStepLength(final StepLengthInner responseLength) {
        return StepLength.builder()
                .number(responseLength.getNumber())
                .unit(responseLength.getUnit())
                .build();
    }
}
