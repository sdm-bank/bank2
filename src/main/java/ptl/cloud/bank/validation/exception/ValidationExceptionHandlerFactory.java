package ptl.cloud.bank.validation.exception;

import ptl.cloud.bank.validation.exception.handler.BasicExceptionValidationHandler;
import ptl.cloud.bank.validation.exception.handler.IllegalArgumentExceptionHandler;
import ptl.cloud.bank.validation.exception.handler.SecurityExceptionValidationHandler;
import ptl.cloud.bank.validation.exception.handler.ValidationExceptionHandler;

public class ValidationExceptionHandlerFactory {
    public <E extends Exception> ValidationExceptionHandler get(E exception) {
        if (exception.getClass().isAssignableFrom(SecurityException.class)) {
            return new SecurityExceptionValidationHandler();
        }
        if (exception.getClass().isAssignableFrom(IllegalArgumentException.class)) {
            return new IllegalArgumentExceptionHandler();
        }
        return new BasicExceptionValidationHandler();
    }
}
