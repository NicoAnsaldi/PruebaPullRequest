package com.bootcamp.calculadoradecalorias.controller;

import com.bootcamp.calculadoradecalorias.exceptions.IngredientNotFound;
import com.bootcamp.calculadoradecalorias.modelo.ErrorDTO;
import com.bootcamp.calculadoradecalorias.modelo.InformacionNutricionalPlatoDTO;
import com.bootcamp.calculadoradecalorias.modelo.PlatoDTO;
import com.bootcamp.calculadoradecalorias.modelo.PlatosDTO;
import com.bootcamp.calculadoradecalorias.servicies.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CalculadoraDeCaloriasController {
    @Autowired
    private CalculateService calculateService;


    @PostMapping("/calculate")
    public ResponseEntity<InformacionNutricionalPlatoDTO> getInformacion(@RequestBody PlatoDTO platoDTO){
        return new ResponseEntity<>(calculateService.calculate(platoDTO), HttpStatus.OK);
    }

    @PostMapping("/calculateList")
    public ResponseEntity<ArrayList<InformacionNutricionalPlatoDTO>> getInformacionList(@RequestBody PlatosDTO platoDTO){
        return new ResponseEntity<>(calculateService.calculateList(platoDTO), HttpStatus.OK);
    }

    @ExceptionHandler(IngredientNotFound.class)
    public ResponseEntity<ErrorDTO> handleExceptcion(IngredientNotFound errorException){
        ErrorDTO errorDTO = new ErrorDTO("Ingrediente Not Found", "No se encontro el ingrediente "+ errorException.getMessage());
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }
}
