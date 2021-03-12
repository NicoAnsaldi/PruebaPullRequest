package com.bootcamp.clase9.repository;

import com.bootcamp.clase9.dto.IngredienteDTO;
import com.bootcamp.clase9.exception.IngredienteNotFound;

public interface ICaloriasRepository {

    double findCaloriasByNombre(String nombre);



}
