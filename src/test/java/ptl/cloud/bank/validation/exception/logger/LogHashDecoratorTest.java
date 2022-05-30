package ptl.cloud.bank.validation.exception.logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LogHashDecoratorTest {
    private LogHashDecorator logHashDecorator;

    @BeforeEach
    public void init() {
        logHashDecorator = new LogHashDecorator();
    }

    @Test
    void decorate() {
        String msg = "This is test message";
        String decorateMessage = logHashDecorator.decorate(msg);
        String hashedMessage = msg + DigestUtils.md5DigestAsHex(msg.getBytes(StandardCharsets.UTF_8));
        assertEquals(hashedMessage, decorateMessage);
    }
}