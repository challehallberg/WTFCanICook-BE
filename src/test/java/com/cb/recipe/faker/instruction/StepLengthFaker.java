package com.cb.recipe.faker.instruction;

import com.cb.recipe.model.instruction.StepLength;
import com.cb.recipe.util.Faker;

import java.util.Random;

public class StepLengthFaker implements Faker<StepLength> {

    private double number;
    private String unit;
    private final Random random;

    public StepLengthFaker(final Random random) {
        this.random = random;
        // Generate a random duration between 1 and 30 minutes.
        this.number = 1 + random.nextDouble() * 29;
        this.unit = "minutes";
    }

    public StepLengthFaker withNumber(double number) {
        this.number = number;
        return this;
    }

    public StepLengthFaker withUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public static StepLengthFaker stepLength() {
        return new StepLengthFaker(new Random(1));
    }

    public static StepLengthFaker stepLength(Random random) {
        return new StepLengthFaker(random);
    }

    @Override
    public StepLength create() {
        return StepLength.builder()
                .number(number)
                .unit(unit)
                .build();
    }
}
