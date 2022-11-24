package br.com.likwi.api.services.impl;

import br.com.likwi.api.domain.User;
import br.com.likwi.api.exception.NotFoundException;
import br.com.likwi.api.repositoy.UserRepository;
import br.com.likwi.api.services.UserService;
import br.com.likwi.api.services.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    public static final String EMAIL_EM_USO = "Email em uso";
    public static final String ID_NÃO_LOCALIZADO = "ID não localizado";
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ID_NÃO_LOCALIZADO));
    }

    @Override
    public List<User> findAll() {

        return this.userRepository.findAll();

    }

    @Override
    public User create(User user) {
//        this.findByEmail(user); //todo shoud be a templatemethod
        this.validate(new EmailIsOKValidator(),user);
        this.validate(new EmailTakenValidator(this.userRepository), user);
        return this.userRepository.save(user);
    }

    @Override
    public User update(User user) {
        //this.findByEmail(user);
        this.validate(new EmailTakenValidator(this.userRepository), user);
        return this.userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        this.userRepository.deleteById(this.findById(id).getId());
    }

    private <T> void validate(Validator<T> validator, T objeto){
        validator.check(objeto);
    }



//    private void findByEmail(User user) {
//        this.userRepository.findByEmail(user.getEmail())
//                .ifPresent(email -> {
//                    if (!email.getId().equals(user.getId()))
//                        throw new DataIntegrityViolationException(EMAIL_EM_USO);
//                });
//    }
}
