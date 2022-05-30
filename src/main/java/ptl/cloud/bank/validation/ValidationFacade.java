package ptl.cloud.bank.validation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ptl.cloud.bank.entities.Card;
import ptl.cloud.bank.entities.Transaction;
import ptl.cloud.bank.validation.card.CardValidatorChain;
import ptl.cloud.bank.validation.exception.ValidationExceptionHandlerFactory;
import ptl.cloud.bank.validation.transaction.TransactionValidator;

@RequiredArgsConstructor
@Slf4j
@Component
public class ValidationFacade {
    private final TransactionValidator transactionValidator;
    private final CardValidatorChain cardValidator;
    private final ValidationExceptionHandlerFactory validationExceptionHandlerFactory;

    public boolean validate(Object object) {
        try {
            if (object.getClass().isAssignableFrom(Transaction.class)) {
                return transactionValidator.validate((Transaction) object);
            }
            if (object.getClass().isAssignableFrom(Card.class)) {
                return cardValidator.validate((Card) object);
            }
            log.error("Validation failed - no validator found");
            return false;
        } catch (Exception e) {
            validationExceptionHandlerFactory.get(e).handle(e);
            return false;
        }
    }
}
