package com.bootcamp.clase9.service;

import com.bootcamp.clase9.dto.IngredienteDTO;
import com.bootcamp.clase9.dto.PlatoResponseDTO;
import com.bootcamp.clase9.dto.PlatoDTO;
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
    public PlatoResponseDTO calculateCalories(PlatoDTO plato) {
        PlatoResponseDTO result = new PlatoResponseDTO();
        result.setIngredientes(calcularCaloriasXIngrediente(plato.getIngredientes()));
        result.setCaloriasPlato(caloriesXPlate(result.getIngredientes()));
        result.setIngredienteConMasCalorias(getIngredientMoreCaloric(result.getIngredientes()));
        return result;
    }

    @Override
    public List<PlatoResponseDTO> calculateCaloriesXPlate(List<PlatoDTO> dishes){
        List<PlatoResponseDTO> result = new ArrayList<PlatoResponseDTO>();
        for(PlatoDTO plate: dishes){
            result.add(calculateCalories(plate));
        }
        return result;
    }

    private double caloriesXPlate(List<IngredienteDTO> ingredients){
        double result = 0;
        if(hasCaloriesLoaded(ingredients)){
            calcularCaloriasXIngrediente(ingredients);
        }
        for (IngredienteDTO ingredient : ingredients){
            result += ingredient.getCalorias();
        }
        return result;
    }
    private boolean hasCaloriesLoaded(List<IngredienteDTO> ingredients){
        boolean result = true;
        if(ingredients.get(0).getCalorias() != 0){
            result = false;
        }
        return result;
    }
    private IngredienteDTO getIngredientMoreCaloric(List<IngredienteDTO> ingredients) {
        IngredienteDTO result = null;
        double caloriaMax = 0;
        if(hasCaloriesLoaded(ingredients)){
            calcularCaloriasXIngrediente(ingredients);
        }
        for (IngredienteDTO ingrediente : ingredients){
            if(ingrediente.getCalorias() > caloriaMax){
                caloriaMax = ingrediente.getCalorias();
                result = ingrediente;
            }
        }
        return result;
    }

    private List<IngredienteDTO> calcularCaloriasXIngrediente(List<IngredienteDTO> ingredients) {
        for(IngredienteDTO ingredient : ingredients){
            double caloriasX100g = repository.findCaloriasByNombre(ingredient.getNombre());
            ingredient.setCalorias(caloriasX100g * ingredient.getPeso() / 100);
        }
        return ingredients;
    }

}
