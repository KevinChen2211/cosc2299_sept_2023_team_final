
package au.edu.rmit.sept.app.superPrice.repositories;

import au.edu.rmit.sept.app.superPrice.models.Order;

import java.util.List;

public interface OrderRepository {
    public Order save(Order order);
    public Order findById(Long id);
    public List<Order> findAll();
    public String updateStatus(Long id, String status);
}
