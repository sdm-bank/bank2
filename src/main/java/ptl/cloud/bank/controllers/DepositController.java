package ptl.cloud.bank.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ptl.cloud.bank.entities.Deposit;
import ptl.cloud.bank.service.DepositService;

import java.util.List;

@RestController
@RequestMapping("/api/deposit")
@RequiredArgsConstructor
public class DepositController {
    private final DepositService depositService;

    @GetMapping("/")
    public List<Deposit> getAll() {
        return depositService.getAll();
    }

    @GetMapping("/{id}")
    public Deposit findOne(@PathVariable("id") Long id) {
        return depositService.findById(id);
    }

    @PostMapping("/")
    public Deposit create(@RequestParam("minimalDuration") Long minimalDuration,
            @RequestParam("interestRateId") Long interestRateId) {
        return depositService.create(minimalDuration, interestRateId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        depositService.delete(id);
        return ResponseEntity.ok("Deleted");
    }
}
