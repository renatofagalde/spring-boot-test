package br.com.likwi.api.services.impl;

import br.com.likwi.api.domain.User;
import br.com.likwi.api.exception.NotFoundException;
import br.com.likwi.api.repositoy.UserRepository;
import br.com.likwi.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class UserServiceImpl implements UserService {



    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(
                        MessageFormat.format("ID {0} não localizado",id)
                ));
    }
}
