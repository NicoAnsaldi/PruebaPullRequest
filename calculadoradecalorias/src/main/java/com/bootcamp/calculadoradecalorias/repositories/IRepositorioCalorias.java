package com.bootcamp.calculadoradecalorias.repositories;

import com.bootcamp.calculadoradecalorias.modelo.CaloriasDTO;

import java.util.Optional;

public interface IRepositorioCalorias {
    Optional<CaloriasDTO> encontrarCaloriasPorNombre(String nombre);
}
