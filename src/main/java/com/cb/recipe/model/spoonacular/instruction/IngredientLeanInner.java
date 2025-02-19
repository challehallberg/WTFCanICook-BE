package com.cb.recipe.model.spoonacular.instruction;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IngredientLeanInner {
    /**
     * The ingredient id.
     */
    private int id;

    /**
     * The ingredient image.
     */
    private String image;

    /**
     * The name of the ingredient.
     */
    private String name;
}
