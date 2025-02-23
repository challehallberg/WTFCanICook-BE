package com.cb.recipe.handler;

import com.cb.recipe.model.Instruction;
import com.cb.recipe.model.Recipe;
import com.cb.recipe.model.spoonacular.recipeInformation.RecipeInformationInner;
import com.cb.recipe.service.OpenAiService;
import com.cb.recipe.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RecipeHandler {
    private final OpenAiService openAiService;
    private final RecipeService recipeService;

    public RecipeHandler(final OpenAiService openAiService, final RecipeService recipeService) {
        this.openAiService = openAiService;
        this.recipeService = recipeService;
    }

    public List<Recipe> getRecipes(final MultipartFile... files) {
        log.info("Fetching ingredients from {} file/files", files.length);

        final String ingredients = Arrays.stream(files)
                .map(openAiService::processImage)
                .collect(Collectors.joining(", "));

        if(ingredients.isEmpty()){
            log.info("No ingredients fetched from file/files");
            return Collections.emptyList();
        }

        final List<Recipe> recipes = recipeService.getRecipe(ingredients);

        final String recipeIds = recipes.stream()
                .map(recipe -> String.valueOf(recipe.getId()))
                .collect(Collectors.joining(","));

        final List<RecipeInformationInner> bulkOfRecipeInformation = recipeService.getRecipeInformationByIds(recipeIds);

        enrichRecipes(recipes, bulkOfRecipeInformation);

        log.info("Fetched {} recipes", recipes.size());

        return recipes;
    }

    public List<Instruction> getInstructions(final int recipeId) {
        log.info("Fetching instructions for recipe {}", recipeId);
        return recipeService.getAnalyzedRecipeInstructions(recipeId);
    }

    /**
     * Enriches each {@link Recipe} in the provided list with additional details from the corresponding
     * {@link RecipeInformationInner} objects.
     * <p>
     * The method creates a mapping from recipe IDs to {@code RecipeInformationInner} using the provided set.
     * For each recipe in the list, if a corresponding {@code RecipeInformationInner} is found, the recipe's details
     * such as ready in minutes, vegan, vegetarian, gluten-free, and servings are updated.
     * </p>
     *
     * @param recipes                 the list of recipes to be enriched; must not be {@code null}
     * @param bulkOfRecipeInformation the set of recipe information objects containing additional details; must not be {@code null}
     */
    private void enrichRecipes(final List<Recipe> recipes, final List<RecipeInformationInner> bulkOfRecipeInformation) {
        final Map<Integer, RecipeInformationInner> recipeInfoMap = bulkOfRecipeInformation.stream()
                .collect(Collectors.toMap(RecipeInformationInner::getId, Function.identity()));

        recipes.forEach(recipe -> {
            RecipeInformationInner info = recipeInfoMap.get(recipe.getId());
            if (info != null) {
                recipe.setReadyInMinutes(info.getReadyInMinutes());
                recipe.setVegan(info.isVegan());
                recipe.setVegetarian(info.isVegetarian());
                recipe.setGlutenFree(info.isGlutenFree());
                recipe.setServings(info.getServings());
            }
        });
    }
}
