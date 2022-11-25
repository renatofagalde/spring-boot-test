package br.com.likwi.api.services;

import br.com.likwi.api.domain.User;
import br.com.likwi.api.repositoy.UserRepository;
import br.com.likwi.api.services.impl.EmailIsOKValidator;
import br.com.likwi.api.services.impl.EmailTakenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class UserCreateRules extends ValidateService implements UserCreateService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public User create(User user) {
        this.validations(user);
        return this.userRepository.save(user);
    }

    private void validations(User user) {
        validate(new EmailIsOKValidator(), user);
        validate(new EmailTakenValidator(this.userRepository),user);
    }


}
