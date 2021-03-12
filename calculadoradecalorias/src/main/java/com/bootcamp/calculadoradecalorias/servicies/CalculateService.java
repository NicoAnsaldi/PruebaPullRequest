package com.bootcamp.calculadoradecalorias.servicies;

import com.bootcamp.calculadoradecalorias.exceptions.IngredientNotFound;
import com.bootcamp.calculadoradecalorias.modelo.*;
import com.bootcamp.calculadoradecalorias.repositories.IRepositorioCalorias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CalculateService implements ICalculateService {
    @Autowired
    private IRepositorioCalorias repositorioCalorias;

    private double calcularCaloriasDeIngrediente(double cantidad, String nombre){
        Optional<CaloriasDTO> caloriasPorNombre = repositorioCalorias.encontrarCaloriasPorNombre(nombre);
        double calorias = -1.0;
        if(caloriasPorNombre.isPresent()){
            calorias = caloriasPorNombre.get().getCalories();
        }else{
            throw new IngredientNotFound(nombre);
        }
        return calorias*(cantidad/100);
    }

    private double calcularCaloriasPlato(List<IngredienteEntradaDTO> ingredientes){
        double result=0.0;
        for(IngredienteEntradaDTO in:ingredientes){
            result += calcularCaloriasDeIngrediente(in.getPeso(),in.getNombre());
        }
        return result;
    }

    private IngredienteRespuestaDTO ingredienteMasCalorico(List<IngredienteEntradaDTO> ingredientes){
        IngredienteRespuestaDTO ingredienteMasCalorico = new IngredienteRespuestaDTO();
        ingredienteMasCalorico.setCalorias(0.0);
        for(IngredienteEntradaDTO in:ingredientes){
            double auxCalorias = calcularCaloriasDeIngrediente(in.getPeso(), in.getNombre());
            if(ingredienteMasCalorico.getCalorias() < auxCalorias){
                ingredienteMasCalorico.setCalorias(auxCalorias);
                ingredienteMasCalorico.setNombre(in.getNombre());
            }
        }
        return ingredienteMasCalorico;
    }

    private ArrayList<IngredienteRespuestaDTO> caloriasPorIngredientes(List<IngredienteEntradaDTO> ingrediente){
        ArrayList<IngredienteRespuestaDTO> result = new ArrayList<>();
        for(IngredienteEntradaDTO in:ingrediente){
            IngredienteRespuestaDTO aux = new IngredienteRespuestaDTO();
            aux.setNombre(in.getNombre());
            aux.setCalorias(calcularCaloriasDeIngrediente(in.getPeso(),in.getNombre()));
            result.add(aux);
        }
        return result;
    }

    @Override
    public InformacionNutricionalPlatoDTO calculate(PlatoDTO platoDTO) {
        InformacionNutricionalPlatoDTO informacionNutricional = new InformacionNutricionalPlatoDTO();
        informacionNutricional.setCaloriasTotales(calcularCaloriasPlato(platoDTO.getIngredientes()));
        informacionNutricional.setIngredientes(caloriasPorIngredientes(platoDTO.getIngredientes()));
        informacionNutricional.setIngredienteMasCalorico(ingredienteMasCalorico(platoDTO.getIngredientes()));

        return informacionNutricional;
    }

    @Override
    public ArrayList<InformacionNutricionalPlatoDTO> calculateList(PlatosDTO platoDTO) {
        ArrayList<InformacionNutricionalPlatoDTO> result = new ArrayList<>();
        for(PlatoDTO p:platoDTO.getPlatos()){
            result.add(calculate(p));
        }
        return result;
    }
}
