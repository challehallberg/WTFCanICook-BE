package com.cb.recipe.mapper;

import com.cb.recipe.model.Instruction;
import com.cb.recipe.model.spoonacular.instruction.InstructionInner;

import java.util.List;

public class InstructionMapper {

    public static List<Instruction> map(final List<InstructionInner> analyzedInstructions) {
        return analyzedInstructions.stream()
                .map(InstructionMapper::mapInstruction)
                .toList();
    }

    private static Instruction mapInstruction(final InstructionInner instructionInner){
        return Instruction.builder()
                .name(instructionInner.getName() != null && !instructionInner.getName().isEmpty() ? instructionInner.getName() : "")
                .steps(InstructionStepMapper.map(instructionInner.getSteps()))
                .build();
    }
}
