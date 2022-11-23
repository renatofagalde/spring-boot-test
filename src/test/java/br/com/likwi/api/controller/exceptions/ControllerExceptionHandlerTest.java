package br.com.likwi.api.controller.exceptions;

import br.com.likwi.api.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ControllerExceptionHandlerTest {

    public static final String EMAIL_EM_USO = "Email em uso";

    public static final String ID_NAO_LOCALIZADO = "ID não localizado";


    @InjectMocks
    private ControllerExceptionHandler controllerExceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void when_objectNotFound_then_return_response_entity() {
        ResponseEntity<DefaultError> response = this.controllerExceptionHandler.objectNotFound(
                new NotFoundException(ID_NAO_LOCALIZADO), new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(DefaultError.class, response.getBody().getClass());
        assertEquals(ID_NAO_LOCALIZADO, response.getBody().getError());
        assertEquals(404,response.getBody().getStatus());
    }

    @Test
    void dataIntegratyViolation() {
    }
}