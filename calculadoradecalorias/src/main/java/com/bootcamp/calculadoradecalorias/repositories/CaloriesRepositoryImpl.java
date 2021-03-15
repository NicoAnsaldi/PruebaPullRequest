package com.bootcamp.calculadoradecalorias.repositories;

import com.bootcamp.calculadoradecalorias.modelo.CaloriesDTO;
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
public class CaloriesRepositoryImpl implements ICaloriesRepository {
    @Override
    public Optional<CaloriesDTO> findCaloriesPropertyByName(String name) {
        List<CaloriesDTO> caloriesDTOS = null;
        caloriesDTOS = loadDataBase();
        Optional<CaloriesDTO> item = null;
        if(caloriesDTOS != null){
            item = caloriesDTOS.stream().filter(caloriasDTO -> caloriasDTO.getName().equals(name))
                    .findFirst();
        }
        return item;
    }

    private List<CaloriesDTO> loadDataBase(){
        File file = null;
        try{
            file = ResourceUtils.getFile("classpath:food.json");
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<CaloriesDTO>> typeRef = new TypeReference<List<CaloriesDTO>>() {};
        List<CaloriesDTO> caloriasDTOS = null;
        try{
            caloriasDTOS = objectMapper.readValue(file, typeRef);
        }catch (IOException e){
            e.printStackTrace();
        }
        return caloriasDTOS;
    }
}
