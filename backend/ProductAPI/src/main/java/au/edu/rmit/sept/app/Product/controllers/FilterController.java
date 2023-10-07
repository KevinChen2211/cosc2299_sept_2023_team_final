package au.edu.rmit.sept.app.Product.controllers;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import au.edu.rmit.sept.app.Product.models.Product;
import au.edu.rmit.sept.app.Product.services.ProductService;

@RestController
@RequestMapping(value = "filter")
public class FilterController {
    private final ProductService service;

    @Autowired
    public FilterController(ProductService serv)
    {
        this.service = serv;
    }
    
    /**
     * Fetches filter options (chain, category, subcategory, promotion status) for
     * products based on a search name.
     *
     * @param name The search name for which filter options are to be retrieved.
     * 
     * @return ResponseEntity<Object> Returns a map containing distinct chains,
     *         categories, subcategories,
     *         and promotion status of products matching the search name with a
     *         status
     *         code of 200 (OK).
     *         If no matching products are found, a "not found" message is returned
     *         with a status code of 404 (NOT FOUND).
     */
    @GetMapping("name/{search_name}")
    public ResponseEntity<Object> getOptionsByName(@PathVariable("search_name") String name) {
        Collection<Product> productOptional = this.service.getByName(name);
        if (productOptional == null)
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);

        Product[] productArray = productOptional.toArray(new Product[0]);
        if (productArray == null || productArray.length == 0)
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);

        List<String> chains = Arrays.stream(productArray)
                .map(Product::getChain)
                .distinct()
                .collect(Collectors.toList());

        List<String> categories = Arrays.stream(productArray)
                .map(Product::getCategory)
                .distinct()
                .collect(Collectors.toList());

        List<String> subcategories = Arrays.stream(productArray)
                .map(Product::getSubcategory)
                .distinct()
                .collect(Collectors.toList());
        
        List<Boolean> promotion = Arrays.stream(productArray)
                .map(Product::getIsPromoted)
                .distinct()
                .collect(Collectors.toList());

        return new ResponseEntity<>(Map.of("chains", chains, "categories", categories, "subcategories", subcategories, "promotion", promotion),
                HttpStatus.OK);
    }

    /**
     * Fetches filter options (chains) for products based on a given subcategory
     * name.
     *
     * @param name The name of the subcategory for which filter options are to be
     *             retrieved.
     * 
     * @return ResponseEntity<Object> Returns a map containing distinct chains of
     *         products matching
     *         the subcategory name with a status code of 200 (OK).
     *         If no matching products are found, or there are no chains
     *         associated with the given subcategory, appropriate messages are
     *         returned with a status code of 404 (NOT FOUND).
     */
    @GetMapping("sub/{sub_name}")
    public ResponseEntity<Object> getOptionsBySubCat(@PathVariable("sub_name") String name){
        Collection<Product> productOptional = this.service.getByName(name);
        if (productOptional == null)
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
            
        Product[] productArray = productOptional.toArray(new Product[0]);

        if (productArray == null)
            return new ResponseEntity<>("No chains found for the given subcategory.", HttpStatus.NOT_FOUND);
        // Extracting unique subcategories from the products array
            List<String> chains = Arrays.stream(productArray)
                                            .map(Product::getChain)
                                            .distinct()
                                            .collect(Collectors.toList());
            return new ResponseEntity<>(Map.of("chains",chains), HttpStatus.OK);
    }
}
