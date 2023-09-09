package au.edu.rmit.sept.app.superPrice.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    // private String id;
    private String productID; // New field
    private String name;
    private String imageLocation; // New field
    private String category; // New field
    private String subcategory; // New field
    private String chain; // New field
    private BigDecimal price;
    private Integer quantity;
    private List<String> reviews; // New field
    private Double avgRating; // New field

    // // Enum for Category
    // public enum Category {
    //     ELECTRONICS, FOOD, CLOTHING, HOME
    // }

    // // Enum for SubCategory
    // public enum SubCategory {
    //     MOBILE, GROCERY, MEN, FURNITURE
    // }

    public Product(String productId, String name, String productImageLocation,
            String category, String subCategory, String chain,
            BigDecimal price, int quantity, List<String> reviews, double avgRating) {
        // this.id = id;
        this.productID = productId;
        this.name = name;
        this.imageLocation = productImageLocation;
        this.category = category;
        this.subcategory = subCategory;
        this.chain = chain;
        this.price = price;
        this.quantity = quantity;
        this.reviews = reviews;
        this.avgRating = avgRating;
    }

    // Existing and New fields in the constructor
    // public Product(String id, String productId, String name, String productImageLocation,
    //                String category, String subCategory, String chain,
    //                BigDecimal price, Integer quantity, List<String> reviews,
    //                Double avgRating) {
    //     this.id = id;
    //     this.productId = productId;
    //     this.name = name;
    //     this.productImageLocation = productImageLocation;
    //     this.category = category;
    //     this.subCategory = subCategory;
    //     this.chain = chain;
    //     this.price = price;
    //     this.quantity = quantity;
    //     this.reviews = reviews;
    //     this.avgRating = avgRating;
    // }

    // // Existing and New Getters and Setters

    // public Long getId() {
    //     return id;
    // }

    // public void setId(Long id) {
    //     this.id = id;
    // }

    // public String getProductId() {
    //     return productId;
    // }

    // public void setProductId(String productId) {
    //     this.productId = productId;
    // }

    // public String getName() {
    //     return name;
    // }

    // public void setName(String name) {
    //     this.name = name;
    // }

    // public String getProductImageLocation() {
    //     return productImageLocation;
    // }

    // public void setProductImageLocation(String productImageLocation) {
    //     this.productImageLocation = productImageLocation;
    // }

    // public Category getCategory() {
    //     return category;
    // }

    // public void setCategory(Category category) {
    //     this.category = category;
    // }

    // public SubCategory getSubCategory() {
    //     return subCategory;
    // }

    // public void setSubCategory(SubCategory subCategory) {
    //     this.subCategory = subCategory;
    // }

    // public String getChain() {
    //     return chain;
    // }

    // public void setChain(String chain) {
    //     this.chain = chain;
    // }

    // public BigDecimal getPrice() {
    //     return price;
    // }

    // public void setPrice(BigDecimal price) {
    //     this.price = price;
    // }

    // public Integer getQuantity() {
    //     return quantity;
    // }

    // public void setQuantity(Integer quantity) {
    //     this.quantity = quantity;
    // }

    // public List<String> getReviews() {
    //     return reviews;
    // }

    // public void setReviews(List<String> reviews) {
    //     this.reviews = reviews;
    // }

    // public Double getAvgRating() {
    //     return avgRating;
    // }

    // public void setAvgRating(Double avgRating) {
    //     this.avgRating = avgRating;
    // }
}
