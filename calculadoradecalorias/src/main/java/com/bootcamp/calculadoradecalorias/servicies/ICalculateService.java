package com.bootcamp.calculadoradecalorias.servicies;

import com.bootcamp.calculadoradecalorias.modelo.InformacionNutricionalPlatoDTO;
import com.bootcamp.calculadoradecalorias.modelo.PlatoDTO;
import com.bootcamp.calculadoradecalorias.modelo.PlatosDTO;

import java.util.ArrayList;
import java.util.List;

public interface ICalculateService {
    InformacionNutricionalPlatoDTO calculate(PlatoDTO platoDTO);
    ArrayList<InformacionNutricionalPlatoDTO> calculateList(PlatosDTO platoDTO);
}
