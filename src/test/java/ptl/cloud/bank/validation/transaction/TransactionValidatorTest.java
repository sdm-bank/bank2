package ptl.cloud.bank.validation.transaction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ptl.cloud.bank.entities.Transaction;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TransactionValidatorTest {

    private TransactionValidator transactionValidator;

    @BeforeEach
    public void init() {
        transactionValidator = new TransactionValidator();
    }

    @Test
    void validate() {
        Transaction dummy = new Transaction();
        boolean validationResponse = transactionValidator.validate(dummy);
        assertTrue(validationResponse);
    }
}