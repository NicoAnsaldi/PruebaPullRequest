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
    public PlatoResponseDTO calcularCalorias(PlatoDTO plato) {
        PlatoResponseDTO result = new PlatoResponseDTO();
        result.setIngredientes(calcularCaloriasXIngrediente(plato.getIngredientes()));
        result.setCaloriasPlato(caloriasXPlato(result.getIngredientes()));
        result.setIngredienteConMasCalorias(obtenerIngredienteMasCalorico(result.getIngredientes()));
        return result;
    }

    @Override
    public List<PlatoResponseDTO> calcularCaloriasPlatos(List<PlatoDTO> platos){
        List<PlatoResponseDTO> result = new ArrayList<PlatoResponseDTO>();
        for(PlatoDTO plato: platos){
            result.add(calcularCalorias(plato));
        }
        return result;
    }

    private double caloriasXPlato(List<IngredienteDTO> ingredientes){
        double result = 0;
        if(tieneCaloriasCargadas(ingredientes)){
            calcularCaloriasXIngrediente(ingredientes);
        }
        for (IngredienteDTO ingrediente : ingredientes){
            result += ingrediente.getCalorias();
        }
        return result;
    }
    private boolean tieneCaloriasCargadas(List<IngredienteDTO> ingredientes){
        boolean result = true;
        if(ingredientes.get(0).getCalorias() != 0){
            result = false;
        }
        return result;
    }
    private IngredienteDTO obtenerIngredienteMasCalorico(List<IngredienteDTO> ingredientes) {
        IngredienteDTO result = null;
        double caloriaMax = 0;
        if(tieneCaloriasCargadas(ingredientes)){
            calcularCaloriasXIngrediente(ingredientes);
        }
        for (IngredienteDTO ingrediente : ingredientes){
            if(ingrediente.getCalorias() > caloriaMax){
                caloriaMax = ingrediente.getCalorias();
                result = ingrediente;
            }
        }
        return result;
    }

    private List<IngredienteDTO> calcularCaloriasXIngrediente(List<IngredienteDTO> ingredientes) {
        for(IngredienteDTO ingrediente : ingredientes){
            double caloriasX100g = repository.findCaloriasByNombre(ingrediente.getNombre());
            ingrediente.setCalorias(caloriasX100g * ingrediente.getPeso() / 100);
        }
        return ingredientes;
    }

}
