package au.edu.rmit.sept.app.Product.models;

import java.io.Serializable;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Chain implements Serializable {

    // private String chainId;
    private String name;
    // private List<Product> products;
    // private List<Store> stores;
    // private List<String> reviews;
    private Double avgRating;

    // // Constructor
    // public Chain(String chainId, String name, List<Product> products, 
    //              List<Store> stores, List<String> reviews, Double avgRating) {
    //     this.chainId = chainId;
    //     this.name = name;
    //     this.products = products;
    //     this.stores = stores;
    //     this.reviews = reviews;
    //     this.avgRating = avgRating;
    // }

    // // Getters and setters
    // public String getChainId() {
    //     return chainId;
    // }

    // public void setChainId(String chainId) {
    //     this.chainId = chainId;
    // }

    // public String getName() {
    //     return name;
    // }

    // public void setName(String name) {
    //     this.name = name;
    // }

    // public List<Product> getProducts() {
    //     return products;
    // }

    // public void setProducts(List<Product> products) {
    //     this.products = products;
    // }

    // public List<Store> getStores() {
    //     return stores;
    // }

    // public void setStores(List<Store> stores) {
    //     this.stores = stores;
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
