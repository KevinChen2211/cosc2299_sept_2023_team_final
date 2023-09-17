package au.edu.rmit.sept.app.Product.services;

import java.util.Collection;

import org.springframework.stereotype.Service;

import au.edu.rmit.sept.app.Product.models.OpeningTime;
import au.edu.rmit.sept.app.Product.repositories.OpenTimeRepository;
import au.edu.rmit.sept.app.Product.repositories.OpenTimeRepositoryImpl;

@Service
public class OpenTimeServiceImpl implements OpenTimeService {
    
    private OpenTimeRepository repository = new OpenTimeRepositoryImpl();

    @Override
    public Collection<OpeningTime> getByName(String name){
        return repository.getByName(name);
    }

}
