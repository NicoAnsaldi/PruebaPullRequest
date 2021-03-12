package com.bootcamp.clase9.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class PlatoResponseDTO {

    private double caloriasPlato;
    private List<IngredienteDTO> ingredientes;
    private IngredienteDTO ingredienteConMasCalorias;

}
