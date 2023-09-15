package au.edu.rmit.sept.app.Product.services;

import java.util.Collection;

import org.springframework.stereotype.Service;

import au.edu.rmit.sept.app.Product.models.Chain;

@Service
public interface ChainService {
    public Collection<Chain> getChains();
    public Chain getByName( String name);
}
