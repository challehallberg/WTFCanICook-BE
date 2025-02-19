package com.cb.recipe.model.spoonacular.recipeInformation;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductMatchInner {
    private int id;
    private String title;
    private String description;
    private String price;
    private String imageUrl;
    private double averageRating;
    private double ratingCount;
    private double score;
    private String link;
}
