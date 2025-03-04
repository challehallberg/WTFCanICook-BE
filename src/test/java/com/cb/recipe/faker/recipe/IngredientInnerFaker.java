package com.cb.recipe.faker.recipe;

import com.cb.recipe.model.spoonacular.recipe.IngredientInner;
import com.cb.recipe.util.Faker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class IngredientInnerFaker implements Faker<IngredientInner> {

    private int id;
    private String aisle;
    private double amount;
    private String image;
    private List<String> meta;
    private String name;
    private String original;
    private String originalName;
    private String unit;
    private String unitLong;
    private String unitShort;
    private String extendedName; // Optional field

    private final Random random;

    public IngredientInnerFaker(final Random random) {
        this.random = random;
        this.id = random.nextInt(1000);
        this.aisle = "Aisle " + (random.nextInt(10) + 1);
        // Set default unit values before using them in original
        this.unit = "unit";
        this.unitLong = "unit";
        this.unitShort = "u";
        this.amount = 1.0 + random.nextDouble() * 9.0; // random between 1.0 and 10.0
        this.image = "https://example.com/ingredient" + id + ".jpg";
        this.meta = new ArrayList<>(Arrays.asList("fresh", "organic"));
        this.name = "Ingredient " + id;
        // Use the already-set unit to build the original string
        this.original = amount + " " + unit + " " + name;
        this.originalName = name;
        this.extendedName = null; // Optional by default
    }

    public IngredientInnerFaker withId(int id) {
        this.id = id;
        return this;
    }

    public IngredientInnerFaker withAisle(String aisle) {
        this.aisle = aisle;
        return this;
    }

    public IngredientInnerFaker withAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public IngredientInnerFaker withImage(String image) {
        this.image = image;
        return this;
    }

    public IngredientInnerFaker withMeta(List<String> meta) {
        this.meta = meta;
        return this;
    }

    public IngredientInnerFaker withName(String name) {
        this.name = name;
        return this;
    }

    public IngredientInnerFaker withOriginal(String original) {
        this.original = original;
        return this;
    }

    public IngredientInnerFaker withOriginalName(String originalName) {
        this.originalName = originalName;
        return this;
    }

    public IngredientInnerFaker withUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public IngredientInnerFaker withUnitLong(String unitLong) {
        this.unitLong = unitLong;
        return this;
    }

    public IngredientInnerFaker withUnitShort(String unitShort) {
        this.unitShort = unitShort;
        return this;
    }

    public IngredientInnerFaker withExtendedName(String extendedName) {
        this.extendedName = extendedName;
        return this;
    }

    public static IngredientInnerFaker ingredient() {
        return new IngredientInnerFaker(new Random(1));
    }

    public static IngredientInnerFaker ingredient(final Random random) {
        return new IngredientInnerFaker(random);
    }

    @Override
    public IngredientInner create() {
        return IngredientInner.builder()
                .id(id)
                .aisle(aisle)
                .amount(amount)
                .image(image)
                .meta(meta)
                .name(name)
                .original(original)
                .originalName(originalName)
                .unit(unit)
                .unitLong(unitLong)
                .unitShort(unitShort)
                .extendedName(extendedName)
                .build();
    }
}
