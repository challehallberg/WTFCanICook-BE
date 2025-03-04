package com.cb.recipe.service;

import com.cb.recipe.client.SpoonacularClient;
import com.cb.recipe.faker.instruction.InstructionFaker;
import com.cb.recipe.faker.recipe.RecipeFaker;
import com.cb.recipe.faker.recipe.RecipeInformationInnerFaker;
import com.cb.recipe.faker.recipe.RecipeInnerFaker;
import com.cb.recipe.model.instruction.Instruction;
import com.cb.recipe.model.recipe.Recipe;
import com.cb.recipe.model.spoonacular.recipeInformation.RecipeInformationInner;
import com.cb.recipe.util.UnitTest;
import com.github.benmanes.caffeine.cache.Cache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
class RecipeServiceTest extends UnitTest {
    @Mock
    private SpoonacularClient client;

    @Mock
    private Cache<String, List<Recipe>> recipeCache;

    @Mock
    private Cache<Integer, List<Instruction>> instructionsCache;

    private RecipeService recipeService;

    @BeforeEach
    void setUp() {
        recipeService = new RecipeService(client, recipeCache, instructionsCache);
    }

    @Test
    void givenCachedIngredients_whenGetRecipes_thenExpectedCachedRecipes() {
        final String ingredients = "apple,banana";
        Mockito.when(recipeCache.getIfPresent(ingredients))
                .thenReturn(Arrays.asList(RecipeFaker.recipe().create()));

        final List<Recipe> result = recipeService.getRecipe(ingredients);

        snapshot(result);
    }

    @Test
    void givenValidIngredients_whenGetRecipes_thenExpectNonCachedRecipes() {
        final String ingredients = "apple,banana";
        Mockito.when(recipeCache.getIfPresent(ingredients))
                .thenReturn(null);

        Mockito.when(client.getRecipesByIngredients(anyString(), anyInt(), anyInt(), anyBoolean()))
                .thenReturn(Arrays.asList(RecipeInnerFaker.recipe().create()));

        final List<Recipe> result = recipeService.getRecipe(ingredients);

        snapshot(result);
    }

    @Test
    void givenValidRecipeIds_whenGetRecipeInformationBulk_thenExpectBulkOfRecipeInformation() {
        final String recipeIds = "1,2,3";

        Mockito.when(client.getRecipeInformationBulk(recipeIds, false))
                .thenReturn(Arrays.asList(RecipeInformationInnerFaker.recipeInformation(new Random(1)).create(),
                        RecipeInformationInnerFaker.recipeInformation(new Random(2)).create(),
                        RecipeInformationInnerFaker.recipeInformation(new Random(3)).create()));

        final List<RecipeInformationInner> result = recipeService.getRecipeInformationByIds(recipeIds);

        snapshot(result);
    }

    @Test
    void givenCachedRecipeId_whenGetAnalyzedRecipeInstructions_thenExpectCachedInstructions() {
        final int recipeId = 123;

        Mockito.when(instructionsCache.getIfPresent(recipeId))
                .thenReturn(Arrays.asList(InstructionFaker.instruction().create()));

        final List<Instruction> result = recipeService.getAnalyzedRecipeInstructions(recipeId);

        snapshot(result);
    }
    //TODO
    /*
    @Test
    void testGetAnalyzedRecipeInstructions_CacheMiss() {
        // Given: cache miss, so client call is made
        int recipeId = 1;
        Mockito.when(instructionsCache.getIfPresent(recipeId))
                .thenReturn(null);

        InstructionInner dummyInstructionInner = new InstructionInner();
        // Set any required fields on dummyInstructionInner if needed.
        List<InstructionInner> dummyInstructionInners = List.of(dummyInstructionInner);
        Mockito.when(client.getAnalyzedInstructions(recipeId, false))
                .thenReturn(Arrays.asList(InstructionInner));

        // Use static mocking for InstructionMapper.map
        List<Instruction> mappedInstructions = List.of(Instruction.builder().name("Mapped Instruction").build());
        try (MockedStatic<InstructionMapper> instructionMapperMock = mockStatic(InstructionMapper.class)) {
            instructionMapperMock.when(() -> InstructionMapper.map(dummyInstructionInners))
                    .thenReturn(mappedInstructions);

            // When
            List<Instruction> result = recipeService.getAnalyzedRecipeInstructions(recipeId);

            // Then
            assertEquals(mappedInstructions, result);
            verify(instructionsCache).put(recipeId, mappedInstructions);
        }
    }*/
}