package br.com.likwi.api.exception;

public class ValidatorException extends RuntimeException{
    public ValidatorException(String message) {
        super(message);
    }
}
