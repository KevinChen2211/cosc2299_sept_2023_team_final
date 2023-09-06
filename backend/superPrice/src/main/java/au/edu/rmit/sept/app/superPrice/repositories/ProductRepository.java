package au.edu.rmit.sept.app.superPrice.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import au.edu.rmit.sept.app.superPrice.models.Product;

@Repository
public interface ProductRepository {
    public List<Product> findAll();

    public Product getById(String id);
    public Product getByName(String name);
    public List<Product> getByChain(String chain);
    public List<Product> getByCategory(String category);
    public List<Product> getBySubCategory(String subCategory);
}
