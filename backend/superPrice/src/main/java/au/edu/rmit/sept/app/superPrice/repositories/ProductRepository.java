package au.edu.rmit.sept.app.superPrice.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import au.edu.rmit.sept.app.superPrice.models.Product;

@Repository
public interface ProductRepository {
    public List<Product> findAll();
    public String getT(String Name);
}
