package com.bootcamp.calculadoradecalorias.servicies;

import com.bootcamp.calculadoradecalorias.modelo.PlatesNutritialInformationDTO;
import com.bootcamp.calculadoradecalorias.modelo.PlateDTO;
import com.bootcamp.calculadoradecalorias.modelo.ListOfPlatesDTO;

import java.util.ArrayList;

public interface ICalculateService {
    PlatesNutritialInformationDTO calculate(PlateDTO platoDTO);
    ArrayList<PlatesNutritialInformationDTO> calculateList(ListOfPlatesDTO platoDTO);
}
