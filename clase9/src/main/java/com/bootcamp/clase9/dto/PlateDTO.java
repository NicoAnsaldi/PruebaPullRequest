package com.bootcamp.clase9.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter @Setter
public class PlateDTO {

    private String name;
    private List<IngredientDTO> ingredients;

}
