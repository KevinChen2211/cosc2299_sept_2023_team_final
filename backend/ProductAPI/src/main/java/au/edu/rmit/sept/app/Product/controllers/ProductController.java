package au.edu.rmit.sept.app.Product.controllers;



import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import au.edu.rmit.sept.app.Product.models.Product;
import au.edu.rmit.sept.app.Product.services.ProductService;



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

    
    @GetMapping("views/all")
    public Collection<Product> all(){
        return service.getProducts();
    }

    

    @GetMapping("id/{product_id}")
    public ResponseEntity<Object> getProductById(@PathVariable("product_id")String id){
        Product productOptional = service.getById(id);
        if (productOptional == null)
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(productOptional,HttpStatus.OK);
    }

    @GetMapping("name/{product_name}")
    public ResponseEntity<Object> getProductByName(@PathVariable("product_name")String name){
        Collection<Product> productOptional = service.getByName(name);
        if (productOptional == null)
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(productOptional,HttpStatus.OK);
    }

    @GetMapping("chain/{product_chain}")
    public ResponseEntity<Object> getProductByChain(@PathVariable("product_chain")String name){
        Collection<Product> productOptional = service.getByChain(name);
        if (productOptional == null)
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(productOptional,HttpStatus.OK);
    }

    @GetMapping("cate/{product_cate}")
    public ResponseEntity<Object> getProductByCategory(@PathVariable("product_cate")String name){
        Collection<Product> productOptional = service.getByCategory(name);
        if (productOptional == null)
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(productOptional,HttpStatus.OK);
    }

    @GetMapping("subcate/{product_subcate}")
    public ResponseEntity<Object> getProductBySubCate(@PathVariable("product_subcate")String name){
        Collection<Product> productOptional = service.getBySubCategory(name);
        if (productOptional == null)
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(productOptional,HttpStatus.OK);
    }

    @GetMapping("")
     public ResponseEntity<Object> Search(
             @RequestParam(required = false) String name,
             @RequestParam(required = false) List<String> cat,
             @RequestParam(required = false) List<String> subcat,
             @RequestParam(required = false) List<String> chain) {

         List<Product> stores = service.getSearchProducts(name,cat,subcat,chain);
         if (stores == null || stores.isEmpty())
             return new ResponseEntity<>("Products not found", HttpStatus.NOT_FOUND);
         return new ResponseEntity<>(stores, HttpStatus.OK);
     }

}