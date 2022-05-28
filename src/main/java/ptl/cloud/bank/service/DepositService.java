package ptl.cloud.bank.service;

import org.springframework.stereotype.Service;
import ptl.cloud.bank.entities.Deposit;
import ptl.cloud.bank.entities.InterestRate;
import ptl.cloud.bank.entities.User;
import ptl.cloud.bank.repositories.DepositRepository;
import ptl.cloud.bank.security.CustomUserDetailsService;

import java.util.ArrayList;

@Service
public class DepositService extends BasicService<Deposit, DepositRepository> {
    private final InterestRateService interestRateService;

    public DepositService(DepositRepository repository, CustomUserDetailsService userDetailsService,
            InterestRateService interestRateService) {
        super(repository, userDetailsService);
        this.interestRateService = interestRateService;
    }

    public Deposit create(Long minimalDuration, Long interestRateId) {
        Deposit deposit = new Deposit();
        User user = userDetailsService.getUser();
        InterestRate interestRate = interestRateService.findById(interestRateId);
        deposit.setMinimalDuration(minimalDuration);
        deposit.setBalance(0d);
        deposit.setInterestRate(interestRate);
        deposit.setOwner(user);
        deposit.setHistoryOfOperations(new ArrayList<>());
        return repository.save(deposit);
    }

    public void delete(Long depositId) {
        repository.deleteById(depositId);
    }
}
