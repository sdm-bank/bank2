package ptl.cloud.bank.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ptl.cloud.bank.entities.InterestRate;
import ptl.cloud.bank.service.InterestRateService;

import java.util.List;

@RestController
@RequestMapping("/api/interestRate")
@RequiredArgsConstructor
public class InterestRateController {
    private final InterestRateService interestRateService;

    @GetMapping("/")
    public List<InterestRate> findAll() {
        return interestRateService.getAll();
    }

    @GetMapping("/{id}")
    public InterestRate getOne(@PathVariable("id") Long id) {
        return interestRateService.findById(id);
    }

    @PostMapping("/")
    public InterestRate create(@RequestParam("percent") Double percent, @RequestParam("type") String type) {
        return interestRateService.create(percent, type);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        interestRateService.delete(id);
        return ResponseEntity.ok("Deleted");
    }

    @PutMapping("{id}/change/percentage")
    public InterestRate changePercentage(@PathVariable("id") Long id, @RequestParam("percentage") Double percentage) {
        return interestRateService.changePercent(percentage, id);
    }
}
