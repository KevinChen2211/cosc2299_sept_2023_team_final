
// package au.edu.rmit.sept.app.superPrice.controllers;

// import au.edu.rmit.sept.app.superPrice.models.Order;
// import au.edu.rmit.sept.app.superPrice.models.Product;
// import au.edu.rmit.sept.app.superPrice.services.OrderService;
// import au.edu.rmit.sept.app.superPrice.services.ProductService;
// import au.edu.rmit.sept.app.superPrice.services.ShoppingCartService;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("order")
// public class OrderController {

//     @Autowired
//     ProductService productService;

//     @Autowired
//     ShoppingCartService cartService;

//     @Autowired
//     OrderService orderService;

//     // @GetMapping("/info")
//     // public ResponseEntity<Object> getInfo(){
//     //     return new ResponseEntity<>(orderService.getOrderById(),HttpStatus.OK);
//     // }

//     @GetMapping("all")
//     public ResponseEntity<Object> getAllProducts() {
//         return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
//     }
    
//     // @GetMapping("/product")
//     // public ResponseEntity<Object> getProductById() {
//     //     Long id = (long) 1;
//     //     Product productOptional = productService.findrProductByID(id);
//     //     if (productOptional == null)
//     //         return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
//     //     return new ResponseEntity<>(productOptional, HttpStatus.OK);
//     // }


//     // @RequestMapping(value = "/order",
//     //         method = RequestMethod.POST,
//     //         consumes = "application/json")
//     // public String createOrder(@RequestBody List<Product> products) {
//     //     // Logic to create a new order
//     //     return "Order Created";
//     // }

//     // @RequestMapping(value = "/order/{id}",
//     //         method = RequestMethod.GET,
//     //         produces = "application/json")
//     // public Order getOrder(@PathVariable Long id) {
//     //     // Logic to get an order by ID
//     //     return new Order();
//     // }

//     // @RequestMapping(value = "/order/{id}",
//     //         method = RequestMethod.PUT,
//     //         consumes = "application/json")
//     // public String updateOrder(@PathVariable Long id, @RequestBody String status) {
//     //     // Logic to update the order status
//     //     return "Order Updated";
//     // }
// }
