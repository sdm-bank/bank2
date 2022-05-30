package ptl.cloud.bank.validation.card;

import lombok.NoArgsConstructor;
import ptl.cloud.bank.entities.Card;

@NoArgsConstructor
public class CardNumberValidationChainPart extends ValidationHandler {
    public CardNumberValidationChainPart(ValidationHandler next) {
        super(next);
    }

    @Override
    public void validate(Card card) {
        if (card.getCardNumber() < 0L) {
            throw new IllegalArgumentException("Card number < 0");
        } else if (next != null) {
            next.validate(card);
        }
    }
}
