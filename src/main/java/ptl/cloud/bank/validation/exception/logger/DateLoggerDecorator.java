package ptl.cloud.bank.validation.exception.logger;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
public class DateLoggerDecorator extends ValidationLogger {
    public DateLoggerDecorator(ValidationLogger next) {
        super(next);
    }

    @Override
    protected String makeDecoration(String message) {
        return LocalDateTime.now().toString();
    }
}
