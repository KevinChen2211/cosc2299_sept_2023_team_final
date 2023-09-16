package au.edu.rmit.sept.app.Product.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import au.edu.rmit.sept.app.Product.models.OpeningTime;

@Repository
public interface OpenTimeRepository {
     public List<OpeningTime> getByName(String name);
}
