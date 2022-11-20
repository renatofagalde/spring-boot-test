package br.com.likwi.api.services;

import br.com.likwi.api.domain.User;

public interface UserService {
    User findById(Long id);
}
