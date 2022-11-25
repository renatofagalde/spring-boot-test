package br.com.likwi.api.services.impl;

import br.com.likwi.api.repositoy.UserRepository;
import br.com.likwi.api.services.UserRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCreateServiceImpl extends UserRules {


    public static final String EMAIL_EM_USO = "Email em uso";
    public static final String ID_NÃO_LOCALIZADO = "ID não localizado";
    @Autowired
    private UserRepository userRepository;

}
