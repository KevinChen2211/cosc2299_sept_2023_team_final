package au.edu.rmit.sept.app.superPrice.repositories;
import au.edu.rmit.sept.app.superPrice.models.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        
        // Existing products
        products.add(new Product(1L, "banana", new BigDecimal("2.00"), 10));
        products.add(new Product(2L, "apple", new BigDecimal("1.50"), 20));
        
        // Add new product here
        products.add(new Product(3L, "orange", new BigDecimal("2.50"), 15));
        
        return products;
    }

    @Override
    public String  getT() {
        return "";
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