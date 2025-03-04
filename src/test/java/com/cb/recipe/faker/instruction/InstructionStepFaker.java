package com.cb.recipe.faker.instruction;

import com.cb.recipe.model.instruction.InstructionStep;
import com.cb.recipe.model.instruction.Equipment;
import com.cb.recipe.model.instruction.StepLength;
import com.cb.recipe.util.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InstructionStepFaker implements Faker<InstructionStep> {

    private int number;
    private String step;
    private StepLength length;
    private List<Equipment> equipment;
    private List<String> ingredients;
    private final Random random;

    public InstructionStepFaker(final Random random) {
        this.random = random;
        this.number = 1 + random.nextInt(10);
        this.step = "Step description " + number;
        // Optionally include a StepLength with some probability
        this.length = random.nextBoolean() ? StepLengthFaker.stepLength(random).create() : null;
        // Optionally generate a list of equipment (can be empty)
        this.equipment = new ArrayList<>();
        if (random.nextBoolean()) {
            this.equipment.add(EquipmentFaker.equipment(random).create());
        }
        // Optionally generate some ingredient names (can be empty)
        this.ingredients = new ArrayList<>();
        if (random.nextBoolean()) {
            this.ingredients.add("Ingredient " + (1 + random.nextInt(100)));
            this.ingredients.add("Ingredient " + (1 + random.nextInt(100)));
        }
    }

    public InstructionStepFaker withNumber(int number) {
        this.number = number;
        return this;
    }

    public InstructionStepFaker withStep(String step) {
        this.step = step;
        return this;
    }

    public InstructionStepFaker withLength(StepLength length) {
        this.length = length;
        return this;
    }

    public InstructionStepFaker withEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
        return this;
    }

    public InstructionStepFaker withIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public static InstructionStepFaker instructionStep() {
        return new InstructionStepFaker(new Random(1));
    }

    public static InstructionStepFaker instructionStep(Random random) {
        return new InstructionStepFaker(random);
    }

    @Override
    public InstructionStep create() {
        return InstructionStep.builder()
                .number(number)
                .step(step)
                .length(length)
                .equipment(equipment)
                .ingredients(ingredients)
                .build();
    }
}
