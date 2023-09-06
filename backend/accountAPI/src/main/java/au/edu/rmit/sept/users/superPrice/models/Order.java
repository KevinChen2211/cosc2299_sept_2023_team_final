package au.edu.rmit.sept.app.superPrice.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id; // Assuming this is a database ID; kept for compatibility
    private String orderId; // New field
    private Date dateOrdered; // New field
    private List<Product> products;
    private String status;
    private String address; // New field
    private boolean isFastDelivery; // New field
    private Date dateRangeStart; // New field
    private Date dateRangeEnd; // New field
    private String timeRangeStart; // New field
    private String timeRangeEnd; // New field

    public String getOrderInfo() {
        return "Order ID: " + orderId +
               ", Database ID: " + id +
               ", Date Ordered: " + dateOrdered +
               ", Status: " + status +
               ", Address: " + address +
               ", Is Fast Delivery: " + isFastDelivery +
               ", Date Range: " + dateRangeStart + " to " + dateRangeEnd +
               ", Time Range: " + timeRangeStart + " to " + timeRangeEnd;
    }
    
}
