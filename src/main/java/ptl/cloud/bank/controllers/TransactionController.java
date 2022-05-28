package ptl.cloud.bank.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ptl.cloud.bank.entities.Transaction;
import ptl.cloud.bank.service.TransactionService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping("/transactions/make")
    public Transaction makeTransaction(@RequestParam("fromId") long fromId, @RequestParam("toId") long toId,
            @RequestParam("value") double value) {
        return transactionService.makeTransaction(fromId, toId, value);
    }
}
