package com.cb.recipe.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StepLength {
    // The duration number (e.g., 15)
    private double number;

    // The duration unit (e.g., "minutes")
    private String unit;
}