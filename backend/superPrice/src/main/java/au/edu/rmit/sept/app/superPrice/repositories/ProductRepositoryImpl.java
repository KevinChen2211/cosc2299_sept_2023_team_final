package au.edu.rmit.sept.app.superPrice.repositories;
import au.edu.rmit.sept.app.superPrice.models.Product;
import au.edu.rmit.sept.app.superPrice.models.Product.Category;
import au.edu.rmit.sept.app.superPrice.models.Product.SubCategory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.math.BigDecimal;
import java.sql.Connection;
import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.datasource.init.UncategorizedScriptException;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.url("jdbc:h2:file:/tmp/demodb");
        dataSourceBuilder.username("sa");
        dataSourceBuilder.password("password");
        return dataSourceBuilder.build();
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();

        // Adding new fields to existing products
        products.add(new Product(1L, "prod1", "banana", "img/banana.png", 
                                  Category.FOOD, SubCategory.GROCERY, "Chain1",
                                  new BigDecimal("2.00"), 10,
                                  Arrays.asList("Good", "Tasty"), 4.5));

        products.add(new Product(2L, "prod2", "apple", "img/apple.png", 
                                  Category.FOOD, SubCategory.GROCERY, "Chain2",
                                  new BigDecimal("1.50"), 20,
                                  Arrays.asList("Fresh", "Crisp"), 4.7));
                                  
        // Add new products with new fields here
        products.add(new Product(3L, "prod3", "orange", "img/orange.png", 
                                  Category.FOOD, SubCategory.GROCERY, "Chain1",
                                  new BigDecimal("2.50"), 15,
                                  Arrays.asList("Juicy", "Sour"), 4.3));

        return products;
    }

    @Override
    public Product getT(String name) {
        // New fields are added but might not be used in this method
        Product product = new Product(1L, "prod1", name, "img/placeholder.png",
                                      Category.FOOD, SubCategory.GROCERY, "Chain1",
                                      new BigDecimal("2.00"), 10,
                                      Arrays.asList("Placeholder"), 0.0);
        return product;
    }

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