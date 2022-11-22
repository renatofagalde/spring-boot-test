package br.com.likwi.api.services.impl;

import br.com.likwi.api.controller.request.UserRequest;
import br.com.likwi.api.controller.response.UserResponse;
import br.com.likwi.api.domain.User;
import br.com.likwi.api.exception.NotFoundException;
import br.com.likwi.api.repositoy.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    public static final long ID = 1L;
    public static final String NOME = "renato";
    public static final String EMAIL = "renato@likwi.com.br";
    public static final String SENHA = "groselha";
    public static final String ID_NAO_LOCALIZADO = "ID não localizado";
    public static final int INDEX_ZERO = 0;


    @InjectMocks
    private UserServiceImpl underTest;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper modelMapper;

    private User user;

    private Optional<User> optionalUser;

    private UserRequest userRequest;

    private UserResponse userResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.startUser();
    }

    @Test
    @DisplayName("When findbyid then return user instance")
    void when_findById_then_return_user_instance() {

        when(this.repository.findById(anyLong())).thenReturn(optionalUser);

        User userByID = this.underTest.findById(ID);

        assertEquals(User.class, userByID.getClass());
        assertNotNull(userByID);
        assertEquals(ID, userByID.getId());
        assertEquals(NOME, userByID.getName());
        assertEquals(EMAIL, userByID.getEmail());

    }


    @DisplayName("When findbyid then return user instance")
    void when_findById_then_return_object_not_found_exception() {

        when(this.repository.findById(anyLong())).thenThrow(
                new NotFoundException(ID_NAO_LOCALIZADO));

        assertThatThrownBy(() -> this.underTest.findById(ID))
                .hasMessageContaining(ID_NAO_LOCALIZADO)
                .isInstanceOf(NotFoundException.class);

    }



    @Test
    void when_findAll_then_return_list_of_users() {
        when(this.repository.findAll()).thenReturn(List.of(user));

        List<User> all = this.underTest.findAll();

        assertNotNull(all);
        assertEquals(User.class,all.get(INDEX_ZERO).getClass());
        assertEquals(ID,all.get(INDEX_ZERO).getId());
        assertEquals(NOME,all.get(INDEX_ZERO).getName());
        assertEquals(EMAIL,all.get(INDEX_ZERO).getEmail());
        assertEquals(SENHA,all.get(INDEX_ZERO).getPassword());

    }

    @Test
    void when_create_then_return_success() {
        when(this.repository.save((any()))).thenReturn(user);

        User user = this.underTest.create(this.user);
        assertNotNull(user);
        assertEquals(User.class,user.getClass());
        assertEquals(ID,user.getId());
        assertEquals(NOME,user.getName());
        assertEquals(EMAIL,user.getEmail());
        assertEquals(SENHA,user.getPassword());

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