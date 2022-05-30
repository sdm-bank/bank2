package ptl.cloud.bank.validation.card;

import lombok.NoArgsConstructor;
import ptl.cloud.bank.entities.Card;

@NoArgsConstructor
public class IdValidationChainPart extends ValidationHandler {
    public IdValidationChainPart(ValidationHandler next) {
        super(next);
    }

    @Override
    public void validate(Card card) {
        if (card.getId() < 0L) {
            throw new SecurityException("Id is < 0");
        } else if (next != null) {
            next.validate(card);
        }
    }
}
