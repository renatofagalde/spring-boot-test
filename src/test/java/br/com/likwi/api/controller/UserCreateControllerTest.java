package br.com.likwi.api.controller;

import br.com.likwi.api.controller.request.UserRequest;
import br.com.likwi.api.controller.response.UserResponse;
import br.com.likwi.api.domain.User;
import br.com.likwi.api.services.UserCreateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserCreateControllerTest {

    public static final long ID = 1L;
    public static final String NAME = "renato";
    public static final String EMAIL = "renato@likwi.com.br";
    public static final String PASSWORD = "groselha";

    public static final int INDEX_ZERO = 0;
    private User user;

    private UserRequest userRequest;

    private UserResponse userResponse;

    public static final String EMAIL_EM_USO = "Email em uso";

    public static final String ID_NAO_LOCALIZADO = "ID não localizado";


    @InjectMocks
    private UserCreateController underTest;

    @Mock
    private UserCreateService service;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.startUser();
    }


    @Test
    void when_create_then_return_created() {
        when(this.service.create(any())).thenReturn(this.user);
        when(this.modelMapper.map(this.userRequest,User.class)).thenReturn(this.user);
        when(this.modelMapper.map(this.user,UserResponse.class)).thenReturn(this.userResponse);

        ResponseEntity<UserResponse> response = this.underTest.create(this.userRequest);

        assertEquals(ResponseEntity.class,response.getClass());
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertNotNull(response.getHeaders().get("Location"));
    }


    private void startUser() {
        this.user = new User(ID, NAME, EMAIL, PASSWORD);
        this.userRequest = new UserRequest(ID, NAME, EMAIL, PASSWORD);
        this.userResponse = new UserResponse(ID, NAME, EMAIL, PASSWORD);
    }


}