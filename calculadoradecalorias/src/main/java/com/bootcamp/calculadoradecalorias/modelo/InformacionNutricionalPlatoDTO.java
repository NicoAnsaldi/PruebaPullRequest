package com.bootcamp.calculadoradecalorias.modelo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
public class InformacionNutricionalPlatoDTO {
    double caloriasTotales;
    ArrayList<IngredienteRespuestaDTO> ingredientes;
    IngredienteRespuestaDTO ingredienteMasCalorico;
}
