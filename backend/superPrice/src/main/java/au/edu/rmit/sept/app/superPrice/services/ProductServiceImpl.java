package au.edu.rmit.sept.app.superPrice.services;

import java.util.Collection;

import org.springframework.stereotype.Service;

import au.edu.rmit.sept.app.superPrice.models.Product;
import au.edu.rmit.sept.app.superPrice.repositories.ProductRepository;
import au.edu.rmit.sept.app.superPrice.repositories.ProductRepositoryImpl;

@Service
public class ProductServiceImpl implements ProductService{
    private ProductRepository repository = new ProductRepositoryImpl();
    @Override
    public Collection<Product> getProducts() {
        return repository.findAll();
    }
}