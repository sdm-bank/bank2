package ptl.cloud.bank.validation.transaction;

import org.springframework.stereotype.Component;
import ptl.cloud.bank.entities.Transaction;
import ptl.cloud.bank.validation.Validator;

@Component
public class TransactionValidator implements Validator<Transaction> {

    @Override
    public boolean validate(Transaction object) {
        // super secret validation strategy :>
        return true;
    }
}
