package ptl.cloud.bank.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import ptl.cloud.bank.entities.Card;
import ptl.cloud.bank.entities.User;
import ptl.cloud.bank.repositories.CardRepository;
import ptl.cloud.bank.security.CustomUserDetailsService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class CardServiceTest {

    private User loggedInUser;
    @Mock
    private CustomUserDetailsService customUserDetailsService;

    @Mock
    private CardRepository cardRepository;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private CardService cardService;

    @BeforeEach
    public void init() {
        loggedInUser = new User();
        when(cardRepository.save(any())).then(returnsFirstArg());
        when(customUserDetailsService.getUser()).thenReturn(loggedInUser);
    }

    @Test
    void create() {
        Card card = cardService.create();
        assertEquals(loggedInUser, card.getOwner());
    }
}