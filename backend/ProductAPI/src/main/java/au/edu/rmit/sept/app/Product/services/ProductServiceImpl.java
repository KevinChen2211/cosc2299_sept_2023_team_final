package au.edu.rmit.sept.app.Product.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import au.edu.rmit.sept.app.Product.models.Product;
import au.edu.rmit.sept.app.Product.repositories.ProductRepository;
import au.edu.rmit.sept.app.Product.repositories.ProductRepositoryImpl;

@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository repository = new ProductRepositoryImpl();

    public  List<Product> ls = repository.findAll();
    @Override
    public Collection<Product> getProducts() {
        return repository.findAll();
    }

    @Override
    public Product getById( String id) {
        return repository.getById(id);
    }

    @Override
    public Collection<Product> getByName( String name) {
        return repository.getByName(name);
    }

        @Override
    public Collection<Product> getByChain( String name) {
        return repository.getByChain(name);
    }

    @Override
    public Collection<Product> getByCategory( String name) {
        return repository.getByCategory(name);
    }

    @Override
    public Collection<Product> getBySubCategory( String name) {
        return repository.getBySubCategory(name);
    }

    @Override
    public List<Product> getSearchProducts(String name, List<String> categories, List<String> subcategories,
            List<String> chains) {
        return repository.getSearch(name, categories, subcategories, chains);
    }
}