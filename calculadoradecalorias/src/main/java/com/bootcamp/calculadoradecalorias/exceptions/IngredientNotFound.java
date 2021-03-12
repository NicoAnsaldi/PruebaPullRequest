package com.bootcamp.calculadoradecalorias.exceptions;

public class IngredientNotFound extends RuntimeException{
    public IngredientNotFound(String s){
        super(s);
    }
}

