package br.com.likwi.api.controller.exceptions;

import br.com.likwi.api.exception.DataIntegrityViolationException;
import br.com.likwi.api.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(DefaultError.class, response.getBody().getClass());
        assertEquals(ID_NAO_LOCALIZADO, response.getBody().getError());
        assertEquals(404,response.getBody().getStatus());
        assertNotEquals("/user/2",response.getBody().getPath());
    }

    @Test
    void when_dataIntegrityViolation_then_() {
        ResponseEntity<DefaultError> response = this.controllerExceptionHandler.dataIntegrityViolation(
                new DataIntegrityViolationException(EMAIL_EM_USO), new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(DefaultError.class, response.getBody().getClass());
        assertEquals(EMAIL_EM_USO, response.getBody().getError());
        assertEquals(400,response.getBody().getStatus());
    }
}