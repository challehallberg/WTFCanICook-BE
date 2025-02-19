package com.cb.recipe.endpoint;

import com.cb.recipe.handler.RecipeHandler;
import com.cb.recipe.model.Recipe;
import lombok.extern.slf4j.Slf4j;
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

    @PostMapping("/getRecipes")
    public ResponseEntity<?> getRecipes(@RequestPart("files") final MultipartFile... files) {
        if (files == null) {
            return ResponseEntity.badRequest().body("File is empty");
        }
        List<Recipe> recipes = recipeHandler.getRecipes(files);

        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/instruction/{recipeId}")
    public ResponseEntity<?> getInstructions(@PathVariable("recipeId") final int recipeId) {
        return ResponseEntity.ok(recipeHandler.getInstructions(recipeId));
    }

    @PostMapping("/ping")
    public ResponseEntity<?> pong() {
        return ResponseEntity.ok("Pong");
    }

}