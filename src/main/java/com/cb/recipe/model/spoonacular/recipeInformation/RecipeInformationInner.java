package com.cb.recipe.model.spoonacular.recipeInformation;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RecipeInformationInner {
    private int id;
    private String title;
    private String image;
    private String imageType;
    private int servings;
    private int readyInMinutes;
    private int cookingMinutes;
    private int preparationMinutes;
    private String license;
    private String sourceName;
    private String sourceUrl;
    private String spoonacularSourceUrl;
    private double healthScore;
    private double spoonacularScore;
    private double pricePerServing;
    private List<AnalyzedInstructionInner> analyzedInstructions; // can be empty
    private boolean cheap;
    private String creditsText;
    private List<String> cuisines;
    private boolean dairyFree;
    private List<String> diets;
    private String gaps;
    private boolean glutenFree;
    private String instructions;
    private boolean ketogenic;
    private boolean lowFodmap;
    private List<String> occasions;
    private boolean sustainable;
    private boolean vegan;
    private boolean vegetarian;
    private boolean veryHealthy;
    private boolean veryPopular;
    private boolean whole30;
    private int weightWatcherSmartPoints;
    private List<String> dishTypes;
    private List<ExtendedIngredientInner> extendedIngredients;
    private String summary;
    private WinePairingInner winePairing;
}
