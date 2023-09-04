package au.edu.rmit.sept.app.superPrice.controllers;



import java.util.Collection;
import java.util.List;
import java.util.Optional;

import au.edu.rmit.sept.app.superPrice.models.Product;
import au.edu.rmit.sept.app.superPrice.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;



@RestController
@RequestMapping(value = "product")
public class ProductController {

    private final ProductService service;
    
    @Autowired
    public ProductController(ProductService serv)
    {
        this.service = serv;
    }

    @GetMapping("all")
    public ResponseEntity<Object> getAllProducts(){
        return new ResponseEntity<>(this.service.getProducts(),HttpStatus.OK);
    }

    // @GetMapping("views")
    // public String viewProducts(Model model) {
    //     // model.addAttribute("LIST_PRODUCT", service.getProducts());
    //     return "view-products";
    // }

    
    @GetMapping("views/all")
    public Collection<Product> all(){
        return service.getProducts();
    }

    
    @GetMapping("get/{id}")
    public Product getName(@PathVariable("id")String id) {
        return service.getName(id);
    }

    @GetMapping("/{product_id}")
    public ResponseEntity<Object> getProductById(@PathVariable("product_id")Long id){
        Product productOptional = service.findrProductByID(id);
        if (productOptional == null)
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(productOptional,HttpStatus.OK);
    }


    // @GetMapping("/{product_id}")
    // public ResponseEntity<Object> getProductById(@PathVariable("product_id")String productId){
    //     Product productOptional = this.service.getName(productId);
    //     if (productOptional != null)
    //         return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
    //     return new ResponseEntity<>(productOptional,HttpStatus.OK);
    // }
}