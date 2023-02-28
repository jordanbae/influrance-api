package tda.tip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tda.tip.entity.Product;
import tda.tip.Repository.ProductRepository;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> show(@PathVariable Integer id) {
        HttpStatus status = HttpStatus.OK;
        Product product = productRepository.findByProduct(id);
        if (product == null) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<Product>(product, status);
    }
}
