package br.com.likwi.api.services;

public abstract class ValidateService {
    protected  <T> void validate(Validator<T> validator, T objeto) {
        validator.check(objeto);
    }

}
