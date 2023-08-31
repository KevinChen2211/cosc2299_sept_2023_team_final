package au.edu.rmit.sept.app.superPrice.models;
import java.io.Serializable;
// public class Product (Long id, String title) {
import java.math.BigDecimal;

public class Product implements Serializable {
    	/** Id of the product */
	private Long id;
	
	/** Name of the product */
	private String name;
	
	/** Price of the product */
	private BigDecimal price;
	
	/** Quantity of the product that will be added to the shopping cart */
	private Integer quantity;

    public Product(Long id, String name, BigDecimal price,Integer quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    // Method 1: Static Factory Method
    public static Product createNewProduct(Long id, String name, BigDecimal price, Integer quantity) {
        return new Product(id, name, price, quantity);
    }

    // Method 2: Clone an Existing Product
    public Product clone() {
        return new Product(this.id, this.name, this.price, this.quantity);
    }

    // Method 3: Builder Class
    public static class Builder {
        private Long id;
        private String name;
        private BigDecimal price;
        private Integer quantity;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder setQuantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public Product build() {
            return new Product(id, name, price, quantity);
        }
    }

    public String giveTitle() {
        return (name == null ? "No title" : name);
    }
}
