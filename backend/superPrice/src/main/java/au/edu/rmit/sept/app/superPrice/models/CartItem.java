
package au.edu.rmit.sept.app.superPrice.models;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// import lombok.Data;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem implements Serializable {


    private Long producID;
    private String name;
    private int quantity;
    private double price;

    public void setPrice(BigDecimal price) {
        this.price = price.doubleValue(); // Again, be cautious with precision loss
    }

}
