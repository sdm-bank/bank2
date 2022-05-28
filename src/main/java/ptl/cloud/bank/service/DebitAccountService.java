package ptl.cloud.bank.service;

import org.springframework.stereotype.Service;
import ptl.cloud.bank.entities.Card;
import ptl.cloud.bank.entities.DebitAccount;
import ptl.cloud.bank.entities.User;
import ptl.cloud.bank.repositories.DebitAccountRepository;
import ptl.cloud.bank.security.CustomUserDetailsService;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class DebitAccountService extends BasicService<DebitAccount, DebitAccountRepository> {
    private final CardService cardService;

    public DebitAccountService(DebitAccountRepository repository, CustomUserDetailsService userDetailsService,
            CardService cardService) {
        super(repository, userDetailsService);
        this.cardService = cardService;
    }

    public DebitAccount create(String currency, Long cardId) {
        User user = userDetailsService.getUser();
        Card card = cardService.findById(cardId);
        DebitAccount account = new DebitAccount();
        account.setOpenDate(LocalDateTime.now());
        account.setCurrency(currency);
        account.setCard(card);
        account.setOwner(user);
        account.setHistoryOfOperations(new ArrayList<>());
        account.setBalance(0d);
        account.setDebit(0d);
        return repository.save(account);
    }

    public DebitAccount close(Long accountId) {
        DebitAccount account = findById(accountId);
        account.setCloseDate(LocalDateTime.now());
        return repository.save(account);
    }

    public DebitAccount addCard(Long cardId, Long accountId) {
        Card card = cardService.findById(cardId);
        DebitAccount account = findById(accountId);
        account.setCard(card);
        return save(account);
    }
}
