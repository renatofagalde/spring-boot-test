package br.com.likwi.api.services.impl;

import br.com.likwi.api.controller.request.UserRequest;
import br.com.likwi.api.controller.response.UserResponse;
import br.com.likwi.api.domain.User;
import br.com.likwi.api.exception.DataIntegrityViolationException;
import br.com.likwi.api.repositoy.UserRepository;
import br.com.likwi.api.services.UserCreateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserCreateServiceImplTest {

    public static final long ID = 1L;
    public static final String NAME = "renato";
    public static final String EMAIL = "renato@likwi.com.br";
    public static final String PASSWORD = "groselha";

    public static final int INDEX_ZERO = 0;
    private User user= new User();

    private Optional<User> optionalUser;

    private UserRequest userRequest=new UserRequest();

    private UserResponse userResponse;

    public static final String EMAIL_EM_USO = "Email em uso";

    public static final String ID_NAO_LOCALIZADO = "ID não localizado";

    @InjectMocks
    private UserCreateService underTest;

    @Mock
    private UserRepository repository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.startUser();
    }

    @Test
    void when_create_then_return_success() {
        when(this.repository.save((any()))).thenReturn(user);

        User user = this.underTest.create(this.user);
        assertNotNull(user);
        assertEquals(User.class,user.getClass());
        assertEquals(ID,user.getId());
        assertEquals(NAME,user.getName());
        assertEquals(EMAIL,user.getEmail());
        assertEquals(PASSWORD,user.getPassword());

    }

    @SuppressWarnings("unchecked")
    @Test
    void when_create_then_return_data_violation_exception() {
        this.optionalUser.get().setId(2L);
        when(this.repository.findByEmail((anyString()))).thenReturn(this.optionalUser);

        assertThatThrownBy(() -> this.underTest.create(this.user))
                .isInstanceOf(DataIntegrityViolationException.class)
                .hasMessage(EMAIL_EM_USO);
    }

    private void startUser() {
        this.user = new User(ID, NAME, EMAIL, PASSWORD);
        this.userRequest = new UserRequest(ID, NAME, EMAIL, PASSWORD);
        this.userResponse = new UserResponse(ID, NAME, EMAIL, PASSWORD);
        this.optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}