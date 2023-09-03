package au.edu.rmit.sept.app.superPrice.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import au.edu.rmit.sept.app.superPrice.models.Product;
import au.edu.rmit.sept.app.superPrice.models.Product.Category;
import au.edu.rmit.sept.app.superPrice.models.Product.SubCategory;
import au.edu.rmit.sept.app.superPrice.repositories.ProductRepository;
import au.edu.rmit.sept.app.superPrice.repositories.ProductRepositoryImpl;

@Service
public class ProductServiceImpl implements ProductService{

    public static List<Product> ls = new ArrayList<>();
    static {
        // Adding new fields to existing products
        ls.add(new Product(1L, "prod1", "banana", "img/banana.png", 
                                  Category.FOOD, SubCategory.GROCERY, "Chain1",
                                  new BigDecimal("2.00"), 10,
                                  Arrays.asList("Good", "Tasty"), 4.5));

        ls.add(new Product(2L, "prod2", "apple", "img/apple.png", 
                                  Category.FOOD, SubCategory.GROCERY, "Chain2",
                                  new BigDecimal("1.50"), 20,
                                  Arrays.asList("Fresh", "Crisp"), 4.7));
                                  
        // Add new products with new fields here
        ls.add(new Product(3L, "prod3", "orange", "img/orange.png", 
                                  Category.FOOD, SubCategory.GROCERY, "Chain1",
                                  new BigDecimal("2.50"), 15,
                                  Arrays.asList("Juicy", "Sour"), 4.3));
    }       
    private ProductRepository repository = new ProductRepositoryImpl();
    
    @Override
    public Collection<Product> getProducts() {
        return repository.findAll();
    }

    @Override
    public Product getName( String name) {
        return repository.getT(name);
    }

    @Override
    public Product findrProductByID(Long id) {
        for (Product product : ls) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }


}