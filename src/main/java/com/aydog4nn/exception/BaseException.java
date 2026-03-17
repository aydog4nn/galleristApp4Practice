package com.aydog4nn.exception;

public class BaseException extends RuntimeException{
    public BaseException(ErrorMessage errorMessage){
        super(errorMessage.prepareErrorMessage());
    }
}
