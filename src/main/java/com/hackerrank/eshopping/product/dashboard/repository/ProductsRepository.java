package com.hackerrank.eshopping.product.dashboard.repository;

import com.hackerrank.eshopping.product.dashboard.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//@Repository
public interface ProductsRepository extends CrudRepository<Product, Long> {
    public List<Product> findAllByOrderByIdAsc();

    public List<Product> findByCategoryOrderByDiscountedPrice(String category);
//
    @Query(value = "SELECT * FROM Product \n" +
            "WHERE availability= ?2 AND category= ?1 \n" +
            "ORDER BY ((retail_price-discounted_price)/retail_price)*100 DESC, discounted_price ASC, id ASC \n"
            , nativeQuery = true)
    public List<Product> findByCategoryAndAvailability(String category, Boolean availability);
}
