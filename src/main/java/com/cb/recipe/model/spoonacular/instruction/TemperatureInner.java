package com.cb.recipe.model.spoonacular.instruction;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TemperatureInner {
    /**
     * The temperature value.
     */
    private double number;

    /**
     * The unit of the temperature (e.g., "Fahrenheit").
     */
    private String unit;
}
