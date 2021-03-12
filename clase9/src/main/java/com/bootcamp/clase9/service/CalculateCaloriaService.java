package com.bootcamp.clase9.service;

import com.bootcamp.clase9.dto.IngredientDTO;
import com.bootcamp.clase9.dto.PlateResponseDTO;
import com.bootcamp.clase9.dto.PlateDTO;
import com.bootcamp.clase9.repository.ICaloriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalculateCaloriaService implements ICalculateCaloriaService{

    @Autowired
    private ICaloriasRepository repository;

    @Override
    public PlateResponseDTO calculateCalories(PlateDTO plato) {
        PlateResponseDTO result = new PlateResponseDTO();
        result.setIngredients(calcularCaloriasXIngrediente(plato.getIngredients()));
        result.setPlateCalories(caloriesXPlate(result.getIngredients()));
        result.setIngredientWithMoreCalories(getIngredientMoreCaloric(result.getIngredients()));
        return result;
    }

    @Override
    public List<PlateResponseDTO> calculateCaloriesXPlate(List<PlateDTO> dishes){
        List<PlateResponseDTO> result = new ArrayList<PlateResponseDTO>();
        for(PlateDTO plate: dishes){
            result.add(calculateCalories(plate));
        }
        return result;
    }

    private double caloriesXPlate(List<IngredientDTO> ingredients){
        double result = 0;
        if(hasCaloriesLoaded(ingredients)){
            calcularCaloriasXIngrediente(ingredients);
        }
        for (IngredientDTO ingredient : ingredients){
            result += ingredient.getCalories();
        }
        return result;
    }
    private boolean hasCaloriesLoaded(List<IngredientDTO> ingredients){
        boolean result = true;
        if(ingredients.get(0).getCalories() != 0){
            result = false;
        }
        return result;
    }
    private IngredientDTO getIngredientMoreCaloric(List<IngredientDTO> ingredients) {
        IngredientDTO result = null;
        double caloriaMax = 0;
        if(hasCaloriesLoaded(ingredients)){
            calcularCaloriasXIngrediente(ingredients);
        }
        for (IngredientDTO ingrediente : ingredients){
            if(ingrediente.getCalories() > caloriaMax){
                caloriaMax = ingrediente.getCalories();
                result = ingrediente;
            }
        }
        return result;
    }

    private List<IngredientDTO> calcularCaloriasXIngrediente(List<IngredientDTO> ingredients) {
        for(IngredientDTO ingredient : ingredients){
            double caloriasX100g = repository.findCaloriasByNombre(ingredient.getName());
            ingredient.setCalories(caloriasX100g * ingredient.getWeight() / 100);
        }
        return ingredients;
    }

}
