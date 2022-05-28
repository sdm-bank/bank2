package ptl.cloud.bank.service;

import org.springframework.stereotype.Service;
import ptl.cloud.bank.entities.Product;
import ptl.cloud.bank.repositories.ProductRepository;
import ptl.cloud.bank.security.CustomUserDetailsService;

@Service
public class ProductService extends BasicService<Product, ProductRepository> {

    public ProductService(ProductRepository repository, CustomUserDetailsService userDetailsService) {
        super(repository, userDetailsService);
    }
}
