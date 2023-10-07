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

    /**
     * Fetches all available products.
     *
     * @return ResponseEntity<Object> Returns a list of all products with a status
     *         code of 200 (OK).
     *         If there are no products, an empty list will be returned.
     */
    @GetMapping("all")
    public ResponseEntity<Object> getAllProducts(){
        return new ResponseEntity<>(this.service.getProducts(),HttpStatus.OK);
    }

    
    @GetMapping("views/all")
    public Collection<Product> all(){
        return service.getProducts();
    }

    /**
     * Retrieves a specific product based on its ID.
     *
     * @param id The ID of the product to be retrieved.
     *
     * @return ResponseEntity<Object> Returns the product details with a status code
     *         of 200 (OK).
     *         If the product with the specified ID is not found, a "not found"
     *         message is returned with a status code of 404 (NOT FOUND).
     */
    @GetMapping("id/{product_id}")
    public ResponseEntity<Object> getProductById(@PathVariable("product_id")String id){
        Product productOptional = service.getById(id);
        if (productOptional == null)
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(productOptional,HttpStatus.OK);
    }
    
    /**
     * Retrieves a specific products based on its name.
     *
     * @param name The name of the products to be retrieved.
     *
     * @return ResponseEntity<Object> Returns the product details with a status code
     *         of 200 (OK).
     *         If the products with the specified name is not found, a "not found"
     *         message is returned with a status code of 404 (NOT FOUND).
     */
    @GetMapping("name/{product_name}")
    public ResponseEntity<Object> getProductByName(@PathVariable("product_name")String name){
        Collection<Product> productOptional = service.getByName(name);
        if (productOptional == null)
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(productOptional,HttpStatus.OK);
    }

    /**
     * Retrieves a specific products based on its chain.
     *
     * @param name The chain of the products to be retrieved.
     *
     * @return ResponseEntity<Object> Returns the product details with a status code
     *         of 200 (OK).
     *         If the products with the specified chain is not found, a "not found"
     *         message is returned with a status code of 404 (NOT FOUND).
     */
    @GetMapping("chain/{product_chain}")
    public ResponseEntity<Object> getProductByChain(@PathVariable("product_chain")String name){
        Collection<Product> productOptional = service.getByChain(name);
        if (productOptional == null)
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(productOptional,HttpStatus.OK);
    }

    /**
     * Retrieves a specific products based on its category.
     *
     * @param name The category of the products to be retrieved.
     *
     * @return ResponseEntity<Object> Returns the product details with a status code
     *         of 200 (OK).
     *         If the products with the specified subcategory is not found, a "not found"
     *         message is returned with a status code of 404 (NOT FOUND).
     */
    @GetMapping("cate/{product_cate}")
    public ResponseEntity<Object> getProductByCategory(@PathVariable("product_cate")String name){
        Collection<Product> productOptional = service.getByCategory(name);
        if (productOptional == null)
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(productOptional,HttpStatus.OK);
    }

    /**
     * Retrieves a specific products based on its subcategory.
     *
     * @param name The subcategory of the products to be retrieved.
     *
     * @return ResponseEntity<Object> Returns the product details with a status code
     *         of 200 (OK).
     *         If the products with the specified subcategory is not found, a "not found"
     *         message is returned with a status code of 404 (NOT FOUND).
     */
    @GetMapping("subcate/{product_subcate}")
    public ResponseEntity<Object> getProductBySubCate(@PathVariable("product_subcate")String name){
        Collection<Product> productOptional = service.getBySubCategory(name);
        if (productOptional == null)
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(productOptional,HttpStatus.OK);
    }

    /**
     * Fetches products based on various filter criteria.
     *
     * @param name     Name of the product.
     * @param cat      Categories of the product.
     * @param subcat   Subcategories of the product.
     * @param chain    Chains of the product.
     * @param promoted Promotion status of the product.
     *
     * @return ResponseEntity<Object> Returns a list of products matching the filter
     *         criteria
     *         with a status code of 200 (OK).
     *         If no products match the criteria, a "Products not found"
     *         message is returned with a status code of 404 (NOT FOUND).
     */
    @GetMapping("")
     public ResponseEntity<Object> Search(
             @RequestParam(required = false) String name,
             @RequestParam(required = false) List<String> cat,
             @RequestParam(required = false) List<String> subcat,
             @RequestParam(required = false) List<String> chain,
             @RequestParam(required = false) String promoted) {

         List<Product> stores = service.getSearchProducts(name,cat,subcat,chain,promoted);
         if (stores == null || stores.isEmpty())
             return new ResponseEntity<>("Products not found", HttpStatus.NOT_FOUND);
         return new ResponseEntity<>(stores, HttpStatus.OK);
     }

     /**
      * Retrieves all products that are on promotion.
      *
      * @return ResponseEntity<Object> Returns a list of products on promotion with a
      *         status code of 200 (OK).
      *         If there are no products on promotion, a "not found" message is
      *         returned with a status code of 404 (NOT FOUND).
      */
    @GetMapping("promotion")
    public ResponseEntity<Object> getProductOnPromotion(){
        Collection<Product> productOptional = service.getByPromotion();
        if (productOptional == null)
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(productOptional,HttpStatus.OK);
    }

}