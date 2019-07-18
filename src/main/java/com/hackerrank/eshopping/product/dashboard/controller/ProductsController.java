package com.hackerrank.eshopping.product.dashboard.controller;

import com.hackerrank.eshopping.product.dashboard.model.Product;
import com.hackerrank.eshopping.product.dashboard.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/products")
public class ProductsController {

    @Autowired
    private ProductsService productsServices;

    @GetMapping
    public @NotNull Iterable<Product> getAllProducts(@RequestParam(required = false) String category, @RequestParam(required = false) Boolean availability) {
        if (category != null && category.length() > 0 && availability != null) {
            return productsServices.filterProduct(category, availability);
        } else if (category != null && category.length() > 0) {
            return productsServices.filterProductsByCategory(category);
        }
        return productsServices.getAllProducts();
    }

    @GetMapping(value = "/{product_id}")
    public Product GetProduct(@PathVariable Long product_id) {
        return productsServices.getProductById(product_id);
    }

    @PostMapping
    public ResponseEntity addProduct(@RequestBody Product product) {
        return productsServices.addProduct(product);
    }

    @PutMapping(value = "/{product_id}")
    public ResponseEntity updateProduct(@RequestBody Product product, @PathVariable Long product_id) {
        product.setId(product_id);
        return productsServices.updateProduct(product, product_id);
    }
}
