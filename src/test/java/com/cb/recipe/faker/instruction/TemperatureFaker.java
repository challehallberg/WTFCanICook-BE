package com.cb.recipe.faker.instruction;

import com.cb.recipe.model.instruction.Temperature;
import com.cb.recipe.util.Faker;

import java.util.Random;

public class TemperatureFaker implements Faker<Temperature> {

    private double number;
    private String unit;
    private final Random random;

    public TemperatureFaker(final Random random) {
        this.random = random;
        // Generate a random temperature between 150 and 250 Fahrenheit
        this.number = 150 + random.nextDouble() * 100;
        this.unit = "Fahrenheit";
    }

    public TemperatureFaker withNumber(double number) {
        this.number = number;
        return this;
    }

    public TemperatureFaker withUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public static TemperatureFaker temperature() {
        return new TemperatureFaker(new Random(1));
    }

    public static TemperatureFaker temperature(Random random) {
        return new TemperatureFaker(random);
    }

    @Override
    public Temperature create() {
        return Temperature.builder()
                .number(number)
                .unit(unit)
                .build();
    }
}
