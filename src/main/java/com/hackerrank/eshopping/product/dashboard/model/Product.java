package com.hackerrank.eshopping.product.dashboard.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@DynamicInsert(true)
@DynamicUpdate(true)
public class Product {
    @Id
    private Long id;
    private String name;
    private String category;
    @JsonProperty("retail_price")
    private Double retailPrice;
    @JsonProperty("discounted_price")
    private Double discountedPrice = 0.0;
    private Boolean availability;

    public Product() {
    }

    public Product(Long id, String name, String category, Double retailPrice, Double discountedPrice, Boolean availability) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.retailPrice = retailPrice;
        this.discountedPrice = discountedPrice;
        this.availability = availability;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(Double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("id=" ).append(id).append(", name=").append(name)
               .append(", category=").append(category).append(", retail_price=").append(retailPrice)
        .append(", discounted_price=").append(discountedPrice).append(", availability").append(availability);
        return builder.toString();
    }
}
