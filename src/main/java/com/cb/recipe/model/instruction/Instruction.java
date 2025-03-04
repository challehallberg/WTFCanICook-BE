package com.cb.recipe.model.instruction;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Instruction {
    // The name of this instruction set (can be empty)
    //@Nullable
    private String name;

    // The steps associated with this instruction set
    private List<InstructionStep> steps;
}
