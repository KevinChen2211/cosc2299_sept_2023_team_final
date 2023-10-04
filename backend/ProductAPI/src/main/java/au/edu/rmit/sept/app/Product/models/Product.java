package au.edu.rmit.sept.app.Product.models;

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
    private String productID; // New field
    private String name;
    private String imageLocation; // New field
    private String category; // New field
    private String subcategory; // New field
    private String chain; // New field
    private BigDecimal price;
    private Integer quantity;
    private Integer boughtQuantity=0;
    private List<String> reviews; // New field
    private Double avgRating; // New field
}
