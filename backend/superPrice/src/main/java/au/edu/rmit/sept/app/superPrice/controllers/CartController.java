
package au.edu.rmit.sept.app.superPrice.controllers;

import au.edu.rmit.sept.app.superPrice.models.Product;
import au.edu.rmit.sept.app.superPrice.models.ItemRequest;
import au.edu.rmit.sept.app.superPrice.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@SessionAttributes("ID_SESSION_SHOPPING_CART")
public class CartController {

    private List<Product> products = new ArrayList<Product>();

    @Autowired
    private ProductService productService;

    @PostConstruct
    public void configure() {
        products = (List<Product>) productService.getProducts();
    }

    @RequestMapping(value = "/products",
            method = RequestMethod.GET,
            produces = "application/json")
    public List<Product> getProducts() {
        return products;
    }

    @RequestMapping(value = "/cart/items",
            method = RequestMethod.GET,
            produces = "application/json")
    public List<Product> getCartItems(@SessionAttribute("ID_SESSION_SHOPPING_CART") List<Product> shoppingCart) {
        return shoppingCart;
    }

    @RequestMapping(value = "/cart/item",
            method = RequestMethod.POST,
            consumes = "application/json")
    public String addItem(@RequestBody ItemRequest request, @SessionAttribute("ID_SESSION_SHOPPING_CART") List<Product> shoppingCart) {
        // Existing logic from example...
        return "OK";
    }

    @RequestMapping(value = "/cart/item",
            method = RequestMethod.DELETE,
            consumes = "application/json")
    public String deleteItem(@RequestBody ItemRequest request, @SessionAttribute("ID_SESSION_SHOPPING_CART") List<Product> shoppingCart) {
        // Existing logic from example...
        return "OK";
    }

    
    
    @RequestMapping(value = "/cart",
            method = RequestMethod.GET,
            produces = "application/json")
    public List<Product> getAllCartItems(@SessionAttribute("ID_SESSION_SHOPPING_CART") List<Product> shoppingCart) {
        return shoppingCart;
    }

    // @RequestMapping(value = "/cart",
    //         method = RequestMethod.DELETE)
    // public String emptyCart(Model model) {
    
    //     // Existing logic from example...
    //     return "OK";
    // }

    private Optional<Product> getProductById(Stream<Product> stream, Long id) {
        // Existing logic from example...
        return Optional.empty();
    }
}