package com.cb.recipe.endpoint;

import com.cb.recipe.handler.RecipeHandler;
import com.cb.recipe.model.instruction.Instruction;
import com.cb.recipe.model.recipe.Recipe;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/recipes")
@CrossOrigin
public class Endpoint {
    private final RecipeHandler recipeHandler;

    public Endpoint(final RecipeHandler recipeHandler) {
        this.recipeHandler = recipeHandler;
    }

    @PostMapping(value = "/getRecipes", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Get recipes from uploaded images")
    public ResponseEntity<List<Recipe>> getRecipes(@RequestPart("files") final MultipartFile... files) {
        if (files == null) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }
        final List<Recipe> recipes = recipeHandler.getRecipes(files);

        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/instruction/{recipeId}")
    @Operation(summary = "Get instructions for a specific recipe")
    public ResponseEntity<List<Instruction>> getInstructions(@PathVariable("recipeId") final int recipeId) {
        return ResponseEntity.ok(recipeHandler.getInstructions(recipeId));
    }

}