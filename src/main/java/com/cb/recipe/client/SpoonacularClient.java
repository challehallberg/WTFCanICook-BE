package com.cb.recipe.client;

import com.cb.recipe.model.spoonacular.instruction.InstructionInner;
import com.cb.recipe.model.spoonacular.recipe.RecipeInner;
import com.cb.recipe.model.spoonacular.recipeInformation.RecipeInformationInner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class SpoonacularClient {

    private final WebClient client;

    //TODO(CB): Fix retryability
    public SpoonacularClient(@Qualifier("spoonacularWebClient") final WebClient webClient) {
        this.client = webClient;
    }


    /**
     * Calls GET /recipes/findByIngredients endpoint with the provided parameters.
     *
     * @param ingredients  a comma-separated list of ingredients (e.g., "apples,flour,sugar")
     * @param number       the maximum number of recipes to return
     * @param ranking      whether to maximize used ingredients (1) or minimize missing ingredients (2)
     * @param ignorePantry whether to ignore typical pantry items (true/false)
     * @return a Mono containing a list of Recipe objects
     */
    public List<RecipeInner> getRecipesByIngredients(final String ingredients, final int number, final int ranking, final boolean ignorePantry) {
        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/recipes/findByIngredients")
                        .queryParam("ingredients", ingredients)
                        .queryParam("number", number)
                        .queryParam("ranking", ranking)
                        .queryParam("ignorePantry", ignorePantry)
                        .queryParam("limitLicense", true)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<RecipeInner>>() {
                })
                .block();
    }

    /**
     * Retrieves bulk recipe information for the given recipe IDs.
     * <p>
     * This method calls the Spoonacular API endpoint "/recipes/informationBulk" using the provided query parameters.
     * It accepts a comma-separated string of recipe IDs and a flag indicating whether to include nutrition data.
     * The JSON response is deserialized into a list of {@link RecipeInformationInner} objects.
     * </p>
     *
     * @param ids              A comma-separated string of recipe IDs (e.g., "715538,716429").
     * @param includeNutrition A boolean flag to include nutrition data in the recipe information.
     * @return A list of {@link RecipeInformationInner} objects corresponding to the provided recipe IDs.
     */
    public List<RecipeInformationInner> getRecipeInformationBulk(final String ids, final boolean includeNutrition) {
        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/recipes/informationBulk")
                        .queryParam("ids", ids)
                        .queryParam("includeNutrition", includeNutrition)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<RecipeInformationInner>>() {
                })
                .block();
    }

    /**
     * Retrieves analyzed instructions for a given recipe.
     * <p>
     * This method calls the Spoonacular API endpoint "/recipes/{id}/analyzedInstructions" with the specified recipe id
     * and step breakdown flag. The JSON response is deserialized into a list of {@link InstructionInner} objects.
     * </p>
     *
     * @param id            the recipe id (e.g., 324694)
     * @param stepBreakdown whether to break down the recipe steps even more
     * @return a list of {@link InstructionInner} objects containing the analyzed instructions for the recipe
     */
    public List<InstructionInner> getAnalyzedInstructions(final int id, final boolean stepBreakdown) {
        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/recipes/{id}/analyzedInstructions")
                        .queryParam("stepBreakdown", stepBreakdown)
                        .build(id))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<InstructionInner>>() {
                })
                .block();
    }
}
