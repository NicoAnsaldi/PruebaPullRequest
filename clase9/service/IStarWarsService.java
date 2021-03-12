package com.bootcamp.clase9.service;

import com.bootcamp.clase9.dto.PersonajeDTO;

import java.util.List;

public interface IStarWarsService {

    List<PersonajeDTO> buscarPersonajeXNombre(String nombre);

}
