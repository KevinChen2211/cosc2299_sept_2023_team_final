package au.edu.rmit.sept.app.superPrice.repositories;
import au.edu.rmit.sept.app.superPrice.models.Product;
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

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    // public DataSource getDataSource() {
    //     DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
    //     dataSourceBuilder.driverClassName("org.h2.Driver");
    //     dataSourceBuilder.url("jdbc:h2:file:/tmp/demodb");
    //     dataSourceBuilder.username("sa");
    //     dataSourceBuilder.password("password");
    //     return dataSourceBuilder.build();
    // }

    private static List<Product> products = new ArrayList<>();
    static {
        // Existing products
        products.add(new Product("1", "prod1", "banana", "img/banana.png",
                "FOOD", "GROCERY", "Chain1",
                new BigDecimal("2.00"), 10,
                Arrays.asList("Good", "Tasty"), 4.5));

        products.add(new Product("2", "prod2", "apple", "img/apple.png",
                "FOOD", "GROCERY", "Exaokajhabsd",
                new BigDecimal("1.50"), 20,
                Arrays.asList("Fresh", "Crisp"), 4.7));

        products.add(new Product("3", "prod3", "orange", "img/orange.png",
                "FOOD", "GROCERY", "Chain1",
                new BigDecimal("2.50"), 15,
                Arrays.asList("Juicy", "Sour"), 4.3));

        // Additional products to fulfill requirements
        // From Chain1 and Exaokajhabsd
        // Categories: FOOD and ELECTRONICS
        // Subcategories: GROCERY and PHONES
        for (int i = 4; i <= 13; i++) {
            String id = String.valueOf(i);
            String prodId = "prod" + id;
            String name = "product" + id;
            String imgLocation = "img/" + name + ".png";
            String category = i % 2 == 0 ? "FOOD" : "ELECTRONICS";
            String subCategory = i % 3 == 0 ? "GROCERY" : "PHONES";
            String chain = i % 4 == 0 ? "Chain1" : "Exaokajhabsd";
            BigDecimal price = new BigDecimal("1.00").add(new BigDecimal("0.50").multiply(new BigDecimal(i)));
            Integer quantity = 10 + i;
            List<String> reviews = Arrays.asList("Review1", "Review2");
            Double avgRating = 4.0 + (i % 2 == 0 ? 0.5 : 0.0);

            products.add(new Product(id, prodId, name, imgLocation, category, subCategory, chain, price, quantity,
                    reviews, avgRating));
        }
    }

    @Override
    public List<Product> findAll() {

        return products;
    }

    // @Override
    // public Product getT(String name) {
    //     return null;
    //     return product;
    // }
    @Override
    public Product getById(String id) {
        return products.stream()
                .filter(product -> id.equals(product.getId()))
                .findFirst()
                .orElse(null);
    }
    @Override
    public Product getByName(String name) {
        return products.stream()
                .filter(product -> name.equalsIgnoreCase(product.getName()))
                .findFirst()
                .orElse(null);
    }
    @Override
    public List<Product> getByChain(String chain) {
    return products.stream()
                .filter(product -> chain.equalsIgnoreCase(product.getChain()))
                .collect(Collectors.toList());
    }
    @Override
    public List<Product> getByCategory(String category) {
    return products.stream()
            .filter(product -> category.equalsIgnoreCase(product.getCategory()))
            .collect(Collectors.toList());
    }
    @Override
    public List<Product> getBySubCategory(String subCategory) {
        return products.stream()
                .filter(product -> subCategory.equalsIgnoreCase(product.getSubCategory()))
                .collect(Collectors.toList());
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