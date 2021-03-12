package com.bootcamp.clase9.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class PlateResponseDTO {

    private double plateCalories;
    private List<IngredientDTO> ingredients;
    private IngredientDTO ingredientWithMoreCalories;

}
