
package au.edu.rmit.sept.app.superPrice.controllers;

import au.edu.rmit.sept.app.superPrice.models.Order;
import au.edu.rmit.sept.app.superPrice.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @RequestMapping(value = "/order",
            method = RequestMethod.POST,
            consumes = "application/json")
    public String createOrder(@RequestBody List<Product> products) {
        // Logic to create a new order
        return "Order Created";
    }

    @RequestMapping(value = "/order/{id}",
            method = RequestMethod.GET,
            produces = "application/json")
    public Order getOrder(@PathVariable Long id) {
        // Logic to get an order by ID
        return new Order();
    }

    @RequestMapping(value = "/order/{id}",
            method = RequestMethod.PUT,
            consumes = "application/json")
    public String updateOrder(@PathVariable Long id, @RequestBody String status) {
        // Logic to update the order status
        return "Order Updated";
    }
}
