package au.edu.rmit.sept.app.Product.controllers;

import java.util.Arrays;

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
    // Return filter options of list of searched product: chain, cat, subcat 
    @GetMapping("name/{search_name}")
    public ResponseEntity<Object> getOptionsByName(@PathVariable("search_name") String name) {
        Product[] productArray = service.getByName(name).toArray(new Product[0]);
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

        return new ResponseEntity<>(Map.of("chains", chains, "categories", categories, "subcategories", subcategories),
                HttpStatus.OK);
    }

    // Return filter options of chains
    @GetMapping("sub/{sub_name}")
    public ResponseEntity<Object> getOptionsBySubCat(@PathVariable("sub_name") String name){
        Product[] productArray = service.getBySubCategory(name).toArray(new Product[0]);

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
