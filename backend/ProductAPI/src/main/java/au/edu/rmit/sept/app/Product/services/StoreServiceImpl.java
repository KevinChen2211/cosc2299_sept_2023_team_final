package au.edu.rmit.sept.app.Product.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import au.edu.rmit.sept.app.Product.models.Store;
import au.edu.rmit.sept.app.Product.repositories.StoreRepository;
import au.edu.rmit.sept.app.Product.repositories.StoreRepositoryImpl;

@Service
public class StoreServiceImpl implements StoreService {

    private StoreRepository repository = new StoreRepositoryImpl();

    @Override
    public List<Store> getStores() {
        return repository.findAll();
    }

    @Override
    public List<Store> getStoresByPostcodeAndChain(List<String> postcodes, List<String> chains) {
        return repository.findStoresByPostcodesAndChains(postcodes, chains);
    }
}
