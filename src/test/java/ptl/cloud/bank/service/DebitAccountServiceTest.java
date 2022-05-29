package ptl.cloud.bank.service;

import org.junit.jupiter.api.BeforeEach;
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
import ptl.cloud.bank.entities.DebitAccount;
import ptl.cloud.bank.entities.User;
import ptl.cloud.bank.repositories.DebitAccountRepository;
import ptl.cloud.bank.security.CustomUserDetailsService;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class DebitAccountServiceTest {

    private final static String DEFAULT_CURRENCY = "PLN";
    private final static Long DEFAULT_CARD_ID = 2L;
    @Mock
    private DebitAccountRepository debitAccountRepository;
    @Mock
    private CustomUserDetailsService customUserDetailsService;
    @Mock
    private CardService cardService;
    @InjectMocks
    private DebitAccountService debitAccountService;
    private User loggedInUser;
    private Card DEFAULT_CARD;
    private LocalDateTime defaultNow;

    @BeforeEach
    public void init() {
        loggedInUser = new User();
        DEFAULT_CARD = new Card();
        defaultNow = LocalDateTime.now();
        DEFAULT_CARD.setId(DEFAULT_CARD_ID);
        when(debitAccountRepository.save(any())).then(returnsFirstArg());
        when(customUserDetailsService.getUser()).thenReturn(loggedInUser);
    }

    @Test
    void create() {
        when(cardService.findById(any())).thenReturn(DEFAULT_CARD);
        DebitAccount debitAccount = debitAccountService.create(DEFAULT_CURRENCY, DEFAULT_CARD_ID);
        assertEquals(DEFAULT_CARD_ID, debitAccount.getCard().getId());
        assertEquals(DEFAULT_CURRENCY, debitAccount.getCurrency());
    }

    @Test
    void close() {
        try (MockedStatic<LocalDateTime> mockedDateTime = Mockito.mockStatic(LocalDateTime.class)) {
            mockedDateTime.when(LocalDateTime::now).thenReturn(defaultNow);
            Optional<DebitAccount> a = Optional.of(new DebitAccount());
            when(debitAccountRepository.findAllByIdAndOwner(any(), any())).thenReturn(a);
            Account a2 = debitAccountService.close(3L);
            assertEquals(defaultNow, a2.getCloseDate());
        }
    }

    @Test
    void addCard() {
        Optional<DebitAccount> a = Optional.of(new DebitAccount());
        when(debitAccountRepository.findAllByIdAndOwner(any(), any())).thenReturn(a);
        when(cardService.findById(any())).thenReturn(DEFAULT_CARD);
        Account a2 = debitAccountService.addCard(2L, 3L);
        assertEquals(DEFAULT_CARD_ID, a2.getCard().getId());
    }
}