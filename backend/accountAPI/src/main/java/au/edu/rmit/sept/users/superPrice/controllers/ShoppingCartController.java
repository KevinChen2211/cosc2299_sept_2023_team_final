
package au.edu.rmit.sept.app.superPrice.controllers;

import au.edu.rmit.sept.app.superPrice.models.Product;
import au.edu.rmit.sept.app.superPrice.models.CartItem;
import au.edu.rmit.sept.app.superPrice.services.ProductService;
import au.edu.rmit.sept.app.superPrice.services.ShoppingCartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;



@RestController
// @SessionAttributes("ID_SESSION_SHOPPING_CART")
@RequestMapping("shopping-cart")
public class ShoppingCartController {

    @Autowired
    ProductService productService;

    @Autowired
    ShoppingCartService cartService;

    @GetMapping("views")
    public ResponseEntity<Object> getAllProducts(){
        return new ResponseEntity<>(cartService.getAllItems(),HttpStatus.OK);
    }

    @GetMapping("total")
    public ResponseEntity<Object> getAmount(){
        return new ResponseEntity<>(cartService.getAmount(),HttpStatus.OK);
    }

    @GetMapping("quantity")
    public ResponseEntity<Object> getCount(){
        return new ResponseEntity<>(cartService.getCount(),HttpStatus.OK);
    }
    
    @GetMapping("add/{id}")
    public ResponseEntity<Object> addCart(@PathVariable("id") Long id){
        Product product = productService.findrProductByID(id);
        if (product != null) {
            CartItem item = new CartItem();
            item.setProducID(product.getId());
            item.setName(product.getName());
            item.setPrice(product.getPrice());
            item.setQuantity(1);
            cartService.add(item);
        } else {
            return new ResponseEntity<>("Item not found",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(cartService.getAllItems(),HttpStatus.OK);
    }

    @GetMapping("clear")
    public ResponseEntity<Object> clearCart(){
        cartService.clear();
        return new ResponseEntity<>(cartService.getAllItems(),HttpStatus.OK);
    }

    @GetMapping("delete/{id}")
     public ResponseEntity<Object> removeCart(@PathVariable("id") Long id){
        cartService.remove(id);
        return new ResponseEntity<>(cartService.getAllItems(),HttpStatus.OK);
    }

    @PostMapping(value="update/")
    public ResponseEntity<Object> updateCart(@RequestParam("id") Long id,@RequestParam("")int qty){
        //TODO: process POST request
        cartService.update(id,qty );
         return new ResponseEntity<>(cartService.getAllItems(),HttpStatus.OK);
    }
    // Frontend example for update POST METHOD
//     <c:forEach var="item" items="${CART_ITEMS}">
//     <form action="/shopping-cart/update" method="post">
//         <input type="hidden" name="id" value="${item.productId}" />
//     <tr>
//         <td>${item.productId }</td>
//         <td>${item.name }</td>
//         <td>${item.price}</td>
//         <td><input name="qty" value="${item.qty}"
//             onblur="this.form.submit()" style="width: 50px;"></td>
//         <td>${item.price*item.qty}</td>
//         <td><a class="btn btn-primary btn-sm"
//             href="/shopping-cart/del/${item.productId }">Remove</a></td>
//     </tr>
//     </form>
// </c:forEach>
    

}