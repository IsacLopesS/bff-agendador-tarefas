package com.isac.bffagendador.infrastructure.exceptions;

public class BusinessException extends RuntimeException{

    public BusinessException(String mensagem){
        super(mensagem);
    }
}
