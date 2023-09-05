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
    
    // Getters and Setters

    // public Long getId() {
    //     return id;
    // }

    // public void setId(Long id) {
    //     this.id = id;
    // }

    // public String getOrderId() {
    //     return orderId;
    // }

    // public void setOrderId(String orderId) {
    //     this.orderId = orderId;
    // }

    // public Date getDateOrdered() {
    //     return dateOrdered;
    // }

    // public void setDateOrdered(Date dateOrdered) {
    //     this.dateOrdered = dateOrdered;
    // }

    // public List<Product> getProducts() {
    //     return products;
    // }

    // public void setProducts(List<Product> products) {
    //     this.products = products;
    // }

    // public String getStatus() {
    //     return status;
    // }

    // public void setStatus(String status) {
    //     this.status = status;
    // }

    // public String getAddress() {
    //     return address;
    // }

    // public void setAddress(String address) {
    //     this.address = address;
    // }

    // public boolean isFastDelivery() {
    //     return isFastDelivery;
    // }

    // public void setFastDelivery(boolean isFastDelivery) {
    //     this.isFastDelivery = isFastDelivery;
    // }

    // public Date getDateRangeStart() {
    //     return dateRangeStart;
    // }

    // public void setDateRangeStart(Date dateRangeStart) {
    //     this.dateRangeStart = dateRangeStart;
    // }

    // public Date getDateRangeEnd() {
    //     return dateRangeEnd;
    // }

    // public void setDateRangeEnd(Date dateRangeEnd) {
    //     this.dateRangeEnd = dateRangeEnd;
    // }

    // public String getTimeRangeStart() {
    //     return timeRangeStart;
    // }

    // public void setTimeRangeStart(String timeRangeStart) {
    //     this.timeRangeStart = timeRangeStart;
    // }

    // public String getTimeRangeEnd() {
    //     return timeRangeEnd;
    // }

    // public void setTimeRangeEnd(String timeRangeEnd) {
    //     this.timeRangeEnd = timeRangeEnd;
    // }

    
}
