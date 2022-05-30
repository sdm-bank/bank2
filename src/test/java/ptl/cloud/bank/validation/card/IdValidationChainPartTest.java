package ptl.cloud.bank.validation.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ptl.cloud.bank.entities.Card;

import static org.junit.jupiter.api.Assertions.assertThrows;

class IdValidationChainPartTest {
    private IdValidationChainPart idValidationChainPart;

    @BeforeEach
    public void init() {
        idValidationChainPart = new IdValidationChainPart();
    }

    @Test
    void validateBadInput() {
        Card card = new Card();
        card.setId(-20L);
        assertThrows(SecurityException.class, () -> idValidationChainPart.validate(card));
    }

    @Test
    void valiateGodInput() {
        Card card = new Card();
        card.setId(23243L);
        idValidationChainPart.validate(card);
    }
}