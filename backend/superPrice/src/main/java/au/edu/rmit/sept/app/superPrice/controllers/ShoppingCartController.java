
package au.edu.rmit.sept.app.superPrice.controllers;

import au.edu.rmit.sept.app.superPrice.models.Product;
import au.edu.rmit.sept.app.superPrice.models.CartItem;
import au.edu.rmit.sept.app.superPrice.services.ProductService;
import au.edu.rmit.sept.app.superPrice.services.ShoppingCartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
// @SessionAttributes("ID_SESSION_SHOPPING_CART")
@RequestMapping("shopping-cart")
public class ShoppingCartController {

    @Autowired
    ProductService productService;

    @Autowired
    ShoppingCartService cartService;

    @GetMapping("views")
    public String viewCarts(Model model) {
        model.addAttribute("CART_ITEMS", cartService.getAllItems());
        return "cart-item";
    }
    
    @GetMapping("add/{id}")
    public String addCart(@PathVariable("id") Long id) {
        Product product = productService.findrProductByID(id);
        if (product != null) {
            CartItem item = new CartItem();
            item.setProducID(product.getId());
            item.setName(product.getName());
            item.setPrice(product.getPrice());
            item.setQuantity(0);
            cartService.add(item);
        }
        return "redirect:/shopping-cart/views";
    }

    // private List<Product> products = new ArrayList<Product>();

    

    // @PostConstruct
    // public void configure() {
    //     products = (List<Product>) productService.getProducts();
    // }

    // @RequestMapping(value = "/products",
    //         method = RequestMethod.GET,
    //         produces = "application/json")
    // public List<Product> getProducts() {
    //     return products;
    // }

    // @RequestMapping(value = "/cart/items",
    //         method = RequestMethod.GET,
    //         produces = "application/json")
    // public List<Product> getCartItems(@SessionAttribute("ID_SESSION_SHOPPING_CART") List<Product> shoppingCart) {
    //     return shoppingCart;
    // }

    // @RequestMapping(value = "/cart/item",
    //         method = RequestMethod.POST,
    //         consumes = "application/json")
    // public String addItem(@RequestBody CartItem request, @SessionAttribute("ID_SESSION_SHOPPING_CART") List<Product> shoppingCart) {
    //     // Existing logic from example...
    //     return "OK";
    // }

    // @RequestMapping(value = "/cart/item",
    //         method = RequestMethod.DELETE,
    //         consumes = "application/json")
    // public String deleteItem(@RequestBody CartItem request, @SessionAttribute("ID_SESSION_SHOPPING_CART") List<Product> shoppingCart) {
    //     // Existing logic from example...
    //     return "OK";
    // }

    
    
    // @RequestMapping(value = "/cart",
    //         method = RequestMethod.GET,
    //         produces = "application/json")
    // public List<Product> getAllCartItems(@SessionAttribute("ID_SESSION_SHOPPING_CART") List<Product> shoppingCart) {
    //     return shoppingCart;
    // }

    // // @RequestMapping(value = "/cart",
    // //         method = RequestMethod.DELETE)
    // // public String emptyCart(Model model) {
    
    // //     // Existing logic from example...
    // //     return "OK";
    // // }

    // private Optional<Product> getProductById(Stream<Product> stream, Long id) {
    //     // Existing logic from example...
    //     return Optional.empty();
    // }
}