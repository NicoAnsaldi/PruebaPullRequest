package com.bootcamp.clase9.service;

import com.bootcamp.clase9.dto.PersonajeDTO;
import com.bootcamp.clase9.repository.IStarWarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StarWarsService implements IStarWarsService{

    @Autowired
    IStarWarsRepository repository;

    @Override
    public List<PersonajeDTO> buscarPersonajeXNombre(String nombre) {
        List<PersonajeDTO> personajes = repository.findPersonajesByNombre(nombre);
        return personajes;
    }
}
