package br.com.likwi.api.controller;

import br.com.likwi.api.controller.request.UserRequest;
import br.com.likwi.api.controller.response.UserResponse;
import br.com.likwi.api.domain.User;
import br.com.likwi.api.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserControllerTest {

    public static final long ID = 1L;
    public static final String NOME = "renato";
    public static final String EMAIL = "renato@likwi.com.br";
    public static final String SENHA = "groselha";

    public static final int INDEX_ZERO = 0;
    private User user;

    private Optional<User> optionalUser;

    private UserRequest userRequest;

    private UserResponse userResponse;

    public static final String EMAIL_EM_USO = "Email em uso";

    public static final String ID_NAO_LOCALIZADO = "ID não localizado";


    @InjectMocks
    private UserController underTest;

    @Mock
    private UserServiceImpl service;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.startUser();
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser() {
        this.user = new User(ID, NOME, EMAIL, SENHA);
        this.userRequest = new UserRequest(ID, NOME, EMAIL, SENHA);
        this.userResponse = new UserResponse(ID, NOME, EMAIL, SENHA);
        this.optionalUser = Optional.of(new User(ID, NOME, EMAIL, SENHA));
    }

}