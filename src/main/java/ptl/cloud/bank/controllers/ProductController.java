package ptl.cloud.bank.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ptl.cloud.bank.entities.Product;
import ptl.cloud.bank.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/")
    private List<Product> getAlll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    private Product getSingle(@PathVariable Long id) {
        return productService.findById(id);
    }
}
