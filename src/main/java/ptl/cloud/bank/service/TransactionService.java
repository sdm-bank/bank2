package ptl.cloud.bank.service;

import org.springframework.stereotype.Service;
import ptl.cloud.bank.entities.Product;
import ptl.cloud.bank.entities.Transaction;
import ptl.cloud.bank.repositories.TransactionRepository;
import ptl.cloud.bank.security.CustomUserDetailsService;

import java.util.List;

@Service
public class TransactionService extends BasicService<Transaction, TransactionRepository> {
    private final ProductService productService;

    public TransactionService(TransactionRepository repository, CustomUserDetailsService userDetailsService,
            ProductService productService) {
        super(repository, userDetailsService);
        this.productService = productService;
    }

    public Transaction createAndSave(double value, String description) {
        Transaction transaction = new Transaction();
        transaction.setValue(value);
        transaction.setDescription(description);
        return repository.save(transaction);
    }

    public Transaction makeTransaction(Long fromId, Long toId, double value) {
        Product from = productService.findById(fromId);
        Product to = productService.findById(toId);
        String description = "Sending money from " + from.getId() + " to: " + to.getId();
        Transaction transaction = createAndSave(value, description);
        transaction.setProducts(List.of(from, to));
        from.getHistoryOfOperations().add(transaction);
        to.getHistoryOfOperations().add(transaction);
        productService.save(from);
        productService.save(to);
        return transaction;
    }
}
