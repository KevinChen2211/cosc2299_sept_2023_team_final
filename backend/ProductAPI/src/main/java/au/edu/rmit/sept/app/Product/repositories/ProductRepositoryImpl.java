package au.edu.rmit.sept.app.Product.repositories;
import au.edu.rmit.sept.app.Product.models.Product;
// import au.edu.rmit.sept.app.superPrice.models.Product.Category;
// import au.edu.rmit.sept.app.superPrice.models.Product.SubCategory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.math.BigDecimal;
import java.sql.Connection;
import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.datasource.init.UncategorizedScriptException;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

        @Override
        public List<Product> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://qb003608hb.execute-api.ap-southeast-2.amazonaws.com/test/product";

        Product[] productsArray = restTemplate.getForObject(url, Product[].class);
        return Arrays.asList(productsArray);
        }


        @Override
        public Product getById(String id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://qb003608hb.execute-api.ap-southeast-2.amazonaws.com/test/product/" + id;

        Product[] productsArray = restTemplate.getForObject(url, Product[].class);
        if (productsArray != null && productsArray.length > 0) {
                return productsArray[0];
        }
        return null;
        }
//     @Override
//     public Product getByName(String name) {
//         return products.stream()
//                 .filter(product -> name.equalsIgnoreCase(product.getName()))
//                 .findFirst()
//                 .orElse(null);
//     }
//     @Override
//     public List<Product> getByChain(String chain) {
//     return products.stream()
//                 .filter(product -> chain.equalsIgnoreCase(product.getChain()))
//                 .collect(Collectors.toList());
//     }
//     @Override
//     public List<Product> getByCategory(String category) {
//     return products.stream()
//             .filter(product -> category.equalsIgnoreCase(product.getCategory()))
//             .collect(Collectors.toList());
//     }
//     @Override
//     public List<Product> getBySubCategory(String subCategory) {
//         return products.stream()
//                 .filter(product -> subCategory.equalsIgnoreCase(product.getSubCategory()))
//                 .collect(Collectors.toList());
//     }

    //     @Override
    // public List<Product> findAll() {
    //     try {
    //         Connection connection = getDataSource().getConnection();
    //         PreparedStatement stm = connection.prepareStatement("SELECT * FROM movies;");
    //         List<Product> movies = new ArrayList<>();
    //         ResultSet rs = stm.executeQuery();
    //         while (rs.next()) {
    //             Product m = new Product(rs.getLong(1), rs.getString(2));
    //             movies.add(m);
    //         }
    //         connection.close();
    //         return movies;
    //     } catch (SQLException e) {
    //         throw new UncategorizedScriptException("Error in findAll", e);
    //     }
    // }
}