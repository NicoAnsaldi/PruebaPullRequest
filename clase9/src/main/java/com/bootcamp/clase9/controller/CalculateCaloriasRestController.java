package com.bootcamp.clase9.controller;

import com.bootcamp.clase9.dto.ErrorDTO;
import com.bootcamp.clase9.dto.PlatoResponseDTO;
import com.bootcamp.clase9.dto.PlatoDTO;
import com.bootcamp.clase9.exception.IngredienteNotFound;
import com.bootcamp.clase9.service.ICalculateCaloriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CalculateCaloriasRestController {

    @Autowired
    private ICalculateCaloriaService service;

    @PostMapping(path = "/calorias")
    public PlatoResponseDTO calculateCalories(@RequestBody PlatoDTO plate) {
        return service.calculateCalories(plate);
    }

    @PostMapping(path = "/platos")
    public List<PlatoResponseDTO> calculatePlate(@RequestBody List<PlatoDTO> plate){
        return service.calculateCaloriesXPlate(plate);
    }

    @ExceptionHandler(IngredienteNotFound.class)
    public ResponseEntity<ErrorDTO> handleException(IngredienteNotFound errorException){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setName("Invalid Ingredient");
        errorDTO.setDescripcion("The ingrediente " + errorException.getMensaje() + " is invalid");
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

}
