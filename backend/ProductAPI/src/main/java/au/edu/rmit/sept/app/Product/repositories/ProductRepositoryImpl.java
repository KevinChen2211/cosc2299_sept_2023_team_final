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

        /**
         * Fetches all available products from an external service.
         *
         * @return List<Product> Returns a list of all products.
         *         If no products are found or an error occurs, an empty list is
         *         returned.
         */
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

        /**
         * Retrieves a specific product based on its ID from an external service.
         *
         * @param id The ID of the product to be retrieved.
         *
         * @return Product Returns the product details.
         *         If the product with the specified ID is not found or an error occurs,
         *         null is returned.
         */
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

        /**
         * Retrieves products based on their name from an external service.
         *
         * @param name The name of the product(s) to be retrieved.
         *
         * @return List<Product> Returns a list of products with the specified name.
         *         If no products match the name or an error occurs, null is returned.
         */
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

        /**
         * Retrieves products based on their chain from an external service.
         *
         * @param chain The cain of the product(s) to be retrieved.
         *
         * @return List<Product> Returns a list of products with the specified chain.
         *         If no products match the chain or an error occurs, null is returned.
         */
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

        /**
         * Retrieves products based on their category from an external service.
         *
         * @param category The category of the product(s) to be retrieved.
         *
         * @return List<Product> Returns a list of products with the specified category.
         *         If no products match the category or an error occurs, null is returned.
         */
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

        /**
         * Retrieves products based on their subCategory from an external service.
         *
         * @param subCategory The subCategory of the product(s) to be retrieved.
         *
         * @return List<Product> Returns a list of products with the specified subCategory.
         *         If no products match the subCategory or an error occurs, null is returned.
         */
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

        /**
         * Fetches products based on various filter criteria from an external service.
         *
         * @param name          Name of the product.
         * @param categories    Categories of the product.
         * @param subcategories Subcategories of the product.
         * @param chains        Chains associated with the product.
         * @param promoted      Promotion status of the product.
         *
         * @return List<Product> Returns a list of products matching the filter
         *         criteria.
         *         If no products match the criteria, or an error occurs, an empty list
         *         is returned.
         */
        @Override
        public List<Product> getSearch(String name, List<String> categories, List<String> subcategories,
                        List<String> chains, String promoted) {
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
                        urlBuilder.append("cat=").append(String.join(",", categories));
                }
                //subcat
                if (subcategories != null && !subcategories.isEmpty()) {
                        if (urlBuilder.toString().contains("?")) {
                                urlBuilder.append("&");
                        } else {
                                urlBuilder.append("?");
                        }
                        urlBuilder.append("subcat=").append(String.join(",", subcategories));
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

                //promotion
                if (promoted != null && !promoted.isEmpty()) {
                        String promotedLower = promoted.toLowerCase();
                        if (promotedLower.equals("true") || promotedLower.equals("false")) {
                                if (urlBuilder.toString().contains("?")) {
                                        urlBuilder.append("&");
                                } else {
                                        urlBuilder.append("?");
                                }
                                urlBuilder.append("isPromoted=").append(promotedLower);
                        } else {
                                // Handle invalid promoted value
                                return new ArrayList<>();
                        }
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

        /**
         * Retrieves all products that are on promotion from an external service.
         *
         * @return List<Product> Returns a list of products on promotion.
         *         If no products are on promotion or an error occurs, null is returned.
         */
        @Override
        public List<Product> getByPromotion() {
                RestTemplate restTemplate = new RestTemplate();
                        String url = "https://qb003608hb.execute-api.ap-southeast-2.amazonaws.com/test/products?isPromoted=true";
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