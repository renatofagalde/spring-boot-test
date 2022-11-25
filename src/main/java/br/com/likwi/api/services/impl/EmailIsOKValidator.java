package br.com.likwi.api.services.impl;

import br.com.likwi.api.domain.User;
import br.com.likwi.api.exception.EmailException;
import br.com.likwi.api.exception.ValidatorException;
import br.com.likwi.api.services.Validator;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.regex.Pattern;

@Service
public class EmailIsOKValidator implements Validator<User> {

    private String regexPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*"
            + "@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    @Override
    public void check(User user) throws ValidatorException {

        if(!this.patternMatches(user.getEmail(),regexPattern)){
            throw new EmailException(
                    MessageFormat.format("Email {0} inválido",user.getEmail())
            );
        }

    }

    private boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
}
