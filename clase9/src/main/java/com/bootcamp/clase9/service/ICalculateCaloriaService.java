package com.bootcamp.clase9.service;

import com.bootcamp.clase9.dto.PlateResponseDTO;
import com.bootcamp.clase9.dto.PlateDTO;

import java.util.List;

public interface ICalculateCaloriaService {

    PlateResponseDTO calculateCalories(PlateDTO plate);
    List<PlateResponseDTO> calculateCaloriesXPlate(List<PlateDTO> plate);

}
