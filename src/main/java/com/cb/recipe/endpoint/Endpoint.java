package com.cb.recipe.endpoint;

import com.cb.recipe.handler.RecipeHandler;
import com.cb.recipe.model.Recipe;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/recipes")
@CrossOrigin
@Slf4j
public class Endpoint {
    private final RecipeHandler recipeHandler;

    public Endpoint(final RecipeHandler recipeHandler) {
        this.recipeHandler = recipeHandler;
    }

    @PostMapping(value = "/getRecipes", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Get recipes from uploaded images")
    public ResponseEntity<?> getRecipes(@RequestPart("files") final MultipartFile... files) {
        if (files == null) {
            return ResponseEntity.badRequest().body("File is empty");
        }
        List<Recipe> recipes = recipeHandler.getRecipes(files);

        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/instruction/{recipeId}")
    @Operation(summary = "Get instructions for a specific recipe")
    public ResponseEntity<?> getInstructions(@PathVariable("recipeId") final int recipeId) {
        return ResponseEntity.ok(recipeHandler.getInstructions(recipeId));
    }

    @PostMapping("/ping")
    @Operation(summary = "Ping Pong!")
    public ResponseEntity<?> pong() {
        return ResponseEntity.ok("Pong");
    }

}