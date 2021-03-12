package com.bootcamp.clase9.repository;

import com.bootcamp.clase9.dto.FoodDTO;
import com.bootcamp.clase9.exception.IngredienteNotFound;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Repository
public class CaloriasRepository implements ICaloriasRepository{

    @SneakyThrows
    @Override
    public double findCaloriasByNombre(String nombre){
        List<FoodDTO> foodDTOS = null;
        foodDTOS = loadDataBase();
        double result = -1;
        if(foodDTOS != null){
            Optional<FoodDTO> item = foodDTOS.stream()
                    .filter(foodDTO -> foodDTO.getName().equals(nombre))
                    .findFirst();
            if(item.isPresent()){
                result = item.get().getCalories();
            }else{
                throw new IngredienteNotFound(nombre);
            }
        }
        return result;
    }

    private List<FoodDTO> loadDataBase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:food.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<FoodDTO>> typeRef = new TypeReference<>() {};
        List<FoodDTO> foodDTOS = null;
        try {
            foodDTOS = objectMapper.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return foodDTOS;
    }

}
