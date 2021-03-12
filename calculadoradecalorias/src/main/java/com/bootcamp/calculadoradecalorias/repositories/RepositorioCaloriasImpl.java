package com.bootcamp.calculadoradecalorias.repositories;

import com.bootcamp.calculadoradecalorias.modelo.CaloriasDTO;
import com.bootcamp.calculadoradecalorias.repositories.IRepositorioCalorias;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Repository
public class RepositorioCaloriasImpl implements IRepositorioCalorias {
    @Override
    public Optional<CaloriasDTO> encontrarCaloriasPorNombre(String nombre) {
        List<CaloriasDTO> caloriasDTOS = null;
        caloriasDTOS = loadDataBase();
        Optional<CaloriasDTO> item = null;
        if(caloriasDTOS != null){
            item = caloriasDTOS.stream().filter(caloriasDTO -> caloriasDTO.getName().equals(nombre))
                    .findFirst();
        }
        return item;
    }

    private List<CaloriasDTO> loadDataBase(){
        File file = null;
        try{
            file = ResourceUtils.getFile("classpath:food.json");
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<CaloriasDTO>> typeRef = new TypeReference<List<CaloriasDTO>>() {};
        List<CaloriasDTO> caloriasDTOS = null;
        try{
            caloriasDTOS = objectMapper.readValue(file, typeRef);
        }catch (IOException e){
            e.printStackTrace();
        }
        return caloriasDTOS;
    }
}
