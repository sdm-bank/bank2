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
import ptl.cloud.bank.entities.InterestRate;
import ptl.cloud.bank.entities.Loan;
import ptl.cloud.bank.entities.User;
import ptl.cloud.bank.repositories.LoanRepository;
import ptl.cloud.bank.security.CustomUserDetailsService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class LoanServiceTest {

    private final static Long interestRateId = 2L;
    private final Long DEFAULT_CARD_ID = 124L;
    private final Double DEFAULT_APY = 0.2D;
    @Mock
    private LoanRepository loanRepository;
    @Mock
    private CustomUserDetailsService customUserDetailsService;
    @Mock
    private InterestRateService interestRateService;
    @InjectMocks
    private LoanService loanService;
    private User loggedInUser;
    private Card defaultCard;
    private InterestRate defaultInterestRate;

    @BeforeEach
    public void init() {
        loggedInUser = new User();
        defaultCard = new Card();
        defaultCard.setId(DEFAULT_CARD_ID);
        defaultInterestRate = new InterestRate();
        defaultInterestRate.setId(interestRateId);

        when(loanRepository.save(any())).then(returnsFirstArg());
    }

    @Test
    void create() {
        when(customUserDetailsService.getUser()).thenReturn(loggedInUser);
        when(interestRateService.findById(any())).thenReturn(defaultInterestRate);
        Loan loan = loanService.create(DEFAULT_APY, interestRateId);
        assertEquals(interestRateId, loan.getInterestRate().getId());
        assertEquals(DEFAULT_APY, loan.getApy());
    }
}