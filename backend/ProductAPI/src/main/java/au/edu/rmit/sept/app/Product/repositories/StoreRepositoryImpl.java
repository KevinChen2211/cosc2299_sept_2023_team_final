package au.edu.rmit.sept.app.Product.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import au.edu.rmit.sept.app.Product.models.Store;

@Repository
public class StoreRepositoryImpl implements StoreRepository {
    
    @Override
        public List<Store> findAll() {
                RestTemplate restTemplate = new RestTemplate();
                String url = "https://qb003608hb.execute-api.ap-southeast-2.amazonaws.com/test/stores";
                try {
                        Store[] StoresArray = restTemplate.getForObject(url, Store[].class);
                        if (StoresArray != null) {
                                return Arrays.asList(StoresArray);
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
        public List<Store> findStoresByPostcodesAndChains(List<String> postcodes, List<String> chains) {
            RestTemplate restTemplate = new RestTemplate();
            String baseUrl = "https://qb003608hb.execute-api.ap-southeast-2.amazonaws.com/test/stores";

            StringBuilder urlBuilder = new StringBuilder(baseUrl);
            if (postcodes != null && !postcodes.isEmpty()) {
                urlBuilder.append("?postcode=").append(String.join(",", postcodes));
            }

            if (chains != null && !chains.isEmpty()) {
                if (urlBuilder.toString().contains("?")) {
                    urlBuilder.append("&");
                } else {
                    urlBuilder.append("?");
                }
                urlBuilder.append("chain=").append(String.join(",", chains));
            }

            try {
                Store[] storesArray = restTemplate.getForObject(urlBuilder.toString(), Store[].class);
                if (storesArray != null) {
                    return Arrays.asList(storesArray);
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
}
