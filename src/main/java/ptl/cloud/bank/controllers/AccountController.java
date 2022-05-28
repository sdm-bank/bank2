package ptl.cloud.bank.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ptl.cloud.bank.entities.Account;
import ptl.cloud.bank.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/")
    public Account create(@RequestParam(name = "currency") String currency, @RequestParam("card_id") Long cardId) {
        return accountService.create(currency, cardId);
    }

    @PutMapping("{accId}/card/add/{cardId}")
    public Account addCard(@PathVariable("accId") Long accountId, @PathVariable("cardId") Long cardId) {
        return accountService.addCard(cardId, accountId);
    }

    @GetMapping("/{id}")
    public Account getOne(@PathVariable("id") Long id) {
        return accountService.findById(id);
    }

    @GetMapping("/")
    public List<Account> getAll() {
        return accountService.getAll();
    }

    @DeleteMapping("/{id}")
    public Account close(@PathVariable("id") Long id) {
        return accountService.close(id);
    }
}
