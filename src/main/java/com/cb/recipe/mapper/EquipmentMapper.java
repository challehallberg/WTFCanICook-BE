package com.cb.recipe.mapper;

import com.cb.recipe.model.spoonacular.instruction.EquipmentInner;
import com.cb.recipe.model.instruction.Equipment;
import com.cb.recipe.model.instruction.Temperature;

public class EquipmentMapper {
    public static Equipment map(final EquipmentInner equipmentInner) {
        Temperature temperature = null;
        if (equipmentInner.getTemperature() != null) {
            temperature = Temperature.builder()
                    .number(equipmentInner.getTemperature().getNumber())
                    .unit(equipmentInner.getTemperature().getUnit())
                    .build();
        }
        return Equipment.builder()
                .name(equipmentInner.getName())
                .temperature(temperature)
                .build();
    }
}
