package au.edu.rmit.sept.app.Product.services;

import java.util.Collection;

import org.springframework.stereotype.Service;

import au.edu.rmit.sept.app.Product.models.Chain;
import au.edu.rmit.sept.app.Product.repositories.ChainRepository;
import au.edu.rmit.sept.app.Product.repositories.ChainRepositoryImpl;

@Service
public class ChainServiceImpl implements ChainService {
    private ChainRepository repository = new ChainRepositoryImpl();

    @Override
    public Collection<Chain> getChains() {
        return repository.findAll();
    }

    @Override
    public Chain getByName( String name) {
        return repository.getByName(name);
    }
}
