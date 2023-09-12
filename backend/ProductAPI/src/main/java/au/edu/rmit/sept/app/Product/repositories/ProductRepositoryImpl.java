package au.edu.rmit.sept.app.Product.repositories;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import au.edu.rmit.sept.app.Product.models.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

        @Override
        public List<Product> findAll() {
                RestTemplate restTemplate = new RestTemplate();
                String url = "https://qb003608hb.execute-api.ap-southeast-2.amazonaws.com/test/products";
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
                String url = "https://qb003608hb.execute-api.ap-southeast-2.amazonaws.com/test/products/" + id;
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
                        String url = "https://qb003608hb.execute-api.ap-southeast-2.amazonaws.com/test/products?search=" + name;
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
                        String url = "https://qb003608hb.execute-api.ap-southeast-2.amazonaws.com/test/products?chain=" + chain;
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
                        String url = "https://qb003608hb.execute-api.ap-southeast-2.amazonaws.com/test/products?cat=" + category;
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
                        String url = "https://qb003608hb.execute-api.ap-southeast-2.amazonaws.com/test/products?subcat=" + subCategory;
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
        public List<Product> getSearch(String name, List<String> categories, List<String> subcategories,
                        List<String> chains) {
                RestTemplate restTemplate = new RestTemplate();
                String baseUrl = "https://qb003608hb.execute-api.ap-southeast-2.amazonaws.com/test/products";

                StringBuilder urlBuilder = new StringBuilder(baseUrl);
                //product name
                if (name != null && !name.isEmpty()) {
                        urlBuilder.append("?search=").append(String.join(",", name));
                }
                //category
                if (categories != null && !categories.isEmpty()) {
                        if (urlBuilder.toString().contains("?")) {
                                urlBuilder.append("&");
                        } else {
                                urlBuilder.append("?");
                        }
                        urlBuilder.append("category=").append(String.join(",", categories));
                }
                //subcat
                if (subcategories != null && !subcategories.isEmpty()) {
                        if (urlBuilder.toString().contains("?")) {
                                urlBuilder.append("&");
                        } else {
                                urlBuilder.append("?");
                        }
                        urlBuilder.append("subcategory=").append(String.join(",", subcategories));
                }

                //chain
                if (chains != null && !chains.isEmpty()) {
                        if (urlBuilder.toString().contains("?")) {
                                urlBuilder.append("&");
                        } else {
                                urlBuilder.append("?");
                        }
                        urlBuilder.append("chain=").append(String.join(",", chains));
                }
                try {
                        Product[] ProductArray = restTemplate.getForObject(urlBuilder.toString(), Product[].class);
                        if (ProductArray != null) {
                                return Arrays.asList(ProductArray);
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