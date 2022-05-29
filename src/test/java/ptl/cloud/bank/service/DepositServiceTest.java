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
import ptl.cloud.bank.entities.Deposit;
import ptl.cloud.bank.entities.InterestRate;
import ptl.cloud.bank.entities.User;
import ptl.cloud.bank.repositories.DepositRepository;
import ptl.cloud.bank.security.CustomUserDetailsService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class DepositServiceTest {

    private final static Long DEFAULT_CARD_ID = 2L;
    private final static Long minimalDuration = 10L;
    private final static Long interestRateId = 2L;
    @Mock
    private DepositRepository depositRepository;
    @Mock
    private CustomUserDetailsService customUserDetailsService;
    @Mock
    private InterestRateService interestRateService;
    @InjectMocks
    private DepositService depositService;
    private InterestRate defaultInterestRate;
    private User loggedInUser;
    private Card DEFAULT_CARD;

    @BeforeEach
    public void init() {
        loggedInUser = new User();
        DEFAULT_CARD = new Card();
        defaultInterestRate = new InterestRate();
        defaultInterestRate.setId(interestRateId);
        DEFAULT_CARD.setId(DEFAULT_CARD_ID);
        when(depositRepository.save(any())).then(returnsFirstArg());
        when(customUserDetailsService.getUser()).thenReturn(loggedInUser);
    }

    @Test
    void create() {
        when(interestRateService.findById(any())).thenReturn(defaultInterestRate);
        Deposit deposit = depositService.create(minimalDuration, interestRateId);
        assertEquals(interestRateId, deposit.getInterestRate().getId());
        assertEquals(minimalDuration, deposit.getMinimalDuration());
    }
}