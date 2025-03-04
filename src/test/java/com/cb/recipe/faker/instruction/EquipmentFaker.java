package com.cb.recipe.faker.instruction;

import com.cb.recipe.model.instruction.Equipment;
import com.cb.recipe.model.instruction.Temperature;
import com.cb.recipe.util.Faker;

import java.util.Random;

public class EquipmentFaker implements Faker<Equipment> {

    private String name;
    private Temperature temperature;
    private final Random random;

    public EquipmentFaker(final Random random) {
        this.random = random;
        this.name = "Equipment " + random.nextInt(100);
        // Randomly decide to include temperature
        this.temperature = random.nextBoolean() ? TemperatureFaker.temperature(random).create() : null;
    }

    public EquipmentFaker withName(String name) {
        this.name = name;
        return this;
    }

    public EquipmentFaker withTemperature(Temperature temperature) {
        this.temperature = temperature;
        return this;
    }

    public static EquipmentFaker equipment() {
        return new EquipmentFaker(new Random(1));
    }

    public static EquipmentFaker equipment(Random random) {
        return new EquipmentFaker(random);
    }

    @Override
    public Equipment create() {
        return Equipment.builder()
                .name(name)
                .temperature(temperature)
                .build();
    }
}
