package au.edu.rmit.sept.app.Product.services;

import au.edu.rmit.sept.app.Product.models.Product;

import java.util.Collection;

import org.springframework.stereotype.Service;


@Service
public interface ProductService
 {
    public Collection<Product> getProducts();

    public Product getById(String id);
    // public Product getByName( String name);
    // public Collection<Product> getByChain( String name);
    // public Collection<Product> getByCategory( String name);
    // public Collection<Product> getBySubCategory( String name);
    

}
