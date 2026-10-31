
package com.abcshop.abcshop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import com.abcshop.abcshop.model.Product;
import com.abcshop.abcshop.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    // GET: Retrieve all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // POST: Add a new product
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@Valid @RequestBody Product product) {
        return productService.addProduct(product);
    }

    // POST: Sell a product
    @PostMapping("/{productId}/sell")
    public Product sellProduct(
            @PathVariable int productId,
            @Valid @RequestBody SellRequest request) {

        return productService.sellProduct(
            productId,
            request.quantity()
        );
    }

    public record SellRequest(
        @Min(value = 1, message = "Quantity must be at least 1")
        int quantity
    ) {
    }
}
