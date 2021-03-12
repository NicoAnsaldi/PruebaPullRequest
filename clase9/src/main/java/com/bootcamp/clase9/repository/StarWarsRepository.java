package com.bootcamp.clase9.repository;

import com.bootcamp.clase9.dto.FoodDTO;
import com.bootcamp.clase9.dto.PersonajeDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Repository
public class StarWarsRepository implements IStarWarsRepository{

    @Override
    public List<PersonajeDTO> findPersonajesByNombre(String nombre) {
        List<PersonajeDTO> result = new ArrayList<PersonajeDTO>();
        List<PersonajeDTO> personajeDTOS = null;
        personajeDTOS = loadDataBase();
        if(personajeDTOS != null) {
            for (PersonajeDTO personaje : personajeDTOS) {
                if (personaje.getName().toLowerCase().contains(nombre.toLowerCase())) {
                    result.add(personaje);
                }
            }
        }
        return result;
    }

    private List<PersonajeDTO> loadDataBase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:starwars.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<PersonajeDTO>> typeRef = new TypeReference<>() {};
        List<PersonajeDTO> personajeDTOS = null;
        try {
            personajeDTOS = objectMapper.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return personajeDTOS;
    }
}
