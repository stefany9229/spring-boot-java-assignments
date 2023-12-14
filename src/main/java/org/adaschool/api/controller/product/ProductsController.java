package org.adaschool.api.controller.product;

import org.adaschool.api.exception.ProductNotFoundException;
import org.adaschool.api.repository.product.Product;
import org.adaschool.api.service.product.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/products/")
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(@Autowired ProductsService productsService) {
        this.productsService = productsService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        productsService.save(product);
        URI createdProductUri = URI.create("");
        return ResponseEntity.created(createdProductUri).body(null);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> allProducts = productsService.all();
        return ResponseEntity.ok(allProducts);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id) {
        Optional<Product> findById = productsService.findById(id);
        if (findById.isEmpty()) {
            throw new ProductNotFoundException(id);
        }
        return ResponseEntity.ok(findById);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateProduct(@RequestBody Product product, @PathVariable("id") String id) {
        Optional<Product> productOptional = productsService.findById(id);
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException(id);
        }
        Product product1 = productsService.update(product, id);
        return ResponseEntity.ok(product1);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") String id) {
        Optional<Product> productOptional = productsService.findById(id);
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException(id);
        }
        productsService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
