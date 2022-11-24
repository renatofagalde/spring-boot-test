package br.com.likwi.api.exception;

public class EmailException extends RuntimeException{
    public EmailException(String message) {
        super(message);
    }
}
