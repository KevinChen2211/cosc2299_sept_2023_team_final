
package au.edu.rmit.sept.app.superPrice.services;

import au.edu.rmit.sept.app.superPrice.models.Order;
import au.edu.rmit.sept.app.superPrice.models.Product;

import java.util.List;

public interface OrderService {
    public Order createOrder(List<Product> products);
    public Order getOrderById(Long id);
    public String updateOrderStatus(Long id, String status);
}
