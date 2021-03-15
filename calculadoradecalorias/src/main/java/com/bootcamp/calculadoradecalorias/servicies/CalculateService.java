package com.bootcamp.calculadoradecalorias.servicies;

import com.bootcamp.calculadoradecalorias.exceptions.IngredientNotFound;
import com.bootcamp.calculadoradecalorias.modelo.*;
import com.bootcamp.calculadoradecalorias.repositories.ICaloriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CalculateService implements ICalculateService {
    @Autowired
    private ICaloriesRepository caloriesRepository;

    private double calcularCaloriasDeIngrediente(double quantity, String name){
        Optional<CaloriesDTO> caloriesByName = caloriesRepository.findCaloriesPropertyByName(name);
        double calories = -1.0;
        if(caloriesByName.isPresent()){
            calories = caloriesByName.get().getCalories();
        }else{
            throw new IngredientNotFound(name);
        }
        return calories*(quantity/100);
    }

    private double calcularCaloriasPlato(List<IngredientsEntryDTO> ingredients){
        double result=0.0;
        for(IngredientsEntryDTO in:ingredients){
            result += calcularCaloriasDeIngrediente(in.getWeight(),in.getName());
        }
        return result;
    }

    private ingredientsResponseDTO ingredienteMasCalorico(List<IngredientsEntryDTO> ingredients){
        ingredientsResponseDTO moreCaloricIngredient = new ingredientsResponseDTO();
        moreCaloricIngredient.setCalories(0.0);
        for(IngredientsEntryDTO in:ingredients){
            double auxCalories = calcularCaloriasDeIngrediente(in.getWeight(), in.getName());
            if(moreCaloricIngredient.getCalories() < auxCalories){
                moreCaloricIngredient.setCalories(auxCalories);
                moreCaloricIngredient.setName(in.getName());
            }
        }
        return moreCaloricIngredient;
    }

    private ArrayList<ingredientsResponseDTO> caloriasPorIngredientes(List<IngredientsEntryDTO> ingredients){
        ArrayList<ingredientsResponseDTO> result = new ArrayList<>();
        for(IngredientsEntryDTO in:ingredients){
            ingredientsResponseDTO aux = new ingredientsResponseDTO();
            aux.setName(in.getName());
            aux.setCalories(calcularCaloriasDeIngrediente(in.getWeight(),in.getName()));
            result.add(aux);
        }
        return result;
    }

    @Override
    public PlatesNutritialInformationDTO calculate(PlateDTO plateDTO) {
        PlatesNutritialInformationDTO nutricialInformation = new PlatesNutritialInformationDTO();
        nutricialInformation.setTotalCalories(calcularCaloriasPlato(plateDTO.getIngredients()));
        nutricialInformation.setIngredients(caloriasPorIngredientes(plateDTO.getIngredients()));
        nutricialInformation.setIngredientMoreCaloric(ingredienteMasCalorico(plateDTO.getIngredients()));

        return nutricialInformation;
    }

    @Override
    public ArrayList<PlatesNutritialInformationDTO> calculateList(ListOfPlatesDTO platoDTO) {
        ArrayList<PlatesNutritialInformationDTO> result = new ArrayList<>();
        for(PlateDTO p:platoDTO.getPlates()){
            result.add(calculate(p));
        }
        return result;
    }
}
