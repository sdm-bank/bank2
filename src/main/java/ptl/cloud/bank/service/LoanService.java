package ptl.cloud.bank.service;

import org.springframework.stereotype.Service;
import ptl.cloud.bank.entities.InterestRate;
import ptl.cloud.bank.entities.Loan;
import ptl.cloud.bank.entities.User;
import ptl.cloud.bank.repositories.LoanRepository;
import ptl.cloud.bank.security.CustomUserDetailsService;

import java.util.ArrayList;

@Service
public class LoanService extends BasicService<Loan, LoanRepository> {
    private final InterestRateService interestRateService;

    public LoanService(LoanRepository repository, CustomUserDetailsService userDetailsService,
            InterestRateService interestRateService) {
        super(repository, userDetailsService);
        this.interestRateService = interestRateService;
    }

    public Loan create(Double apy, Long interestRateId) {
        Loan loan = new Loan();
        User user = userDetailsService.getUser();
        InterestRate interestRate = interestRateService.findById(interestRateId);
        loan.setInterestRate(interestRate);
        loan.setBalance(0d);
        loan.setHistoryOfOperations(new ArrayList<>());
        loan.setOwner(user);
        loan.setApy(apy);
        return repository.save(loan);
    }

    ;

    public void delete(Long loadId) {
        repository.deleteById(loadId);
    }
}
