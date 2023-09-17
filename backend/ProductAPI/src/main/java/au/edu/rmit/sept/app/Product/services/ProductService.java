package au.edu.rmit.sept.app.Product.services;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import au.edu.rmit.sept.app.Product.models.Product;


@Service
public interface ProductService
 {
    public Collection<Product> getProducts();

    public Product getById(String id);
    public Collection<Product> getByName( String name);
    public Collection<Product> getByChain( String name);
    public Collection<Product> getByCategory( String name);
    public Collection<Product> getBySubCategory( String name);
    public List<Product> getSearchProducts(String name, List<String> categories, List<String> subcategories,
            List<String> chains);

}
