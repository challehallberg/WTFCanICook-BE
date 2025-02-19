package com.cb.recipe.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Temperature {
    // The temperature value (e.g., 200.0)
    private double number;

    // The unit of the temperature (e.g., "Fahrenheit")
    private String unit;
}