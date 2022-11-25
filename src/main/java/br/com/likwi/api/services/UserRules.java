package br.com.likwi.api.services;

import br.com.likwi.api.domain.User;
import br.com.likwi.api.repositoy.UserRepository;
import br.com.likwi.api.services.impl.EmailIsOKValidator;
import br.com.likwi.api.services.impl.EmailTakenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class UserRules implements UserCreateService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User create(User user) {
        this.validations(user);
        return this.userRepository.save(user);
    }

    private void validations(User user) {
        this.validate(new EmailIsOKValidator(), user);
        this.validate(new EmailTakenValidator(this.userRepository),user);
    }

    private <T> void validate(Validator<T> validator, T objeto) {
        validator.check(objeto);
    }
}
