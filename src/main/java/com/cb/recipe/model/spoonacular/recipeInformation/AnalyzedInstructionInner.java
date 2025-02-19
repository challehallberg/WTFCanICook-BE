package com.cb.recipe.model.spoonacular.recipeInformation;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AnalyzedInstructionInner {
    private String name;
    private List<InstructionStepInner> steps;
}