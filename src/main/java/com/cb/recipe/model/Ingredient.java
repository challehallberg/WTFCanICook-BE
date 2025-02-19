package com.cb.recipe.model;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Ingredient {
    private int id;
    private String name;
    private BigDecimal amount;
    private String unit;
    private String image;
}