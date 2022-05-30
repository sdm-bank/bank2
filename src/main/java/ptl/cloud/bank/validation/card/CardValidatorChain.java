package ptl.cloud.bank.validation.card;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ptl.cloud.bank.entities.Card;
import ptl.cloud.bank.validation.Validator;

@Component
@RequiredArgsConstructor
public class CardValidatorChain implements Validator<Card> {
    private final ValidationHandler validationChain;

    public CardValidatorChain() {
        validationChain = new CardNumberValidationChainPart(new IdValidationChainPart());
    }

    @Override
    public boolean validate(Card object) {
        // super complex validation
        validationChain.validate(object);
        return true;
    }
}
