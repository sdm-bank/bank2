package ptl.cloud.bank.validation.exception.logger;

import lombok.NoArgsConstructor;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@NoArgsConstructor
public class LogHashDecorator extends ValidationLogger {
    public LogHashDecorator(ValidationLogger next) {
        super(next);
    }

    @Override
    protected String makeDecoration(String message) {
        return DigestUtils.md5DigestAsHex(message.getBytes(StandardCharsets.UTF_8));
    }
}
