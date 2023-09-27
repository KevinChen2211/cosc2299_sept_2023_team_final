// package au.edu.rmit.sept.app.Product.controllers;


// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.springframework.http.ResponseEntity;

// import au.edu.rmit.sept.app.Product.models.Product;
// import au.edu.rmit.sept.app.Product.services.ProductService;

// import java.math.BigDecimal;
// import java.util.Arrays;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.stream.Collectors;
// import java.util.stream.Stream;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.Mockito.when;

// public class FilterControllerTest {

//     @InjectMocks
//     private FilterController controller;

//     @Mock
//     private ProductService productService;

//     @BeforeEach
//     public void init() {
//         MockitoAnnotations.openMocks(this);
//     }

//     @Test
//     public void testFilterForNameApp_Success() {
//         // Setup
//         String filter = "app";
//         Map<String, List<String>> mockResponse = new HashMap<>();
//         mockResponse.put("categories", Arrays.asList("fruit-and-veg", "dairy-and-eggs"));
//         mockResponse.put("chains", Arrays.asList("Aldi", "Woolworths", "Coles"));
//         mockResponse.put("subcategories", Arrays.asList("apples", "pineapples", "eggs"));

//         List<Product> mockProductsChains = mockResponse.get("chains").stream().map(chain -> new Product("dummyID",
//                 "dummyName", "dummyImage", "fruit-and-veg", "apples", chain, BigDecimal.ZERO, 0, null, 0.0))
//                 .collect(Collectors.toList());

//         List<Product> mockProductsCategories = mockResponse.get("categories").stream()
//                 .map(category -> new Product("dummyID", "dummyName", "dummyImage", category, "apples", "Aldi",
//                         BigDecimal.ZERO, 0, null, 0.0))
//                 .collect(Collectors.toList());

//         List<Product> mockProductsSubcategories = mockResponse
//                 .get("subcategories").stream().map(subcategory -> new Product("dummyID", "dummyName", "dummyImage",
//                         "fruit-and-veg", subcategory, "Aldi", BigDecimal.ZERO, 0, null, 0.0))
//                 .collect(Collectors.toList());

//         when(productService.getByName(filter))
//                 .thenReturn(Stream.of(mockProductsChains, mockProductsCategories, mockProductsSubcategories)
//                         .flatMap(List::stream).collect(Collectors.toList()));

//         // Execute
//         ResponseEntity<Object> result = controller.getOptionsByName(filter);

//         // Verify
//         assertEquals(mockResponse, result.getBody());
//     }

//     @Test
//     public void testFilterForSubCateApples_Success() {
//         // Setup
//         String subcategory = "apples";
//         List<String> mockChains = Arrays.asList("Aldi", "Woolworths");
//         when(productService.getByName(subcategory)).thenReturn(mockChains.stream().map(chain -> new Product("dummyID", chain, "dummyImage", subcategory, subcategory, chain, BigDecimal.ZERO, 0, null, 0.0)).collect(Collectors.toList()));

//         // Execute
//         ResponseEntity<Object> result = controller.getOptionsBySubCat(subcategory);

//         // Verify
//         assertEquals(Map.of("chains", mockChains), result.getBody());
//     }
// }
