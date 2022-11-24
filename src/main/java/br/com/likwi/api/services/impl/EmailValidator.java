package br.com.likwi.api.services.impl;

import br.com.likwi.api.domain.User;
import br.com.likwi.api.exception.DataIntegrityViolationException;
import br.com.likwi.api.exception.ValidatorException;
import br.com.likwi.api.repositoy.UserRepository;
import br.com.likwi.api.services.Validator;
import org.springframework.stereotype.Service;

import static br.com.likwi.api.services.impl.UserServiceImpl.EMAIL_EM_USO;

@Service
public class EmailValidator implements Validator<User> {

    private UserRepository userRepository;

    public EmailValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void check(User user) throws ValidatorException {
        this.userRepository.findByEmail(user.getEmail())
                .ifPresent(email -> {
                    if (!email.getId().equals(user.getId()))
                        throw new DataIntegrityViolationException(EMAIL_EM_USO);
                });
    }
}
