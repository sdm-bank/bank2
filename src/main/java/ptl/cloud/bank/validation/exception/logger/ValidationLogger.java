package ptl.cloud.bank.validation.exception.logger;

public abstract class ValidationLogger {
    private ValidationLogger next;

    public ValidationLogger() {
    }

    ;

    public ValidationLogger(ValidationLogger next) {
        this.next = next;
    }

    public String decorate(String message) {
        message += makeDecoration(message);
        if (next != null) {
            next.decorate(message);
        }
        return message;
    }

    protected abstract String makeDecoration(String message);
}
