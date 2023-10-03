
// package au.edu.rmit.sept.app.Product.controllers;

// import static org.mockito.Mockito.*;
// import static org.junit.jupiter.api.Assertions.*;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;

// import au.edu.rmit.sept.app.Product.models.Product;
// import au.edu.rmit.sept.app.Product.services.ProductService;

// import java.math.BigDecimal;
// import java.util.Arrays;
// import java.util.List;

// public class ProductControllerTest {

//     private ProductController productController;
//     private ProductService productService;

//     @BeforeEach
//     public void setUp() {
//         productService = mock(ProductService.class);
//         productController = new ProductController(productService);
//     }

//     @Test
//     public void testGetAllProducts_SuccessWithProducts() {
//         // Product mockProduct1 = new Product("1", "Apples 300g", "www.google.com/apples", "fruit-and-veg", "apples", "Aldi", new BigDecimal("47.65"), 53, null, 2.8);
//         // Product mockProduct2 = new Product("2", "Bananas 1kg", "www.google.com/bananas", "fruit-and-veg", "bananas", "Woolworths", new BigDecimal("32.15"), 76, null, 3.5);
//         List<Product> mockProducts = Arrays.asList();

//         when(productService.getProducts()).thenReturn(mockProducts);

//         ResponseEntity<Object> response = productController.getAllProducts();
//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertEquals(mockProducts, response.getBody());
//     }

//     @Test
//     public void testGetProductById_SuccessWithProduct() {
//         // Product mockProduct = new Product("60852124", "Apples 300g", "www.google.com/apples", "fruit-and-veg", "apples", "Aldi", new BigDecimal("47.65"), 53, null, 2.8);
//         List<Product> mockProducts = Arrays.asList();
//         when(productService.getById("60852124")).thenReturn(mockProduct);

//         ResponseEntity<Object> response = productController.getProductById("60852124");
//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertEquals(mockProduct, response.getBody());
//     }

//     @Test
//     public void testGetProductByName_SuccessWithProducts() {
//         Product mockProduct = new Product("60852124", "Apples 300g", "www.google.com/apples", "fruit-and-veg", "apples", "Aldi", new BigDecimal("47.65"), 53, null, 2.8);
//         List<Product> mockProducts = Arrays.asList(mockProduct);
//         when(productService.getByName("Apples 300g")).thenReturn(mockProducts);

//         ResponseEntity<Object> response = productController.getProductByName("Apples 300g");
//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertEquals(mockProducts, response.getBody());
//     }

//     @Test
//     public void testGetProductByChain_SuccessWithProducts() {
//         Product mockProduct = new Product("1", "Apples 300g", "www.google.com/apples", "fruit-and-veg", "apples", "Aldi", new BigDecimal("47.65"), 53, null, 2.8);
//         List<Product> mockProducts = Arrays.asList(mockProduct);
//         when(productService.getByChain("Aldi")).thenReturn(mockProducts);

//         ResponseEntity<Object> response = productController.getProductByChain("Aldi");
//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertEquals(mockProducts, response.getBody());
//     }

//     @Test
//     public void testGetProductByCategory_SuccessWithProducts() {
//         Product mockProduct = new Product("60852124", "Apples 300g", "www.google.com/apples", "fruit-and-veg", "apples", "Aldi", new BigDecimal("47.65"), 53, null, 2.8);
//         List<Product> mockProducts = Arrays.asList(mockProduct);
//         when(productService.getByCategory("fruit-and-veg")).thenReturn(mockProducts);

//         ResponseEntity<Object> response = productController.getProductByCategory("fruit-and-veg");
//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertEquals(mockProducts, response.getBody());
//     }

//     @Test
//     public void testGetProductBySubcategory_SuccessWithProducts() {
//         Product mockProduct = new Product("60852124", "Apples 300g", "www.google.com/apples", "fruit-and-veg", "apples", "Aldi", new BigDecimal("47.65"), 53, null, 2.8);
//         List<Product> mockProducts = Arrays.asList(mockProduct);
//         when(productService.getBySubCategory("apples")).thenReturn(mockProducts);

//         ResponseEntity<Object> response = productController.getProductBySubCate("apples");
//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertEquals(mockProducts, response.getBody());
//     }

    

//     @Test
//     public void testGetSearch_ByNameAp_Success() {
//         // Given
//         Product mockProduct = new Product("60852124", "Apples 300g", "www.google.com/apples", "fruit-and-veg", "apples", "Aldi", new BigDecimal("47.65"), 53, null, 2.8);
//         List<Product> mockProducts = Arrays.asList(mockProduct);
//         when(productService.getSearchProducts("ap", null, List.of("grapes", "apples"), null)).thenReturn(mockProducts);

//         // When
//         ResponseEntity<Object> response = productController.Search("ap", null, List.of("grapes", "apples"), null);

//         // Then
//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertEquals(mockProducts, response.getBody());
//     }

//     @Test
//     public void testGetSearch_ByNameApAndSubcategoryGrapesApples_Success() {
//         // Given
//         Product mockProduct = new Product("60852124", "Apples 300g", "www.google.com/apples", "fruit-and-veg", "apples", "Aldi", new BigDecimal("47.65"), 53, null, 2.8);
//         List<Product> mockProducts = Arrays.asList(mockProduct);
//         when(productService.getSearchProducts("ap", null, List.of("grapes", "apples"), null)).thenReturn(mockProducts);

//         // When
//         ResponseEntity<Object> response = productController.Search("ap", null, List.of("grapes", "apples"), null);

//         // Then
//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertEquals(mockProducts, response.getBody());
//     }

//     @Test
//     public void testGetSearch_ByNameApAndSubcategoryGrapesApplesAndChainWoolworths_Success() {
//         // Given
//         Product mockProduct = new Product("62104441", "Apples Per Kg", "www.google.com/apples", "fruit-and-veg", "apples", "Woolworths", new BigDecimal("11.45"), 59, null, 1.5);
//         List<Product> mockProducts = Arrays.asList(mockProduct);
//         when(productService.getSearchProducts("ap", null, List.of("grapes", "apples"), List.of("Woolworths"))).thenReturn(mockProducts);

//         // When
//         ResponseEntity<Object> response = productController.Search("ap", null, List.of("grapes", "apples"), List.of("Woolworths"));

//         // Then
//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertEquals(mockProducts, response.getBody());
//     }

// }
