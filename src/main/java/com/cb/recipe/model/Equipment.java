package com.cb.recipe.model;

import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

@Data
@Builder
public class Equipment {
    // The name of the equipment (e.g., "oven")
    private String name;

    // Optional temperature information for the equipment
    @Nullable
    private Temperature temperature;

    public Optional<Temperature> getOptionalTemperature() {
        return Optional.ofNullable(temperature);
    }
}