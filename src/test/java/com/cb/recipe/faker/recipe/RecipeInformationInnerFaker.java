package com.cb.recipe.faker.recipe;

import com.cb.recipe.model.spoonacular.recipeInformation.RecipeInformationInner;
import com.cb.recipe.util.Faker;

import java.util.Random;

public class RecipeInformationInnerFaker implements Faker<RecipeInformationInner> {
    // We don't need all fields from RecipeInformationInner
    private int id;
    private int readyInMinutes;
    private boolean vegan;
    private boolean vegetarian;
    private boolean glutenFree;
    private int servings;
    private final Random random;

    public RecipeInformationInnerFaker(final Random random) {
        this.random = random;
        this.id = random.nextInt(10000);
        this.readyInMinutes = 10 + random.nextInt(50); // Between 10 and 59 minutes
        this.vegan = random.nextBoolean();
        this.vegetarian = random.nextBoolean();
        this.glutenFree = random.nextBoolean();
        this.servings = 1 + random.nextInt(5);
    }

    public RecipeInformationInnerFaker withId(final int id){
        this.id = id;
        return this;
    }

    public RecipeInformationInnerFaker withReadyInMinutes(final int minutes) {
        this.readyInMinutes = minutes;
        return this;
    }

    public RecipeInformationInnerFaker withVegan(final boolean vegan) {
        this.vegan = vegan;
        return this;
    }

    public RecipeInformationInnerFaker withVegetarian(final boolean vegetarian) {
        this.vegetarian = vegetarian;
        return this;
    }

    public RecipeInformationInnerFaker withGlutenFree(final boolean glutenFree) {
        this.glutenFree = glutenFree;
        return this;
    }

    public RecipeInformationInnerFaker withServings(final int servings) {
        this.servings = servings;
        return this;
    }

    public static RecipeInformationInnerFaker recipeInformation() {
        return new RecipeInformationInnerFaker(new Random(1));
    }

    public static RecipeInformationInnerFaker recipeInformation(final Random random) {
        return new RecipeInformationInnerFaker(random);
    }

    @Override
    public RecipeInformationInner create() {
        return RecipeInformationInner.builder()
                .id(id)
                .readyInMinutes(readyInMinutes)
                .vegan(vegan)
                .vegetarian(vegetarian)
                .glutenFree(glutenFree)
                .servings(servings)
                .build();
    }
}
