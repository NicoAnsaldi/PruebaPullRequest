package com.bootcamp.clase9.repository;

import com.bootcamp.clase9.dto.PersonajeDTO;

import java.util.List;

public interface IStarWarsRepository {

    List<PersonajeDTO> findPersonajesByNombre(String nombre);
}
