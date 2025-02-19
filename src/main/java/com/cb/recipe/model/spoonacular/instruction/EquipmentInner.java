package com.cb.recipe.model.spoonacular.instruction;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EquipmentInner {
    /**
     * The equipment id.
     */
    private int id;

    /**
     * The equipment image.
     */
    private String image;

    /**
     * The name of the equipment.
     */
    private String name;

    /**
     * Optional temperature information for the equipment.
     */
    private TemperatureInner temperature;
}
