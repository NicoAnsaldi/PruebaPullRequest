package com.bootcamp.calculadoradecalorias.repositories;

import com.bootcamp.calculadoradecalorias.modelo.CaloriesDTO;

import java.util.Optional;

public interface ICaloriesRepository {
    Optional<CaloriesDTO> findCaloriesPropertyByName(String name);
}
