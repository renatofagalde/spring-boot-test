package br.com.likwi.api.services;

import br.com.likwi.api.exception.ValidatorException;

public interface Validator<T> {
    void check(T object) throws ValidatorException;
}
