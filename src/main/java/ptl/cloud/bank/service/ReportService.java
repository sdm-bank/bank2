package ptl.cloud.bank.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ptl.cloud.bank.entities.Product;
import ptl.cloud.bank.entities.Report;
import ptl.cloud.bank.entities.Transaction;
import ptl.cloud.bank.repositories.ReportRepository;
import ptl.cloud.bank.repositories.UserRepository;
import ptl.cloud.bank.security.CustomUserDetailsService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final UserRepository userRepository;
    private final ProductService productService;
    private final CustomUserDetailsService userDetailsService;
    private final ReportRepository reportRepository;
    private final TransactionService transactionService;

    public Report getNumberOfTransactions() {
        List<Product> products = productService.getAll();
        int total = products.stream().map(Product::getHistoryOfOperations).map(List::size).reduce(0, Integer::sum);
        return createReport(total, Report.Type.TOTAL_TRANSACTIONS);
    }

    public Report getTotalTransactionBalance() {
        List<Transaction> transactions = transactionService.getAll();
        double total = transactions.stream().map(Transaction::getValue).reduce(0d, Double::sum);
        return createReport(total, Report.Type.SUM_OF_BALANCES);
    }

    public Report getAverageTransactionValue() {
        double totalTransactions = getNumberOfTransactions().getResult();
        double totalBalance = getTotalTransactionBalance().getResult();
        double avg = totalBalance / totalTransactions;
        return createReport(avg, Report.Type.AVERAGE_BALANCE);
    }

    private Report createReport(double result, Report.Type type) {
        Report report = new Report();
        report.setResult(result);
        report.setType(type);
        report.setOwner(userDetailsService.getUser());
        return reportRepository.save(report);
    }
}
