package com.hackerrank.eshopping.product.dashboard.service;

import com.hackerrank.eshopping.product.dashboard.exception.ResourceNotFoundException;
import com.hackerrank.eshopping.product.dashboard.model.Product;
import com.hackerrank.eshopping.product.dashboard.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.ws.http.HTTPException;

@Service
@Transactional
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductsRepository productsRepository;


//    public ProductsServiceImpl(ProductsRepository productsRepository) {
//        this.productsRepository = productsRepository;
//    }

    @Override
    public ResponseEntity addProduct(Product product) {

        if (!productsRepository.existsById(product.getId())) {
            productsRepository.save(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public ResponseEntity updateProduct(Product product, long productId) {
        try {
            Product retrievedProduct = getProductById(productId);
            retrievedProduct.setAvailability(product.getAvailability());
            retrievedProduct.setCategory(product.getCategory());
            retrievedProduct.setDiscountedPrice(product.getDiscountedPrice());
            productsRepository.save(retrievedProduct);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Error error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public Product getProductById(Long productId) {
        return productsRepository.findById(productId)
                                 .orElseThrow((() -> new ResourceNotFoundException()));
    }

    @Override
    public @NotNull Iterable<Product> getAllProducts() {
        return productsRepository.findAllByOrderByIdAsc();
    }

    @Override
    public @NotNull Iterable<Product> filterProductsByCategory(String category) {
        return productsRepository.findByCategoryOrderByDiscountedPrice(category);
    }

    public @NotNull Iterable<Product> filterProduct(String category, Boolean availability) {
        return productsRepository.findByCategoryAndAvailability(category, availability);
    }

}
