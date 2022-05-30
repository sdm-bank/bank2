package ptl.cloud.bank.validation.exception.logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateLoggerDecoratorTest {
    private DateLoggerDecorator dateLoggerDecorator;

    @BeforeEach
    public void init() {
        dateLoggerDecorator = new DateLoggerDecorator();
    }

    @Test
    @Disabled("There is not way to generate two dates with exactly this same millis part")
    void decorate() {
        String msg = "This is test message";
        String decoratedMessage = dateLoggerDecorator.decorate(msg);
        String msgWithDate = msg + LocalDateTime.now().toString();
        assertEquals(msgWithDate, decoratedMessage);
    }
}