package au.edu.rmit.sept.app.Product.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import au.edu.rmit.sept.app.Product.models.Product;

@Repository
public interface ProductRepository {
    public List<Product> findAll();

    public Product getById(String id);
    public List<Product> getByName(String name);
    public List<Product> getByChain(String chain);
    public List<Product> getByCategory(String category);
    public List<Product> getBySubCategory(String subCategory);
    public List<Product> getSearch(String name, List<String> categories, List<String> subcategories,
            List<String> chains, String promoted);
    public List<Product> getByPromotion();
}
