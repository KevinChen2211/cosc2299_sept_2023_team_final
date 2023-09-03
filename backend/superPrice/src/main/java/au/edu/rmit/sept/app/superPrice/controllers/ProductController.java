package au.edu.rmit.sept.app.superPrice.controllers;



import java.util.Collection;
import java.util.List;

import au.edu.rmit.sept.app.superPrice.models.Product;
import au.edu.rmit.sept.app.superPrice.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value = "products")
public class ProductController {

    private ProductService service;
    
    @Autowired
    public ProductController(ProductService serv)
    {
        this.service = serv;
    }

    
    @GetMapping("views")
    public Collection<Product> all(){
        return service.getProducts();
    }

    
    @GetMapping("get/{name}")
    public Product getName(@PathVariable("name")String name) {
        return service.getName(name);
    }
}