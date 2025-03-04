package com.cb.recipe.faker.instruction;

import com.cb.recipe.model.instruction.Instruction;
import com.cb.recipe.model.instruction.InstructionStep;
import com.cb.recipe.util.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InstructionFaker implements Faker<Instruction> {

    private String name;
    private List<InstructionStep> steps;
    private final Random random;

    public InstructionFaker(final Random random) {
        this.random = random;
        // Sometimes provide a name, sometimes leave it empty.
        this.name = random.nextBoolean() ? "Instruction Set " + random.nextInt(100) : "";
        this.steps = new ArrayList<>();
        final int amountOfSteps = random.nextInt(10);
        for(int i = 1; i < amountOfSteps; i++){
            this.steps.add(InstructionStepFaker.instructionStep(random).withNumber(i).create());
        }
    }

    public InstructionFaker withName(String name) {
        this.name = name;
        return this;
    }

    public InstructionFaker withSteps(List<InstructionStep> steps) {
        this.steps = steps;
        return this;
    }

    public InstructionFaker addStep(InstructionStep step) {
        this.steps.add(step);
        return this;
    }

    public static InstructionFaker instruction() {
        return new InstructionFaker(new Random(1));
    }

    public static InstructionFaker instruction(Random random) {
        return new InstructionFaker(random);
    }

    @Override
    public Instruction create() {
        return Instruction.builder()
                .name(name)
                .steps(steps)
                .build();
    }
}
