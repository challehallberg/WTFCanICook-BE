package com.cb.recipe.handler;

import com.cb.recipe.faker.instruction.InstructionFaker;
import com.cb.recipe.faker.recipe.RecipeFaker;
import com.cb.recipe.faker.recipe.RecipeInformationInnerFaker;
import com.cb.recipe.model.instruction.Instruction;
import com.cb.recipe.model.recipe.Recipe;
import com.cb.recipe.service.OpenAiService;
import com.cb.recipe.service.RecipeService;
import com.cb.recipe.util.UnitTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecipeHandlerTest extends UnitTest {

    @Mock
    private OpenAiService openAiService;

    @Mock
    private RecipeService recipeService;

    @Mock
    private MultipartFile file1;

    @Mock
    private MultipartFile file2;

    @InjectMocks
    private RecipeHandler recipeHandler;

    @Test
    void givenValidFilesWithNoBytes_whenGetRecipes_thenExpectEmptyList() {
        when(openAiService.processImage(file1))
                .thenReturn("");
        when(openAiService.processImage(file2))
                .thenReturn("");

        final List<Recipe> result = recipeHandler.getRecipes(file1, file2);

        assertEquals(Collections.emptyList(), result);
        verify(recipeService, never()).getRecipe(anyString());
    }

    @Test
    void givenValidFile_whenGetRecipes_thenExpectRecipeList() {
        final int recipeId = 123;

        when(openAiService.processImage(file1))
                .thenReturn("apple, banana");

        when(recipeService.getRecipe("apple, banana"))
                .thenReturn(Arrays.asList(RecipeFaker.recipe()
                        .withId(recipeId)
                        //Enrichment fields are not set initially.
                        .withReadyInMinutes(0)
                        .withVegan(false)
                        .withVegetarian(false)
                        .withGlutenFree(false)
                        .withServings(0)
                        .create()));

        when(recipeService.getRecipeInformationByIds(Mockito.anyString()))
                .thenReturn(Arrays.asList(RecipeInformationInnerFaker.recipeInformation()
                        .withId(recipeId)
                        .create()));

        final List<Recipe> result = recipeHandler.getRecipes(file1);

        snapshot(result);
    }

    @Test
    void givenTwoValidFiles_whenGetRecipes_thenExpectRecipeList() {
        final int recipeId1 = 123;
        final int recipeId2 = 321;

        when(openAiService.processImage(file1))
                .thenReturn("apple, banana");

        when(recipeService.getRecipe("apple, banana"))
                .thenReturn(Arrays.asList(RecipeFaker.recipe()
                        .withId(recipeId1)
                        //Enrichment fields are not set initially.
                        .withReadyInMinutes(0)
                        .withVegan(false)
                        .withVegetarian(false)
                        .withGlutenFree(false)
                        .withServings(0)
                        .create(), RecipeFaker.recipe(new Random(2))
                        .withId(recipeId2)
                        //Enrichment fields are not set initially.
                        .withReadyInMinutes(0)
                        .withVegan(false)
                        .withVegetarian(false)
                        .withGlutenFree(false)
                        .withServings(0)
                        .create()));

        when(recipeService.getRecipeInformationByIds(Mockito.anyString()))
                .thenReturn(Arrays.asList(RecipeInformationInnerFaker.recipeInformation()
                        .withId(recipeId1)
                        .create(), RecipeInformationInnerFaker.recipeInformation(new Random(2))
                        .withId(recipeId2)
                        .create()));

        final List<Recipe> result = recipeHandler.getRecipes(file1);

        snapshot(result);
    }

    @Test
    void givenValidRecipeId_whenGetAnalyzedRecipeInstructions_thenExpectedInstructions() {
        final int recipeId = 101;

        when(recipeService.getAnalyzedRecipeInstructions(recipeId))
                .thenReturn(Arrays.asList(InstructionFaker.instruction().create()));

        List<Instruction> result = recipeHandler.getInstructions(recipeId);

        snapshot(result);

        verify(recipeService, times(1)).getAnalyzedRecipeInstructions(recipeId);
    }
}
