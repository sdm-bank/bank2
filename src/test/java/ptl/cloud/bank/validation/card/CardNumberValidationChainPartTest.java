package ptl.cloud.bank.validation.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ptl.cloud.bank.entities.Card;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CardNumberValidationChainPartTest {
    private CardNumberValidationChainPart cardNumberValidationChainPart;

    @BeforeEach
    public void init() {
        cardNumberValidationChainPart = new CardNumberValidationChainPart();
    }

    @Test
    void validateBadInput() {
        Card card = new Card();
        card.setCardNumber(-13L);
        assertThrows(IllegalArgumentException.class, () -> cardNumberValidationChainPart.validate(card));
    }

    @Test
    void validateGoodInput() {
        Card card = new Card();
        card.setCardNumber(235L);
        cardNumberValidationChainPart.validate(card);
    }
}