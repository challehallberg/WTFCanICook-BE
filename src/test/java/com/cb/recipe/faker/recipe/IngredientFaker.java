package com.cb.recipe.faker.recipe;

import com.cb.recipe.model.recipe.Ingredient;
import com.cb.recipe.util.Faker;

import java.math.BigDecimal;
import java.util.Random;

public class IngredientFaker implements Faker<Ingredient> {

    private int id;
    private String name;
    private BigDecimal amount;
    private String unit;
    private String image;

    private final Random random;

    public IngredientFaker(final Random random) {
        this.random = random;
        this.id = random.nextInt(1000);
        this.name = "Ingredient " + id;
        // Generate a random amount between 1 and 10
        this.amount = BigDecimal.valueOf(1 + (random.nextDouble() * 9));
        // Choose a random unit from a predefined list
        final String[] units = {"tsp", "tbsp", "cup", "g", "oz"};
        this.unit = units[random.nextInt(units.length)];
        this.image = "https://example.com/ingredient" + id + ".jpg";
    }

    public IngredientFaker withId(final int id) {
        this.id = id;
        return this;
    }

    public IngredientFaker withName(final String name) {
        this.name = name;
        return this;
    }

    public IngredientFaker withAmount(final BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public IngredientFaker withUnit(final String unit) {
        this.unit = unit;
        return this;
    }

    public IngredientFaker withImage(final String image) {
        this.image = image;
        return this;
    }

    public static IngredientFaker ingredient() {
        return new IngredientFaker(new Random(1));
    }

    public static IngredientFaker ingredient(final Random random) {
        return new IngredientFaker(random);
    }

    @Override
    public Ingredient create() {
        return Ingredient.builder()
                .id(id)
                .name(name)
                .amount(amount)
                .unit(unit)
                .image(image)
                .build();
    }
}
