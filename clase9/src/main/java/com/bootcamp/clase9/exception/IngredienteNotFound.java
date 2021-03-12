package com.bootcamp.clase9.exception;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class IngredienteNotFound extends Exception{

    private String mensaje;

    public IngredienteNotFound(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
    }
}
