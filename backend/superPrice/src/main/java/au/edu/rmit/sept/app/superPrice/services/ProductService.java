package au.edu.rmit.sept.app.superPrice.services;

import au.edu.rmit.sept.app.superPrice.models.Product;

import java.util.Collection;

import org.springframework.stereotype.Service;


@Service
public interface ProductService
 {
    public Collection<Product> getProducts();
}
