package ptl.cloud.bank.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import ptl.cloud.bank.entities.Action;
import ptl.cloud.bank.entities.Product;
import ptl.cloud.bank.entities.Report;
import ptl.cloud.bank.entities.Transaction;
import ptl.cloud.bank.repositories.ReportRepository;
import ptl.cloud.bank.repositories.UserRepository;
import ptl.cloud.bank.security.CustomUserDetailsService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class ReportServiceTest {


    @Mock
    private UserRepository userRepository;
    @Mock
    private ProductService productService;
    @Mock
    private CustomUserDetailsService userDetailsService;
    @Mock
    private ReportRepository reportRepository;
    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private ReportService reportService;
    @Mock
    private CustomUserDetailsService customUserDetailsService;

    @BeforeEach
    public void init() {
        when(reportRepository.save(any())).then(returnsFirstArg());
    }

    @Test
    void getNumberOfTransactions() {
        List<Action> historyOfOperations = new ArrayList<>();
        historyOfOperations.addAll(List.of(new Action(), new Action(), new Action()));
        Product p1 = new Product();
        p1.setHistoryOfOperations(historyOfOperations);
        Product p2 = new Product();
        p2.setHistoryOfOperations(historyOfOperations);
        when(productService.getAll()).thenReturn(List.of(p1, p2));
        Report report = reportService.getNumberOfTransactions();
        assertEquals(6D, report.getResult());
        assertEquals(Report.Type.TOTAL_TRANSACTIONS, report.getType());
    }

    @Test
    void getTotalTransactionBalance() {
        Transaction t1 = new Transaction();
        t1.setValue(2D);
        Transaction t2 = new Transaction();
        t2.setValue(3D);
        Transaction t3 = new Transaction();
        t3.setValue(4D);
        Transaction t4 = new Transaction();
        t4.setValue(5D);
        when(transactionService.getAll()).thenReturn(List.of(t1, t2, t3, t4));
        Report report = reportService.getTotalTransactionBalance();
        assertEquals(14D, report.getResult());
        assertEquals(Report.Type.SUM_OF_BALANCES, report.getType());
    }

    @Test
    void getAverageTransactionValue() {
        Transaction t1 = new Transaction();
        t1.setValue(2D);
        Transaction t2 = new Transaction();
        t2.setValue(3D);
        Transaction t3 = new Transaction();
        t3.setValue(4D);
        Transaction t4 = new Transaction();
        t4.setValue(5D);
        when(transactionService.getAll()).thenReturn(List.of(t1, t2, t3, t4));
        List<Action> historyOfOperations = new ArrayList<>();
        historyOfOperations.addAll(List.of(new Action(), new Action(), new Action()));
        Product p1 = new Product();
        p1.setHistoryOfOperations(historyOfOperations);
        Product p2 = new Product();
        p2.setHistoryOfOperations(historyOfOperations);
        when(productService.getAll()).thenReturn(List.of(p1, p2));
        Report report = reportService.getAverageTransactionValue();
        assertEquals(14D / 6D, report.getResult());
        assertEquals(Report.Type.AVERAGE_BALANCE, report.getType());
    }
}