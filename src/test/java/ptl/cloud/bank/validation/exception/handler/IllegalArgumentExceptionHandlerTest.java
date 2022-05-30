package ptl.cloud.bank.validation.exception.handler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class IllegalArgumentExceptionHandlerTest {
    private IllegalArgumentExceptionHandler illegalArgumentExceptionHandler;

    @BeforeEach
    public void init() {
        illegalArgumentExceptionHandler = new IllegalArgumentExceptionHandler();
    }

    @Test
    void handle() {
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
        assertThrows(ResponseStatusException.class,
                () -> illegalArgumentExceptionHandler.handle(illegalArgumentException));
    }
}