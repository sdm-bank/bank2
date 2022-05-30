package ptl.cloud.bank.validation.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ptl.cloud.bank.validation.exception.handler.IllegalArgumentExceptionHandler;
import ptl.cloud.bank.validation.exception.handler.SecurityExceptionValidationHandler;
import ptl.cloud.bank.validation.exception.handler.ValidationExceptionHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidationExceptionHandlerFactoryTest {
    private ValidationExceptionHandlerFactory validationExceptionHandlerFactory;

    @BeforeEach
    public void init() {
        validationExceptionHandlerFactory = new ValidationExceptionHandlerFactory();
    }

    @Test
    void createForSecurityException() {
        SecurityException securityException = new SecurityException();
        ValidationExceptionHandler validationExceptionHandler = validationExceptionHandlerFactory.get(
                securityException);
        assertEquals(SecurityExceptionValidationHandler.class, validationExceptionHandler.getClass());
    }

    @Test
    void createForIllegalArgumentException() {
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
        ValidationExceptionHandler validationExceptionHandler = validationExceptionHandlerFactory.get(
                illegalArgumentException);
        assertEquals(IllegalArgumentExceptionHandler.class, validationExceptionHandler.getClass());
    }
}