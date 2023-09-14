package au.edu.rmit.sept.app.Product.services;
import java.util.Collection;

import org.springframework.stereotype.Service;

import au.edu.rmit.sept.app.Product.models.OpeningTime;

@Service
public interface OpenTimeService {
    public Collection<OpeningTime> getByName(String name);
}
