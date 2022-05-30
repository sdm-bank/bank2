package ptl.cloud.bank.validation.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ptl.cloud.bank.entities.Card;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CardValidatorChainTest {
    private CardValidatorChain cardValidatorChain;

    @BeforeEach
    public void init() {
        cardValidatorChain = new CardValidatorChain();
    }

    @Test
    void validateBadCardNumber() {
        Card card = new Card();
        card.setCardNumber(-323L);
        assertThrows(IllegalArgumentException.class, () -> cardValidatorChain.validate(card));
    }

    @Test
    void validateGoodCardNumber() {
        Card card = new Card();
        card.setId(23L);
        card.setCardNumber(35L);
        cardValidatorChain.validate(card);
    }

    @Test
    void validateBadId() {
        Card card = new Card();
        card.setCardNumber(32L);
        card.setId(-32L);
        assertThrows(SecurityException.class, () -> cardValidatorChain.validate(card));
    }

    @Test
    void validateGoodId() {
        Card card = new Card();
        card.setCardNumber(54L);
        card.setId(32L);
        cardValidatorChain.validate(card);
    }
}