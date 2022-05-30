package ptl.cloud.bank.validation.exception.handler;

import lombok.extern.slf4j.Slf4j;
import ptl.cloud.bank.validation.exception.logger.DateLoggerDecorator;
import ptl.cloud.bank.validation.exception.logger.LogHashDecorator;
import ptl.cloud.bank.validation.exception.logger.ValidationLogger;

@Slf4j
public abstract class ValidationExceptionHandler<E extends Exception> {
    private final ValidationLogger validationChain = new DateLoggerDecorator(new LogHashDecorator());

    protected void log(E exception) {
        log.error(validationChain.decorate("There was exception: " + exception.getMessage()));
    }

    public void handle(E exception) {
        log(exception);
        makeHandle(exception);
    }

    protected abstract void makeHandle(E exception);
}
