package ptl.cloud.bank.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import ptl.cloud.bank.entities.Product;
import ptl.cloud.bank.entities.Transaction;
import ptl.cloud.bank.repositories.TransactionRepository;
import ptl.cloud.bank.security.CustomUserDetailsService;
import ptl.cloud.bank.validation.ValidationFacade;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    private static final double DEFAULT_VALUE = 2d;
    private static final String DEFAULT_DESCRIPTION = "description";
    private static final Long DEFAULT_FROM_ID = 1L;
    private static final Long DEFAULT_TO_ID = 2L;
    @Mock
    private TransactionRepository repository;
    @Mock
    private CustomUserDetailsService userDetailsService;
    @Mock
    private ProductService productService;
    @Mock
    private ValidationFacade validationFacade;
    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    public void init() {
        when(repository.save(any())).then(returnsFirstArg());
    }

    @Test
    void createAndSave() {
        Transaction t = transactionService.createAndSave(DEFAULT_VALUE, DEFAULT_DESCRIPTION);
        assertEquals(DEFAULT_DESCRIPTION, t.getDescription());
        assertEquals(DEFAULT_VALUE, t.getValue());
    }

    @Test
    void makeTransaction() {
        Product from = new Product();
        from.setId(DEFAULT_FROM_ID);
        from.setHistoryOfOperations(new ArrayList<>());
        Product to = new Product();
        to.setId(DEFAULT_TO_ID);
        to.setHistoryOfOperations(new ArrayList<>());
        when(validationFacade.validate(any())).thenReturn(true);
        when(productService.findById(DEFAULT_FROM_ID)).thenReturn(from);
        when(productService.findById(DEFAULT_TO_ID)).thenReturn(to);
        Transaction t = transactionService.makeTransaction(DEFAULT_FROM_ID, DEFAULT_TO_ID, DEFAULT_VALUE);
        assertEquals(DEFAULT_VALUE, t.getValue());
        assertEquals(from, t.getProducts().get(0));
        assertEquals(to, t.getProducts().get(1));
        assertEquals("Sending money from 1 to: 2", t.getDescription());
    }
}