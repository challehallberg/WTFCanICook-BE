package com.cb.recipe.faker.recipe;

import com.cb.recipe.model.spoonacular.recipe.IngredientInner;
import com.cb.recipe.model.spoonacular.recipe.RecipeInner;
import com.cb.recipe.util.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecipeInnerFaker implements Faker<RecipeInner> {

    private int id;
    private String image;
    private String imageType;
    private int likes;
    private int missedIngredientCount;
    private List<IngredientInner> missedIngredients;
    private String title;
    private List<IngredientInner> unusedIngredients;
    private int usedIngredientCount;
    private List<IngredientInner> usedIngredients;

    private final Random random;

    public RecipeInnerFaker(final Random random) {
        this.random = random;
        this.id = random.nextInt(1000);
        this.image = "https://example.com/recipe" + id + ".jpg";
        this.imageType = "jpg";
        this.likes = random.nextInt(100);
        this.missedIngredientCount = random.nextInt(5);
        this.missedIngredients = new ArrayList<>();
        // Generate a list of missed ingredients based on the count.
        for (int i = 0; i < missedIngredientCount; i++) {
            this.missedIngredients.add(IngredientInnerFaker.ingredient(random).create());
        }
        this.title = "Recipe " + id;
        this.unusedIngredients = new ArrayList<>();
        // Optionally, add some unused ingredients if desired. For now, we leave it empty.
        this.usedIngredientCount = random.nextInt(5);
        this.usedIngredients = new ArrayList<>();
        for (int i = 0; i < usedIngredientCount; i++) {
            this.usedIngredients.add(IngredientInnerFaker.ingredient(random).create());
        }
    }

    public RecipeInnerFaker withId(int id) {
        this.id = id;
        return this;
    }

    public RecipeInnerFaker withImage(String image) {
        this.image = image;
        return this;
    }

    public RecipeInnerFaker withImageType(String imageType) {
        this.imageType = imageType;
        return this;
    }

    public RecipeInnerFaker withLikes(int likes) {
        this.likes = likes;
        return this;
    }

    public RecipeInnerFaker withMissedIngredientCount(int count) {
        this.missedIngredientCount = count;
        return this;
    }

    public RecipeInnerFaker withMissedIngredients(List<IngredientInner> ingredients) {
        this.missedIngredients = ingredients;
        return this;
    }

    public RecipeInnerFaker withTitle(String title) {
        this.title = title;
        return this;
    }

    public RecipeInnerFaker withUnusedIngredients(List<IngredientInner> unusedIngredients) {
        this.unusedIngredients = unusedIngredients;
        return this;
    }

    public RecipeInnerFaker withUsedIngredientCount(int count) {
        this.usedIngredientCount = count;
        return this;
    }

    public RecipeInnerFaker withUsedIngredients(List<IngredientInner> usedIngredients) {
        this.usedIngredients = usedIngredients;
        return this;
    }

    public static RecipeInnerFaker recipe() {
        return new RecipeInnerFaker(new Random(1));
    }

    public static RecipeInnerFaker recipe(Random random) {
        return new RecipeInnerFaker(random);
    }

    @Override
    public RecipeInner create() {
        return RecipeInner.builder()
                .id(id)
                .image(image)
                .imageType(imageType)
                .likes(likes)
                .missedIngredientCount(missedIngredientCount)
                .missedIngredients(missedIngredients)
                .title(title)
                .unusedIngredients(unusedIngredients)
                .usedIngredientCount(usedIngredientCount)
                .usedIngredients(usedIngredients)
                .build();
    }
}
