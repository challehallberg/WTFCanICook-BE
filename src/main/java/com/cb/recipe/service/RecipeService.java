package com.cb.recipe.service;

import com.cb.recipe.client.SpoonacularClient;
import com.cb.recipe.mapper.InstructionMapper;
import com.cb.recipe.mapper.RecipeMapper;
import com.cb.recipe.model.instruction.Instruction;
import com.cb.recipe.model.recipe.Recipe;
import com.cb.recipe.model.spoonacular.instruction.InstructionInner;
import com.cb.recipe.model.spoonacular.recipe.RecipeInner;
import com.cb.recipe.model.spoonacular.recipeInformation.RecipeInformationInner;
import com.github.benmanes.caffeine.cache.Cache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class RecipeService {
    private final SpoonacularClient client;
    private final Cache<String, List<Recipe>> recipeCache;
    private final Cache<Integer, List<Instruction>> instructionsCache;

    public RecipeService(final SpoonacularClient client,
                         @Qualifier("recipeCache") final Cache<String, List<Recipe>> recipeCache,
                         @Qualifier("instructionsCache") final Cache<Integer, List<Instruction>> instructionsCache) {
        this.client = client;
        this.recipeCache = recipeCache;
        this.instructionsCache = instructionsCache;
    }

    public List<Recipe> getRecipe(final String ingredients) {
        final List<Recipe> cachedRecipes = recipeCache.getIfPresent(ingredients);
        if (cachedRecipes != null) {
            log.info("Recipes based on these ingredients are already cached, retrieving cached recipes");
            return cachedRecipes;
        }

        final int number = 10;                 // Integer | The maximum number of items to return (between 1 and 100). Defaults to 10.
        final int ranking = 1;                 // Integer | Whether to maximize used ingredients (1) or minimize missing ingredients (2) first.
        final boolean ignorePantry = true;     // Boolean | Whether to ignore typical pantry items, such as water, salt, flour, etc.

        try {
            final List<RecipeInner> recipesByIngredients = client.getRecipesByIngredients(ingredients, number, ranking, ignorePantry);

            final List<Recipe> recipes = RecipeMapper.map(recipesByIngredients);

            recipeCache.put(ingredients, recipes);

            return recipes;
        } catch (Exception e) {
            log.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    public List<RecipeInformationInner> getRecipeInformationByIds(final String recipeIds) {
        return client.getRecipeInformationBulk(recipeIds, false);
    }

    public List<Instruction> getAnalyzedRecipeInstructions(final int recipeId) {
        final List<Instruction> cachedInstructions = instructionsCache.getIfPresent(recipeId);
        if (cachedInstructions != null) {
            log.info("Instructions for {} are already cached, retrieving cached instructions", recipeId);
            return cachedInstructions;
        }

        final List<InstructionInner> analyzedInstructions = client.getAnalyzedInstructions(recipeId, false);
        final List<Instruction> mappedInstructions = InstructionMapper.map(analyzedInstructions);

        instructionsCache.put(recipeId, mappedInstructions);
        return mappedInstructions;
    }
}
