package com.bootcamp.clase9.service;

import com.bootcamp.clase9.dto.PlatoResponseDTO;
import com.bootcamp.clase9.dto.PlatoDTO;
import com.bootcamp.clase9.exception.IngredienteNotFound;

import java.util.List;

public interface ICalculateCaloriaService {

    PlatoResponseDTO calcularCalorias(PlatoDTO plato);
    List<PlatoResponseDTO> calcularCaloriasPlatos(List<PlatoDTO> plato);

}
