package ptl.cloud.bank.validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import ptl.cloud.bank.entities.Card;
import ptl.cloud.bank.entities.Transaction;
import ptl.cloud.bank.validation.card.CardValidatorChain;
import ptl.cloud.bank.validation.transaction.TransactionValidator;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class ValidationFacadeTest {

    @Mock
    private CardValidatorChain cardValidatorChain;

    @Mock
    private TransactionValidator transactionValidator;

    @InjectMocks
    private ValidationFacade validationFacade;

    @Test
    void validateTransaction() {
        Transaction dummyTransaction = new Transaction();
        validationFacade.validate(dummyTransaction);
        verify(transactionValidator).validate(any());
    }

    @Test
    void validateCard() {
        Card card = new Card();
        validationFacade.validate(card);
        verify(cardValidatorChain).validate(any());
    }
}