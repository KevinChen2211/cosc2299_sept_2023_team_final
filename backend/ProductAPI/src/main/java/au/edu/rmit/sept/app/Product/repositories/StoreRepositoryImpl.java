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

    /**
     * Fetches all available stores from an external service.
     *
     * @return List<Store> Returns a list of all stores.
     *         If no stores are found or an error occurs, an empty list is returned.
     */
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

        /**
         * Fetches stores from an external service based on given postcodes and/or
         * chains.
         *
         * @param postcodes A list of postcodes to filter the stores by.
         * @param chains    A list of chains to filter the stores by.
         *
         * @return List<Store> Returns a list of stores filtered by the given postcodes
         *         and/or chains.
         *         If no stores match the criteria, or an error occurs, an empty list is
         *         returned.
         */
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
