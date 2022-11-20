package br.com.likwi.api.services.impl;

import br.com.likwi.api.domain.User;
import br.com.likwi.api.repositoy.UserRepository;
import br.com.likwi.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Não localizado"));
    }
}
