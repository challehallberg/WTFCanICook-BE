package com.cb.recipe.faker.recipe;

import com.cb.recipe.model.recipe.Ingredient;
import com.cb.recipe.model.recipe.Recipe;
import com.cb.recipe.util.Faker;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecipeFaker implements Faker<Recipe> {

    private int id;
    private String title;
    private String image;
    private String imageType;
    private int likes;
    private int missedIngredientCount;
    private List<Ingredient> missedIngredients;
    private BigDecimal usedIngredientCount;
    private List<Ingredient> usedIngredients;
    private int readyInMinutes;
    private boolean vegan;
    private boolean vegetarian;
    private boolean glutenFree;
    private int servings;

    private final Random random;

    public RecipeFaker(final Random random) {
        this.random = random;
        this.id = random.nextInt(1000);
        this.title = "Recipe " + id;
        this.image = "https://example.com/image" + id + ".jpg";
        this.imageType = "jpg";
        this.likes = random.nextInt(100);

        final int missedIngredientCount = random.nextInt(5);
        final BigDecimal usedIngredientCount = BigDecimal.valueOf(random.nextInt(5));

        this.missedIngredientCount = missedIngredientCount;
        this.missedIngredients = createIngredients(missedIngredientCount);

        this.usedIngredientCount = usedIngredientCount;
        this.usedIngredients = createIngredients(usedIngredientCount.intValue());

        this.readyInMinutes = 10 + random.nextInt(50);
        this.vegan = random.nextBoolean();
        this.vegetarian = random.nextBoolean();
        this.glutenFree = random.nextBoolean();
        this.servings = 1 + random.nextInt(5);
    }

    private List<Ingredient> createIngredients(final int count){
        List<Ingredient> ingredients = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            ingredients.add(IngredientFaker.ingredient(new Random(i)).create());
        }
        return ingredients;
    }

    public RecipeFaker withId(final int id) {
        this.id = id;
        return this;
    }

    public RecipeFaker withTitle(final String title) {
        this.title = title;
        return this;
    }

    public RecipeFaker withImage(final String image) {
        this.image = image;
        return this;
    }

    public RecipeFaker withImageType(final String imageType) {
        this.imageType = imageType;
        return this;
    }

    public RecipeFaker withLikes(final int likes) {
        this.likes = likes;
        return this;
    }

    public RecipeFaker withMissedIngredientCount(final int count) {
        this.missedIngredientCount = count;
        return this;
    }

    public RecipeFaker withMissedIngredients(final List<Ingredient> ingredients) {
        this.missedIngredients = ingredients;
        return this;
    }

    public RecipeFaker withUsedIngredientCount(final BigDecimal count) {
        this.usedIngredientCount = count;
        return this;
    }

    public RecipeFaker withUsedIngredients(final List<Ingredient> ingredients) {
        this.usedIngredients = ingredients;
        return this;
    }

    public RecipeFaker withReadyInMinutes(final int minutes) {
        this.readyInMinutes = minutes;
        return this;
    }

    public RecipeFaker withVegan(final boolean vegan) {
        this.vegan = vegan;
        return this;
    }

    public RecipeFaker withVegetarian(final boolean vegetarian) {
        this.vegetarian = vegetarian;
        return this;
    }

    public RecipeFaker withGlutenFree(final boolean glutenFree) {
        this.glutenFree = glutenFree;
        return this;
    }

    public RecipeFaker withServings(final int servings) {
        this.servings = servings;
        return this;
    }

    public static RecipeFaker recipe() {
        return new RecipeFaker(new Random(1));
    }

    public static RecipeFaker recipe(final Random random) {
        return new RecipeFaker(random);
    }

    @Override
    public Recipe create() {
        return Recipe.builder()
                .id(id)
                .title(title)
                .image(image)
                .imageType(imageType)
                .likes(likes)
                .missedIngredientCount(missedIngredientCount)
                .missedIngredients(missedIngredients)
                .usedIngredientCount(usedIngredientCount)
                .usedIngredients(usedIngredients)
                .readyInMinutes(readyInMinutes)
                .vegan(vegan)
                .vegetarian(vegetarian)
                .glutenFree(glutenFree)
                .servings(servings)
                .build();
    }
}