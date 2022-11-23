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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserControllerTest {

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
    void when_findById_then_return_success() {
        when(this.service.findById(anyLong())).thenReturn(this.user);
        when(this.modelMapper.map(any(), any())).thenReturn(this.userResponse);

        ResponseEntity<UserResponse> response = this.underTest.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserResponse.class,response.getBody().getClass());

        assertEquals(ID,response.getBody().getId());
        assertEquals(NAME,response.getBody().getName());
        assertEquals(EMAIL,response.getBody().getEmail());
        assertEquals(PASSWORD,response.getBody().getPassword());

    }

    @Test
    void when_findAll_then_return_list_of_users() {
        when(this.service.findAll()).thenReturn(List.of(this.user));
        when(this.modelMapper.map(any(), any())).thenReturn(this.userResponse);

        ResponseEntity<List<UserResponse>> response = this.underTest.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class,response.getBody().getClass());
        assertEquals(UserResponse.class,response.getBody().get(INDEX_ZERO).getClass());

        assertEquals(ID,response.getBody().get(INDEX_ZERO).getId());
        assertEquals(NAME,response.getBody().get(INDEX_ZERO).getName());
        assertEquals(EMAIL,response.getBody().get(INDEX_ZERO).getEmail());

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

    @Test
    void when_update_return_success() {

        when(this.service.update(any())).thenReturn(this.user);
        when(this.modelMapper.map(this.userRequest,User.class)).thenReturn(this.user);
        when(this.modelMapper.map(this.user,UserResponse.class)).thenReturn(this.userResponse);

        ResponseEntity<UserResponse> response = this.underTest.update(ID,this.userRequest);

        assertEquals(ResponseEntity.class,response.getClass());
        assertEquals(HttpStatus.OK,response.getStatusCode());

        assertEquals(ID,response.getBody().getId());
        assertEquals(NAME,response.getBody().getName());
        assertEquals(EMAIL,response.getBody().getEmail());

    }

    @Test
    void delete() {
    }

    private void startUser() {
        this.user = new User(ID, NAME, EMAIL, PASSWORD);
        this.userRequest = new UserRequest(ID, NAME, EMAIL, PASSWORD);
        this.userResponse = new UserResponse(ID, NAME, EMAIL, PASSWORD);
    }

}