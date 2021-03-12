package com.bootcamp.clase9.controller;

import com.bootcamp.clase9.dto.PersonajeDTO;
import com.bootcamp.clase9.service.IStarWarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StarWarsRestController {
    @Autowired
    private IStarWarsService service;

    @PostMapping(path = "/starwars")
    public List<PersonajeDTO> buscarPersonaje(@RequestBody String nombre){
        return service.buscarPersonajeXNombre(nombre);
    }

}
