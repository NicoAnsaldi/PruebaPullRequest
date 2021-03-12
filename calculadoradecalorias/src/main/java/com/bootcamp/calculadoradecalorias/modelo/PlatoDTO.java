package com.bootcamp.calculadoradecalorias.modelo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
public class PlatoDTO {
    String nombre;
    ArrayList<IngredienteEntradaDTO> ingredientes;
}
