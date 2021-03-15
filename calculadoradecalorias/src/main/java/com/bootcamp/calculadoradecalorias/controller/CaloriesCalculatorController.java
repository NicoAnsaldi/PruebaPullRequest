package com.bootcamp.calculadoradecalorias.controller;

import com.bootcamp.calculadoradecalorias.exceptions.IngredientNotFound;
import com.bootcamp.calculadoradecalorias.modelo.ErrorDTO;
import com.bootcamp.calculadoradecalorias.modelo.PlatesNutritialInformationDTO;
import com.bootcamp.calculadoradecalorias.modelo.PlateDTO;
import com.bootcamp.calculadoradecalorias.modelo.ListOfPlatesDTO;
import com.bootcamp.calculadoradecalorias.servicies.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class CaloriesCalculatorController {
    @Autowired
    private CalculateService calculateService;


    @PostMapping("/calculate")
    public ResponseEntity<PlatesNutritialInformationDTO> getInformacion(@RequestBody PlateDTO plateDTO){
        return new ResponseEntity<>(calculateService.calculate(plateDTO), HttpStatus.OK);
    }

    @PostMapping("/calculateList")
    public ResponseEntity<ArrayList<PlatesNutritialInformationDTO>> getInformacionList(@RequestBody ListOfPlatesDTO listOfPlatesDTO){
        return new ResponseEntity<>(calculateService.calculateList(listOfPlatesDTO), HttpStatus.OK);
    }

    @ExceptionHandler(IngredientNotFound.class)
    public ResponseEntity<ErrorDTO> handleExceptcion(IngredientNotFound errorException){
        ErrorDTO errorDTO = new ErrorDTO("Ingrediente Not Found", "No se encontro el ingrediente "+ errorException.getMessage());
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }
}
