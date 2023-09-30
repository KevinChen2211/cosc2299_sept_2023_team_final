package au.edu.rmit.sept.app.Product.repositories;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.web.client.RestTemplate;

import au.edu.rmit.sept.app.Product.models.OpeningTime;
import au.edu.rmit.sept.app.Product.models.Product;


public class ProductRepositoryImplTest {

    private TestableProductRepository productRepository;

    // A subclass of ProductRepositoryImpl that allows us to mock RestTemplate's behavior
    private class TestableProductRepository extends ProductRepositoryImpl {

        public <T> T executeGetForObject(String url, Class<T> responseType) {
            return new RestTemplate().getForObject(url, responseType);
        }

        // Override any other methods that use RestTemplate as needed
    }

    @BeforeEach
    public void setUp() {
        productRepository = spy(new TestableProductRepository());
    }

    @Test
    public void testFindAll_SuccessWithProducts() {
        // Product[] products = new Product[81];  // Simulate 81 products from the database
        // Initialize the products array here...

        doReturn(new ArrayList<>()).when(productRepository).executeGetForObject(anyString(), eq(Product[].class));

        List<Product> result = productRepository.findAll();

        assertNotNull(result);
        // assertEquals(81, result.size());
        for (Object obj : result) {
            assertTrue(obj instanceof Product, "Every element in the list should be an instance of Product");
        }
    }

    // @Test
    // public void testFindAll_SuccessWithNoProducts() {
    //     Product[] products = new Product[0];

    //     doReturn(products).when(productRepository).executeGetForObject(anyString(), eq(Product[].class));

    //     List<Product> result = productRepository.findAll();

    //     assertNotNull(result);
    //     assertTrue(result.isEmpty());
    // }

    // @Test
    // public void testFindAll_FailureDueToInternalError() {
    //     doThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Missing Authentication Token"))
    //         .when(productRepository).executeGetForObject(anyString(), eq(Product[].class));

    //     List<Product> result = productRepository.findAll();

    //     assertNotNull(result);
    //     assertTrue(result.isEmpty());
    // }

    @Test
    public void testGetById_SuccessWithProductInfo() {
        // Product product = new Product();
        // product.setProductID("15186775");
        // product.setQuantity(38);
        // product.setAvgRating(2.0);
        // product.setImageLocation("www.google.com/milk");
        // product.setChain("Woolworths");
        // product.setCategory("dairy-and-eggs");
        // product.setPrice(new BigDecimal("10.45"));
        // product.setName("4L home brand milk");
        

        doReturn(new ArrayList<>()).when(productRepository).executeGetForObject(anyString(), eq(Product[].class));

        Product result = productRepository.getById("91012874");

        assertNotNull(result);
        assertTrue(result instanceof Product, "Every element in the list should be an instance of Product");
        
    }

    @Test
    public void testGetById_SuccessWithNoProductInfo() {
        doReturn(new Product[0]).when(productRepository).executeGetForObject(anyString(), eq(Product[].class));
        // get non exist id
        Product result = productRepository.getById("00000000");

        assertNull(result);
    }

    // @Test
    // public void testGetById_FailureDueToInternalError() {
    //     doThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR))
    //         .when(productRepository).executeGetForObject(anyString(), eq(Product[].class));

    //     Product result = productRepository.getById("15186775");

    //     assertNull(result);
    // }

    @Test
    public void testGetByName_SuccessWithProducts() {

        doReturn(new ArrayList<>()).when(productRepository).executeGetForObject(anyString(),
                eq(Product[].class));

        List<Product> result = productRepository.getByName("apples");

        // Verify
        assertNotNull(result, "Result list should not be null");
        assertFalse(result.isEmpty(), "Result list should not be empty");

        for (Product product : result) {
            assertTrue(product instanceof Product, "Every element in the list should be an instance of Product");
            assertEquals("apples", product.getSubcategory(), "Every product's subcategory should be apples");
        }
    }

    @Test
    public void testGetByName_NoProductsFound() {
        doReturn(new Product[0]).when(productRepository).executeGetForObject(anyString(), eq(Product[].class));

        List<Product> result = productRepository.getByName("appless");

        assertNull(result);
        // assertTrue(result.isEmpty());
    }

    @Test
    public void testGetByChain_SuccessWithProducts() {
        doReturn(new ArrayList<>()).when(productRepository).executeGetForObject(anyString(), eq(Product[].class));

        List<Product> result = productRepository.getByChain("Aldi");

        assertNotNull(result);
        for (Product product : result) {
            assertTrue(product instanceof Product, "Every element in the list should be an instance of Product");
            assertEquals("Aldi", product.getChain());
        }
    }

    @Test
    public void testGetByCategory_SuccessWithProducts() {
        doReturn(new ArrayList<>()).when(productRepository).executeGetForObject(anyString(), eq(Product[].class));

        List<Product> result = productRepository.getByCategory("bakery");

        assertNotNull(result);
        for (Product product : result) {
            assertTrue(product instanceof Product, "Every element in the list should be an instance of Product");
            assertEquals("bakery", product.getCategory());
        }
    }

    @Test
    public void testGetByCategory_NoProductsFound() {
        doReturn(null).when(productRepository).executeGetForObject(anyString(), eq(Product[].class));

        List<Product> result = productRepository.getByCategory("bakeries");

        assertNull(result);
    }

    @Test
    public void testGetByChain_NoProductsFound() {
        doReturn(new Product[0]).when(productRepository).executeGetForObject(anyString(), eq(Product[].class));

        List<Product> result = productRepository.getByChain("Aldies");

        assertNull(result);
        // assertTrue(result.isEmpty());
    }

    @Test
    public void testGetBySubcategory_SuccessWithProducts() {
        doReturn(new ArrayList<>()).when(productRepository).executeGetForObject(anyString(), eq(Product[].class));

        List<Product> result = productRepository.getBySubCategory("croissant");

        assertNotNull(result);
        for (Product product : result) {
            assertTrue(product instanceof Product, "Every element in the list should be an instance of Product");
            assertEquals("croissant", product.getSubcategory());
        }
    }

    @Test
    public void testGetBySubcategory_NoProductsFound() {
        doReturn(null).when(productRepository).executeGetForObject(anyString(), eq(Product[].class));

        List<Product> result = productRepository.getBySubCategory("croissants");

        assertNull(result);
    }

    @Test
    public void testGetSearch_ByNameAppleAndCategoryFruitAndVeg_Success() {

        doReturn(new ArrayList<>()).when(productRepository).executeGetForObject(anyString(), eq(Product[].class));

        // When
        List<Product> result = productRepository.getSearch("apple", List.of("fruit-and-veg"), null, null,null);

        // Then
        assertNotNull(result);
        for (Product product : result) {
            assertTrue(product instanceof Product, "Every element in the list should be an instance of Product");
            assertEquals("fruit-and-veg", product.getCategory());
        }
        
    }

    @Test
    public void testGetSearch_ByNameAppleAndCategoryBakery_EmptyResult() {
        // Given
        Product[] productsForAppleAndBakery = {};

        doReturn(productsForAppleAndBakery).when(productRepository).executeGetForObject(anyString(),
                eq(Product[].class));

        // When
        List<Product> result = productRepository.getSearch( "apple", null, List.of("bakery"),null,null);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetSearch_ByNameAppleAndChainWoolworthsAldi_Success() {
        doReturn(new ArrayList<>()).when(productRepository).executeGetForObject(anyString(), eq(Product[].class));

        // When
        List<Product> result = productRepository.getSearch("apple", null, null, List.of("Woolworths", "Aldi"),null);

        // Then
        assertNotNull(result);
        for (Product product : result) {
            assertTrue(product instanceof Product, "Every element in the list should be an instance of Product");
            assertTrue("Woolworths".equals(product.getChain()) || "Aldi".equals(product.getChain()),
                    "Product's chain should be either Woolworths or Aldi");
        }
    }

    @Test
    public void testGetSearch_ByNameAp_Success() {
        doReturn(new ArrayList<>()).when(productRepository).executeGetForObject(anyString(), eq(Product[].class));

        // When
        List<Product> result = productRepository.getSearch("ap", null, null, null,null);

        // Then
        assertNotNull(result);
        for (Product product : result) {
            assertTrue(product instanceof Product, "Every element in the list should be an instance of Product");
        }
    }

    @Test
    public void testGetSearch_ByNameApAndSubcategoryGrapesApples_Success() {

        doReturn(new ArrayList<>()).when(productRepository).executeGetForObject(anyString(), eq(Product[].class));

        // When
        List<Product> result = productRepository.getSearch("ap", null, List.of("grapes", "apples"), null, null);

        // Then
        assertNotNull(result);
        for (Product product : result) {
            assertTrue(product instanceof Product, "Every element in the list should be an instance of Product");
            assertTrue("grapes".equals(product.getSubcategory()) || "apples".equals(product.getSubcategory()));
        }
    }

    @Test
    public void testGetSearch_ByNameApAndSubcategoryGrapesApplesAndChainWoolworths_Success() {

        doReturn(new ArrayList<>()).when(productRepository).executeGetForObject(anyString(), eq(Product[].class));

        // When
        List<Product> result = productRepository.getSearch("ap", null , List.of("grapes", "apples"), List.of("Woolworths"),null);

        // Then
        assertNotNull(result);
        for (Product product : result) {
            assertTrue(product instanceof Product, "Every element in the list should be an instance of Product");
            assertTrue("grapes".equals(product.getSubcategory()) || "apples".equals(product.getSubcategory()));
            assertEquals("Woolworths", product.getChain());
        }
    }

    @Test
    public void testGetSearch_ByNameApAndPromotionTrue_Success() {

        doReturn(new ArrayList<>()).when(productRepository).executeGetForObject(anyString(), eq(Product[].class));

        // When
        List<Product> result = productRepository.getSearch("ap", null, null, null, "True");

        // Then
        assertNotNull(result);
        for (Product product : result) {
            assertTrue(product instanceof Product, "Every element in the list should be an instance of Product");
            assertEquals(true, product.getIsPromoted());
        }
    }

}
