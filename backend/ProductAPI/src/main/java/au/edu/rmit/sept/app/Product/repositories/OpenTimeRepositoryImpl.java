package au.edu.rmit.sept.app.Product.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import au.edu.rmit.sept.app.Product.models.OpeningTime;

@Repository
public class OpenTimeRepositoryImpl implements OpenTimeRepository{

    /**
     * Retrieves opening times for a specific store or chain based on its name from
     * an external service.
     *
     * @param name The name of the store or chain for which the opening times are to
     *             be retrieved.
     *
     * @return List<OpeningTime> Returns a list of opening times for the specified
     *         name.
     *         If the opening times for the specified name are not found or an error
     *         occurs,
     *         an empty list or null is returned.
     */
    @Override
    public List<OpeningTime> getByName(String name){
         RestTemplate restTemplate = new RestTemplate();
        String url = "https://qb003608hb.execute-api.ap-southeast-2.amazonaws.com/test/opening-times/" + name;
        try {
            OpeningTime[] OpeningTimeArray = restTemplate.getForObject(url, OpeningTime[].class);
            if (OpeningTimeArray != null) {
                return Arrays.asList(OpeningTimeArray);
            } else {
                return new ArrayList<>();
            }
        } catch (HttpClientErrorException.NotFound e) {
            return null;
        }
        
    }
}
