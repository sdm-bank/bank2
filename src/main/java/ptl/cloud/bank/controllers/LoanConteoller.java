package ptl.cloud.bank.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ptl.cloud.bank.entities.Loan;
import ptl.cloud.bank.service.LoanService;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanConteoller {
    private final LoanService loanService;

    @GetMapping("/")
    public List<Loan> getAll() {
        return loanService.getAll();
    }

    @GetMapping("/{id}")
    public Loan getOne(@PathVariable("id") Long id) {
        return loanService.findById(id);
    }

    @PostMapping("/")
    public Loan post(@RequestParam("apy") Double apy, @RequestParam("interestRateId") Long interestRateId) {
        return loanService.create(apy, interestRateId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delte(@PathVariable("id") Long id) {
        loanService.delete(id);
        return ResponseEntity.ok("Deleted");
    }
}
