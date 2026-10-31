
package com.abcshop.abcshop.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.abcshop.abcshop.model.Product;
import com.abcshop.abcshop.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 1. Retrieve all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 2. Add a new product
    public Product addProduct(Product product) {

        // Prevent users from manually specifying an ID
        product.setId(0);

        return productRepository.save(product);
    }

    // 3. Sell a product
    @Transactional
    public Product sellProduct(int productId, int quantity) {

        if (quantity <= 0) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Selling quantity must be greater than zero"
            );
        }

        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Product not found"
            ));

        if (product.getQuantity() < quantity) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Insufficient stock"
            );
        }

        product.setQuantity(product.getQuantity() - quantity);

        return productRepository.save(product);
    }
}
