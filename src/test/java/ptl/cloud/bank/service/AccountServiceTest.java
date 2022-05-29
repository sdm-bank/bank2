package ptl.cloud.bank.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import ptl.cloud.bank.entities.Account;
import ptl.cloud.bank.entities.Card;
import ptl.cloud.bank.entities.User;
import ptl.cloud.bank.repositories.AccountRepository;
import ptl.cloud.bank.security.CustomUserDetailsService;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    private final Long DEFAULT_CARD_ID = 124L;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private CardService cardService;
    @Mock
    private CustomUserDetailsService customUserDetailsService;
    private User loggedInUser;
    private Card defaultCard;
    private LocalDateTime defaultNow;
    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    public void init() {
        loggedInUser = new User();
        defaultCard = new Card();
        defaultCard.setId(DEFAULT_CARD_ID);
        defaultNow = LocalDateTime.of(2020, 12, 12, 12, 12);

        when(accountRepository.save(any())).then(returnsFirstArg());
    }

    @Test
    @DisplayName("Create new Account")
    void create() {
        when(customUserDetailsService.getUser()).thenReturn(loggedInUser);
        when(cardService.findById(any())).thenReturn(defaultCard);
        Account a = accountService.create("PLN", 124L);
        assertEquals(Long.valueOf(DEFAULT_CARD_ID), a.getCard().getId());
        assertEquals("PLN", a.getCurrency());
    }

    @Test
    @DisplayName("Close account")
    void close() {
        try (MockedStatic<LocalDateTime> mockedDateTime = Mockito.mockStatic(LocalDateTime.class)) {
            mockedDateTime.when(LocalDateTime::now).thenReturn(defaultNow);
            Optional<Account> a = Optional.of(new Account());
            when(accountRepository.findAllByIdAndOwner(any(), any())).thenReturn(a);
            Account a2 = accountService.close(3L);
            assertEquals(defaultNow, a2.getCloseDate());
        }
    }

    @Test
    @DisplayName("Add new card to account")
    void addCard() {
        Optional<Account> a = Optional.of(new Account());
        when(accountRepository.findAllByIdAndOwner(any(), any())).thenReturn(a);
        when(cardService.findById(any())).thenReturn(defaultCard);
        Account a2 = accountService.addCard(2L, 3L);
        assertEquals(DEFAULT_CARD_ID, a2.getCard().getId());
    }
}