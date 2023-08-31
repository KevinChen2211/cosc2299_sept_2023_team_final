package au.edu.rmit.sept.app.superPrice.repositories;
import au.edu.rmit.sept.app.superPrice.models.Product;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    @Override
    public List<Product> findAll() {
        return List.of(new Product(1L, "The Godfather"), new Product(2L, "Minions" ), new Product(3L, "Miniosns" ));
    }
}