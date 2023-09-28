// package au.edu.rmit.sept.app.Product.repositories;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.ArgumentMatchers.*;
// import static org.mockito.Mockito.*;

// import java.math.BigDecimal;
// import java.util.List;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import org.springframework.web.client.RestTemplate;

// import au.edu.rmit.sept.app.Product.models.Product;


// public class ProductRepositoryImplTest {

//     private TestableProductRepository productRepository;

//     // A subclass of ProductRepositoryImpl that allows us to mock RestTemplate's behavior
//     private class TestableProductRepository extends ProductRepositoryImpl {

//         public <T> T executeGetForObject(String url, Class<T> responseType) {
//             return new RestTemplate().getForObject(url, responseType);
//         }

//         // Override any other methods that use RestTemplate as needed
//     }

//     @BeforeEach
//     public void setUp() {
//         productRepository = spy(new TestableProductRepository());
//     }

//     @Test
//     public void testFindAll_SuccessWithProducts() {
//         Product[] products = new Product[81];  // Simulate 81 products from the database
//         // Initialize the products array here...

//         doReturn(products).when(productRepository).executeGetForObject(anyString(), eq(Product[].class));

//         List<Product> result = productRepository.findAll();

//         assertNotNull(result);
//         assertEquals(81, result.size());
//     }

//     // @Test
//     // public void testFindAll_SuccessWithNoProducts() {
//     //     Product[] products = new Product[0];

//     //     doReturn(products).when(productRepository).executeGetForObject(anyString(), eq(Product[].class));

//     //     List<Product> result = productRepository.findAll();

//     //     assertNotNull(result);
//     //     assertTrue(result.isEmpty());
//     // }

//     // @Test
//     // public void testFindAll_FailureDueToInternalError() {
//     //     doThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Missing Authentication Token"))
//     //         .when(productRepository).executeGetForObject(anyString(), eq(Product[].class));

//     //     List<Product> result = productRepository.findAll();

//     //     assertNotNull(result);
//     //     assertTrue(result.isEmpty());
//     // }

//     @Test
//     public void testGetById_SuccessWithProductInfo() {
//         Product product = new Product();
//         product.setProductID("15186775");
//         product.setQuantity(38);
//         product.setAvgRating(2.0);
//         product.setImageLocation("www.google.com/milk");
//         product.setChain("Woolworths");
//         product.setCategory("dairy-and-eggs");
//         product.setPrice(new BigDecimal("10.45"));
//         product.setName("4L home brand milk");
        

//         doReturn(new Product[]{product}).when(productRepository).executeGetForObject(anyString(), eq(Product[].class));

//         Product result = productRepository.getById("15186775");

//         assertNotNull(result);
//         assertEquals("15186775", result.getProductID());
//     }

//     @Test
//     public void testGetById_SuccessWithNoProductInfo() {
//         doReturn(new Product[0]).when(productRepository).executeGetForObject(anyString(), eq(Product[].class));
//         // get non exist id
//         Product result = productRepository.getById("1518677");

//         assertNull(result);
//     }

//     // @Test
//     // public void testGetById_FailureDueToInternalError() {
//     //     doThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR))
//     //         .when(productRepository).executeGetForObject(anyString(), eq(Product[].class));

//     //     Product result = productRepository.getById("15186775");

//     //     assertNull(result);
//     // }

//     @Test
//     public void testGetByName_SuccessWithProducts() {
//         Product product1 = new Product();
//         product1.setProductID("60852124");
//         product1.setQuantity(53);
//         product1.setAvgRating(2.8);
//         product1.setImageLocation("www.google.com/apples");
//         product1.setChain("Aldi");
//         product1.setCategory("fruit-and-veg");
//         product1.setPrice(new BigDecimal("47.65"));
//         product1.setName("Apples 300g");

//         Product product2 = new Product();
//         product2.setProductID("62104441");
//         product2.setQuantity(59);
//         product2.setAvgRating(1.5);
//         product2.setImageLocation("www.google.com/apples");
//         product2.setChain("Woolworths");
//         product2.setCategory("fruit-and-veg");
//         product2.setPrice(new BigDecimal("11.45"));
//         product2.setName("Apples Per Kg");

//         doReturn(new Product[] { product1, product2 }).when(productRepository).executeGetForObject(anyString(),
//                 eq(Product[].class));

//         List<Product> result = productRepository.getByName("apples");

//         assertNotNull(result);
//         assertEquals(2, result.size());
//         assertEquals("60852124", result.get(0).getProductID());
//         assertEquals("62104441", result.get(1).getProductID());
//     }

//     @Test
//     public void testGetByName_NoProductsFound() {
//         doReturn(new Product[0]).when(productRepository).executeGetForObject(anyString(), eq(Product[].class));

//         List<Product> result = productRepository.getByName("appless");

//         assertNull(result);
//         // assertTrue(result.isEmpty());
//     }

//     @Test
//     public void testGetByChain_SuccessWithProducts() {
//         // Creating mock products for Aldi chain
//         Product[] productsForAldi = new Product[22];

//         Product product1 = new Product();
//         product1.setProductID("60852124");
//         product1.setQuantity(53);
//         product1.setAvgRating(2.8);
//         product1.setImageLocation("www.google.com/apples");
//         product1.setChain("Aldi");
//         product1.setCategory("fruit-and-veg");
//         product1.setPrice(new BigDecimal("47.65"));
//         product1.setName("Apples 300g");
//         // product1.setLowerName("apples 300g");
//         product1.setSubcategory("apples");

//         productsForAldi[0] = product1;
//         // ... Initialize other products in the array ...

//         doReturn(productsForAldi).when(productRepository).executeGetForObject(anyString(), eq(Product[].class));

//         List<Product> result = productRepository.getByChain("Aldi");

//         assertNotNull(result);
//         assertEquals(22, result.size());
//         assertEquals("60852124", result.get(0).getProductID());
//         // ... Add other assertions for other products if needed ...
//     }

//     @Test
//     public void testGetByCategory_SuccessWithProducts() {
//         // Creating mock products for bakery category
//         Product[] productsForBakery = new Product[20];

//         Product product1 = new Product();
//         product1.setProductID("27665265");
//         product1.setQuantity(24);
//         product1.setAvgRating(1.1);
//         product1.setImageLocation("www.google.com/rolls");
//         product1.setChain("Coles");
//         product1.setCategory("bakery");
//         product1.setPrice(new BigDecimal("11.06"));
//         product1.setName("6 white rolls");
//         // product1.setLowerName("6 white rolls");
//         product1.setSubcategory("rolls");

//         productsForBakery[0] = product1;
//         // ... Initialize other products in the array ...

//         doReturn(productsForBakery).when(productRepository).executeGetForObject(anyString(), eq(Product[].class));

//         List<Product> result = productRepository.getByCategory("bakery");

//         assertNotNull(result);
//         assertEquals(20, result.size());
//         assertEquals("27665265", result.get(0).getProductID());
//         // ... Add other assertions for other products if needed ...
//     }

//     @Test
//     public void testGetByCategory_NoProductsFound() {
//         doReturn(null).when(productRepository).executeGetForObject(anyString(), eq(Product[].class));

//         List<Product> result = productRepository.getByCategory("bakeries");

//         assertNull(result);
//     }

//     @Test
//     public void testGetByChain_NoProductsFound() {
//         doReturn(new Product[0]).when(productRepository).executeGetForObject(anyString(), eq(Product[].class));

//         List<Product> result = productRepository.getByChain("Aldies");

//         assertNull(result);
//         // assertTrue(result.isEmpty());
//     }

//     @Test
//     public void testGetBySubcategory_SuccessWithProducts() {
//         // Creating mock products for croissant subcategory
//         Product[] productsForCroissant = new Product[3];

//         Product product1 = new Product();
//         product1.setProductID("75528812");
//         product1.setQuantity(59);
//         product1.setAvgRating(3.8);
//         product1.setImageLocation("www.google.com/croissant");
//         product1.setChain("Aldi");
//         product1.setCategory("bakery");
//         product1.setPrice(new BigDecimal("5.41"));
//         product1.setName("Butter croissant");
//         // product1.setLowerName("butter croissant");
//         product1.setSubcategory("croissant");

//         productsForCroissant[0] = product1;
//         // ... Initialize other products in the array ...

//         doReturn(productsForCroissant).when(productRepository).executeGetForObject(anyString(), eq(Product[].class));

//         List<Product> result = productRepository.getBySubCategory("croissant");

//         assertNotNull(result);
//         assertEquals(3, result.size());
//         assertEquals("75528812", result.get(0).getProductID());
//         // ... Add other assertions for other products if needed ...
//     }

//     @Test
//     public void testGetBySubcategory_NoProductsFound() {
//         doReturn(null).when(productRepository).executeGetForObject(anyString(), eq(Product[].class));

//         List<Product> result = productRepository.getBySubCategory("croissants");

//         assertNull(result);
//     }

//     @Test
//     public void testGetSearch_ByNameAppleAndCategoryFruitAndVeg_Success() {
//         // Given
//         Product product1 = new Product();
//         product1.setQuantity(53);
//         product1.setAvgRating(2.8);
//         product1.setImageLocation("www.google.com/apples");
//         product1.setChain("Aldi");
//         product1.setCategory("fruit-and-veg");
//         product1.setPrice(new BigDecimal("47.65"));
//         product1.setName("Apples 300g");
//         product1.setProductID("60852124");
//         // product1.setLower_name("apples 300g");
//         product1.setSubcategory("apples");

//         Product[] productsForAppleAndCategory =  new Product[5]; // Continue initializing the rest
//         productsForAppleAndCategory[0] = product1;

//         doReturn(productsForAppleAndCategory).when(productRepository).executeGetForObject(anyString(), eq(Product[].class));

//         // When
//         List<Product> result = productRepository.getSearch("apple", List.of("fruit-and-veg"), null, null);

//         // Then
//         assertNotNull(result);
//         assertEquals(5, result.size());
//         assertEquals("60852124", result.get(0).getProductID());
//         // ... Additional assertions for the products ...
//     }

//     @Test
//     public void testGetSearch_ByNameAppleAndCategoryBakery_EmptyResult() {
//         // Given
//         Product[] productsForAppleAndBakery = {};

//         doReturn(productsForAppleAndBakery).when(productRepository).executeGetForObject(anyString(),
//                 eq(Product[].class));

//         // When
//         List<Product> result = productRepository.getSearch( "apple", null, List.of("bakery"),null);

//         // Then
//         assertNotNull(result);
//         assertTrue(result.isEmpty());
//     }

//     @Test
//     public void testGetSearch_ByNameAppleAndChainWoolworthsAldi_Success() {
//         // Given
//         Product product1 = new Product();
//         product1.setQuantity(53);
//         product1.setAvgRating(2.8);
//         product1.setImageLocation("www.google.com/apples");
//         product1.setChain("Aldi");
//         product1.setCategory("fruit-and-veg");
//         product1.setPrice(new BigDecimal("47.65"));
//         product1.setName("Apples 300g");
//         product1.setProductID("60852124");
//         // product1.setLower_name("apples 300g");
//         product1.setSubcategory("apples");

//         Product[] productsForAppleAndChains = {product1}; // Continue initializing the rest

//         doReturn(productsForAppleAndChains).when(productRepository).executeGetForObject(anyString(), eq(Product[].class));

//         // When
//         List<Product> result = productRepository.getSearch("apple", null, null, List.of("Woolworths", "Aldi"));

//         // Then
//         assertNotNull(result);
//         assertEquals(3, result.size());
//         assertEquals("60852124", result.get(0).getProductID());
//         // ... Additional assertions for the products ...
//     }

//     @Test
// public void testGetSearch_ByNameAp_Success() {
//     // Given
//     Product product1 = new Product();
//     product1.setQuantity(53);
//     product1.setAvgRating(2.8);
//     product1.setImageLocation("www.google.com/apples");
//     product1.setChain("Aldi");
//     product1.setCategory("fruit-and-veg");
//     product1.setPrice(new BigDecimal("47.65"));
//     product1.setName("Apples 300g");
//     product1.setProductID("60852124");
//     // product1.setLower_name("apples 300g");
//     product1.setSubcategory("apples");

//     Product[] productsForAp = {product1}; // Continue initializing the rest

//     doReturn(productsForAp).when(productRepository).executeGetForObject(anyString(), eq(Product[].class));

//     // When
//     List<Product> result = productRepository.getSearch("ap", null, null, null);

//     // Then
//     assertNotNull(result);
//     assertEquals(8, result.size());
//     assertEquals("60852124", result.get(0).getProductID());
// }

// @Test
// public void testGetSearch_ByNameApAndSubcategoryGrapesApples_Success() {
//     // Given
//     Product product1 = new Product();
//     product1.setQuantity(53);
//     product1.setAvgRating(2.8);
//     product1.setImageLocation("www.google.com/apples");
//     product1.setChain("Aldi");
//     product1.setCategory("fruit-and-veg");
//     product1.setPrice(new BigDecimal("47.65"));
//     product1.setName("Apples 300g");
//     product1.setProductID("60852124");
//     // product1.setLower_name("apples 300g");
//     product1.setSubcategory("apples");

//     Product[] productsForApSubcategory = {product1}; // Continue initializing the rest

//     doReturn(productsForApSubcategory).when(productRepository).executeGetForObject(anyString(), eq(Product[].class));

//     // When
//     List<Product> result = productRepository.getSearch("ap", null, List.of("grapes", "apples"), null);

//     // Then
//     assertNotNull(result);
//     assertEquals(5, result.size());
//     assertEquals("60852124", result.get(0).getProductID());
// }

// @Test
// public void testGetSearch_ByNameApAndSubcategoryGrapesApplesAndChainWoolworths_Success() {
//     // Given
//     Product product1 = new Product();
//     product1.setQuantity(59);
//     product1.setAvgRating(1.5);
//     product1.setImageLocation("www.google.com/apples");
//     product1.setChain("Woolworths");
//     product1.setCategory("fruit-and-veg");
//     product1.setPrice(new BigDecimal("11.45"));
//     product1.setName("Apples Per Kg");
//     product1.setProductID("62104441");
//     // product1.setLower_name("apples per kg");
//     product1.setSubcategory("apples");

//     Product[] productsForApSubcategoryChain = {product1}; // Continue initializing the rest

//     doReturn(productsForApSubcategoryChain).when(productRepository).executeGetForObject(anyString(), eq(Product[].class));

//     // When
//     List<Product> result = productRepository.getSearch("ap", null , List.of("grapes", "apples"), List.of("Woolworths"));

//     // Then
//     assertNotNull(result);
//     assertEquals(3, result.size());
//     assertEquals("62104441", result.get(0).getProductID());
// }

// }
