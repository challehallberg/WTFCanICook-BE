package com.cb.recipe.model.instruction;

import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

@Data
@Builder
public class InstructionStep {
    // The order number of this step
    private int number;

    // The textual description of the step
    private String step;

    // Optional: the duration for this step (if provided)
    @Nullable
    private StepLength length;

    // A list of equipment names used in this step
    @Nullable
    private List<Equipment> equipment;

    // A list of ingredient names used in this step
    @Nullable
    private List<String> ingredients;

    public Optional<StepLength> getOptionalLength() {
        return Optional.ofNullable(length);
    }

    // Optional getter for equipment
    public Optional<List<Equipment>> getOptionalEquipment() {
        return Optional.ofNullable(equipment);
    }

    // Optional getter for ingredients
    public Optional<List<String>> getOptionalIngredients() {
        return Optional.ofNullable(ingredients);
    }
}