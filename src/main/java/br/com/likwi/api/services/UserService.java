package br.com.likwi.api.services;

import br.com.likwi.api.domain.User;

import java.util.List;

public interface UserService {
    User findById(Long id);

    List<User> findAll();

    User update(User user);

    void delete(Long id);
}
