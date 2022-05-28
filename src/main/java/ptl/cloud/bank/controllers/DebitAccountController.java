package ptl.cloud.bank.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ptl.cloud.bank.entities.DebitAccount;
import ptl.cloud.bank.service.DebitAccountService;

import java.util.List;

@RestController
@RequestMapping("/api/debitAccounts")
@RequiredArgsConstructor
public class DebitAccountController {
    private final DebitAccountService debitAccountService;

    @PostMapping("/")
    public DebitAccount create(@RequestParam(name = "currency") String currency, @RequestParam("card_id") Long cardId) {
        return debitAccountService.create(currency, cardId);
    }

    @PutMapping("{accId}/card/add/{cardId}")
    public DebitAccount addCard(@PathVariable("accId") Long accountId, @PathVariable("cardId") Long cardId) {
        return debitAccountService.addCard(cardId, accountId);
    }

    @GetMapping("/{id}")
    public DebitAccount getOne(@PathVariable("id") Long id) {
        return debitAccountService.findById(id);
    }

    @GetMapping("/")
    public List<DebitAccount> getAll() {
        return debitAccountService.getAll();
    }

    @DeleteMapping("/{id}")
    public DebitAccount close(@PathVariable("id") Long id) {
        return debitAccountService.close(id);
    }
}
