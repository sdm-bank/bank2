package ptl.cloud.bank.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ptl.cloud.bank.entities.Account;
import ptl.cloud.bank.entities.Card;
import ptl.cloud.bank.entities.User;
import ptl.cloud.bank.repositories.CardRepository;
import ptl.cloud.bank.security.CustomUserDetailsService;

import java.util.ArrayList;
import java.util.Random;

@Service
public class CardService extends BasicService<Card, CardRepository> {
    private final AccountService accountService;

    public CardService(CardRepository repository, CustomUserDetailsService userDetailsService,
            @Lazy AccountService accountService) {
        super(repository, userDetailsService);
        this.accountService = accountService;
    }

    public Card create() {
        Random random = new Random();
        int cvc = random.nextInt() % 1000;
        long cardNumber = random.nextLong();
        User user = userDetailsService.getUser();
        Card card = new Card();
        card.setCardNumber(cardNumber);
        card.setCvc(cvc);
        card.setOwner(user);
        card.setAccounts(new ArrayList<>());
        return repository.save(card);
    }

    public void delete(Long cardId) {
        repository.findById(cardId).get().getAccounts().stream().map(this::detachAccount).toList();
        repository.deleteById(cardId);
    }

    private Account detachAccount(Account account) {
        account.setCard(null);
        return accountService.save(account);
    }
}
