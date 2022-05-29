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
import ptl.cloud.bank.repositories.InterestRateRepository;
import ptl.cloud.bank.security.CustomUserDetailsService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class InterestRateServiceTest {

    private static final Double DEFAULT_PERCENT = 0.2D;
    private static final String DEFAULT_TYPE = "A";
    private final Long DEFAULT_CARD_ID = 124L;
    @Mock
    private InterestRateRepository interestRateRepository;
    @Mock
    private CustomUserDetailsService customUserDetailsService;
    @Mock
    private ProductService productService;
    @InjectMocks
    private InterestRateService interestRateService;
    private Card defaultCard;

    @BeforeEach
    public void init() {
        defaultCard = new Card();
        defaultCard.setId(DEFAULT_CARD_ID);

        when(interestRateRepository.save(any())).then(returnsFirstArg());
    }

    @Test
    void create() {
        InterestRate interestRate = interestRateService.create(DEFAULT_PERCENT, DEFAULT_TYPE);
        assertEquals(DEFAULT_PERCENT, interestRate.getPercent());
        assertEquals(DEFAULT_TYPE, interestRate.getType());
    }

    @Test
    void changePercent() {
        InterestRate ir = new InterestRate();
        ir.setPercent(21D);
        when(interestRateRepository.findById(any())).thenReturn(Optional.of(ir));
        ir = interestRateService.changePercent(3D, 3L);
        assertEquals(3D, ir.getPercent());
    }
}