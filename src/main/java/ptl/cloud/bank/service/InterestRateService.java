package ptl.cloud.bank.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ptl.cloud.bank.entities.InterestRate;
import ptl.cloud.bank.entities.Product;
import ptl.cloud.bank.repositories.InterestRateRepository;
import ptl.cloud.bank.security.CustomUserDetailsService;

@Service
public class InterestRateService extends BasicService<InterestRate, InterestRateRepository> {
    private final ProductService productService;

    public InterestRateService(InterestRateRepository repository, CustomUserDetailsService userDetailsService,
            ProductService productService) {
        super(repository, userDetailsService);
        this.productService = productService;
    }

    public InterestRate create(Double percent, String type) {
        InterestRate interestRate = new InterestRate();
        interestRate.setPercent(percent);
        interestRate.setType(type);
        interestRate.setOwner(userDetailsService.getUser());
        return repository.save(interestRate);
    }

    public void delete(Long id) {
        Product product = repository.findById(id).get().getProduct();
        if (product != null) {
            product.setInterestRate(null);
            productService.save(product);
        }
        repository.deleteById(id);
    }

    public InterestRate changePercent(Double newPercentage, Long interestRateId) {
        InterestRate interestRate = repository.findById(interestRateId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "There is no interes rate with this id: " + interestRateId));
        interestRate.setPercent(newPercentage);
        return repository.save(interestRate);
    }
}
