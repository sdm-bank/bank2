package ptl.cloud.bank.validation.exception.handler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class BasicExceptionValidationHandlerTest {
    private BasicExceptionValidationHandler basicExceptionValidationHandler;

    @BeforeEach
    public void init() {
        basicExceptionValidationHandler = new BasicExceptionValidationHandler();
    }

    @Test
    void handle() {
        Exception exception = new Exception();
        assertThrows(ResponseStatusException.class, () -> basicExceptionValidationHandler.handle(exception));
    }
}