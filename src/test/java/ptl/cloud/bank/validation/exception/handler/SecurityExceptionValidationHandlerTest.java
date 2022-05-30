package ptl.cloud.bank.validation.exception.handler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class SecurityExceptionValidationHandlerTest {
    private SecurityExceptionValidationHandler securityExceptionValidationHandler;

    @BeforeEach
    public void init() {
        securityExceptionValidationHandler = new SecurityExceptionValidationHandler();
    }

    @Test
    void handle() {
        SecurityException securityException = new SecurityException();
        assertThrows(ResponseStatusException.class, () -> securityExceptionValidationHandler.handle(securityException));
    }
}