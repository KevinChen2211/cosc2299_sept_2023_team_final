package au.edu.rmit.sept.app.superPrice.repositories;
import au.edu.rmit.sept.app.superPrice.models.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

        @Override
        public List<Product> findAll() {
                RestTemplate restTemplate = new RestTemplate();
                String url = "https://qb003608hb.execute-api.ap-southeast-2.amazonaws.com/test/product";
                try {
                        Product[] productsArray = restTemplate.getForObject(url, Product[].class);
                        if (productsArray != null) {
                                return Arrays.asList(productsArray);
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
        public Product getById(String id) {
                RestTemplate restTemplate = new RestTemplate();
                String url = "https://qb003608hb.execute-api.ap-southeast-2.amazonaws.com/test/product/" + id;
                try {
                        Product[] productsArray = restTemplate.getForObject(url, Product[].class);
                        if (productsArray != null && productsArray.length > 0) {
                                return productsArray[0];
                        }
                } catch (HttpClientErrorException.NotFound e) {
                        return null;
                }
                return null;
        }
        @Override
        public  List<Product> getByName(String name) {
                RestTemplate restTemplate = new RestTemplate();
                        String url = "https://qb003608hb.execute-api.ap-southeast-2.amazonaws.com/test/product?search=" + name;
                        try {
                                Product[] productsArray = restTemplate.getForObject(url, Product[].class);
                                if (productsArray != null && productsArray.length > 0) {
                                        return Arrays.asList(productsArray);
                                }
                        } catch (HttpClientErrorException.NotFound e) {
                                return null;
                        }
                        return null;
        }
        @Override
        public List<Product> getByChain(String chain) {
                RestTemplate restTemplate = new RestTemplate();
                        String url = "https://qb003608hb.execute-api.ap-southeast-2.amazonaws.com/test/product?chain=" + chain;
                        try {
                                Product[] productsArray = restTemplate.getForObject(url, Product[].class);
                                if (productsArray != null && productsArray.length > 0) {
                                        return Arrays.asList(productsArray);
                                }
                        } catch (HttpClientErrorException.NotFound e) {
                                return null;
                        }
                        return null;
        }
        
        @Override
        public List<Product> getByCategory(String category) {
                RestTemplate restTemplate = new RestTemplate();
                        String url = "https://qb003608hb.execute-api.ap-southeast-2.amazonaws.com/test/product?cat=" + category;
                        try {
                                Product[] productsArray = restTemplate.getForObject(url, Product[].class);
                                if (productsArray != null && productsArray.length > 0) {
                                        return Arrays.asList(productsArray);
                                }
                        } catch (HttpClientErrorException.NotFound e) {
                                return null;
                        }
                        return null;
        }

        @Override
        public List<Product> getBySubCategory(String subCategory) {
                RestTemplate restTemplate = new RestTemplate();
                        String url = "https://qb003608hb.execute-api.ap-southeast-2.amazonaws.com/test/product?subcat=" + subCategory;
                        try {
                                Product[] productsArray = restTemplate.getForObject(url, Product[].class);
                                if (productsArray != null && productsArray.length > 0) {
                                        return Arrays.asList(productsArray);
                                }
                        } catch (HttpClientErrorException.NotFound e) {
                                return null;
                        }
                        return null;
        }


}