package ptl.cloud.bank.service;

import org.springframework.stereotype.Service;
import ptl.cloud.bank.entities.Account;
import ptl.cloud.bank.entities.Card;
import ptl.cloud.bank.entities.User;
import ptl.cloud.bank.repositories.AccountRepository;
import ptl.cloud.bank.security.CustomUserDetailsService;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class AccountService extends BasicService<Account, AccountRepository> {
    private final CardService cardService;

    public AccountService(AccountRepository repository, CustomUserDetailsService userDetailsService,
            CardService cardService) {
        super(repository, userDetailsService);
        this.cardService = cardService;
    }


    public Account create(String currency, Long cardId) {
        User user = userDetailsService.getUser();
        Card card = cardService.findById(cardId);
        Account account = new Account();
        account.setOpenDate(LocalDateTime.now());
        account.setCurrency(currency);
        account.setCard(card);
        account.setOwner(user);
        account.setHistoryOfOperations(new ArrayList<>());
        account.setBalance(0d);
        return repository.save(account);
    }

    public Account close(Long accountId) {
        Account account = findById(accountId);
        account.setCloseDate(LocalDateTime.now());
        return repository.save(account);
    }

    public Account addCard(Long cardId, Long accountId) {
        Card card = cardService.findById(cardId);
        Account account = findById(accountId);
        account.setCard(card);
        return save(account);
    }
}
