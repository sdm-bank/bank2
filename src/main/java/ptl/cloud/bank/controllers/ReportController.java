package ptl.cloud.bank.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ptl.cloud.bank.entities.Report;
import ptl.cloud.bank.service.ReportService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/reports/number/of/transactions")
    public Report getTotalTransactions() {
        return reportService.getNumberOfTransactions();
    }

    @GetMapping("/reports/avg/transaction/balance")
    public Report getAvgBalance() {
        return reportService.getAverageTransactionValue();
    }

    @GetMapping("/reports/total/transaction/balance")
    public Report getTotalBalance() {
        return reportService.getTotalTransactionBalance();
    }
}
