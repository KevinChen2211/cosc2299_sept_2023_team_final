package au.edu.rmit.sept.app.Product.services;

import java.util.List;

import org.springframework.stereotype.Service;

import au.edu.rmit.sept.app.Product.models.Store;

@Service
public interface StoreService {
    public List<Store> getStores();
    public List<Store> getStoresByPostcodeAndChain(List<String> postcodes, List<String> chains);
}
