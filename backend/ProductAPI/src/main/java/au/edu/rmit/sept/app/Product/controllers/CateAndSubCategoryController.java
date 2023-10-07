package au.edu.rmit.sept.app.Product.controllers;



import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import au.edu.rmit.sept.app.Product.models.Product;
import au.edu.rmit.sept.app.Product.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "cate")
public class CateAndSubCategoryController {
    private final ProductService service;

    @Autowired
    public CateAndSubCategoryController(ProductService serv)
    {
        this.service = serv;
    }

    /**
     * Retrieves all available categories from an external service.
     *
     * @return ResponseEntity<Object> Returns a sorted list of all categories with a
     *         status code of 200 (OK).
     *         If no categories are found, a "No categories found." message is
     *         returned with a status code of 404 (NOT FOUND).
     */
    @GetMapping("all/cate")
    public ResponseEntity<Object> getAllCate() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://qb003608hb.execute-api.ap-southeast-2.amazonaws.com/test/categories";
        try {
            List<String> categories = restTemplate.getForObject(url, List.class);
            categories.sort(String::compareTo); // Sort the list
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (HttpClientErrorException.NotFound e) {
            return new ResponseEntity<>("No categories found.", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Retrieves all available subcategories from an external service.
     *
     * @return ResponseEntity<Object> Returns a sorted list of all subcategories
     *         with a status code of 200 (OK).
     *         If no subcategories are found, a "No categories found." message is
     *         returned with a status code of 404 (NOT FOUND).
     */
    @GetMapping("all/subcate")
    public ResponseEntity<Object> getAllSubCate() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://qb003608hb.execute-api.ap-southeast-2.amazonaws.com/test/subcategories";

        try {
            List<String> subcategories = restTemplate.getForObject(url, List.class);
            subcategories.sort(String::compareTo); // Sort the list
            return new ResponseEntity<>(subcategories, HttpStatus.OK);
        } catch (HttpClientErrorException.NotFound e) {
            return new ResponseEntity<>("No categories found.", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Retrieves all subcategories associated with a given category from an external
     * service.
     *
     * @param CateName The name of the category for which subcategories are to be
     *                 retrieved.
     *
     * @return ResponseEntity<Object> Returns a sorted list of subcategories
     *         associated with the given category
     *         with a status code of 200 (OK).
     *         If no subcategories are found for the given category, a
     *         "No subcategories found for the given category." message is returned
     *         with a status code of 404 (NOT FOUND).
     */
    @GetMapping("sub/{cate_sub}")
    public ResponseEntity<Object> getAllSubCateByCate(@PathVariable("cate_sub") String CateName) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://qb003608hb.execute-api.ap-southeast-2.amazonaws.com/test/products?cat=" + CateName;

        try {
            Product[] productsArray = restTemplate.getForObject(url, Product[].class);

            // Extracting unique subcategories from the products array and sorting them
            List<String> subcategories = Arrays.stream(productsArray)
                    .map(Product::getSubcategory)
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());

            return new ResponseEntity<>(subcategories, HttpStatus.OK);
        } catch (HttpClientErrorException.NotFound e) {
            return new ResponseEntity<>("No subcategories found for the given category.", HttpStatus.NOT_FOUND);
        }
    }

}
