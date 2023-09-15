package au.edu.rmit.sept.app.Product.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import au.edu.rmit.sept.app.Product.models.Chain;




@Repository
public class ChainRepositoryImpl implements ChainRepository {
    
    @Override
    public List<Chain> findAll() {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://qb003608hb.execute-api.ap-southeast-2.amazonaws.com/test/chains";
            try {
                    Chain[] ChainsArray = restTemplate.getForObject(url, Chain[].class);
                    if (ChainsArray != null) {
                            return Arrays.asList(ChainsArray);
                    } else {
                            return new ArrayList<>();
                    }
            } catch (HttpClientErrorException.NotFound e) {
                    return new ArrayList<>();
            } catch (Exception e) {
                    // Handle other exceptions if needed
                    return new ArrayList<>();
            }
    }

    @Override
    public Chain getByName(String name) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://qb003608hb.execute-api.ap-southeast-2.amazonaws.com/test/chains/" + name;
        try {
            Chain[] ChainsArray = restTemplate.getForObject(url, Chain[].class);
            if (ChainsArray != null && ChainsArray.length > 0) {
                return ChainsArray[0];
            }
        } catch (HttpClientErrorException.NotFound e) {
            return null;
        }
        return null;
    }
}
