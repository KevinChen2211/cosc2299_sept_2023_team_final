package au.edu.rmit.sept.app.Product.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import au.edu.rmit.sept.app.Product.models.Store;

@Repository
public interface StoreRepository {
    public List<Store> findAll();
    public List<Store> findStoresByPostcodesAndChains(List<String> postcodes, List<String> chains);
}
