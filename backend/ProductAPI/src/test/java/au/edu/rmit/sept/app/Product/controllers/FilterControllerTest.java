package au.edu.rmit.sept.app.Product.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import au.edu.rmit.sept.app.Product.models.Product;
import au.edu.rmit.sept.app.Product.services.ProductService;

public class FilterControllerTest {

    @InjectMocks
    private FilterController controller;

    @Mock
    private ProductService productService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetOptionsByName() {
        // Setup
        ProductService mockService = mock(ProductService.class);
        FilterController controller = new FilterController(mockService);

        Product mockProduct1 = new Product(); // Assuming a default constructor and setters are available
        mockProduct1.setChain("Coles");
        mockProduct1.setCategory("fruit-and-veg");
        mockProduct1.setSubcategory("apples");
        mockProduct1.setIsPromoted(false);

        List<Product> mockProductList = Arrays.asList(mockProduct1);

        when(mockService.getByName("app")).thenReturn(mockProductList);

        // Action
        ResponseEntity<Object> response = controller.getOptionsByName("app");

        // Verification
        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertTrue(response.getBody() instanceof Map);
        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();

        assertTrue(responseBody.containsKey("chains"));
        assertTrue(responseBody.containsKey("subcategories"));
        assertTrue(responseBody.containsKey("categories"));
        assertTrue(responseBody.containsKey("promotion"));
    }

    @Test
    public void testFilterForSubCateApples_Success() {
        // Setup
        String subcategory = "apples";
        // List<String> mockChains = Arrays.asList("Aldi", "Woolworths");
        // when(productService.getByName(subcategory)).thenReturn(mockChains.stream().map(chain
        // -> new Product("dummyID", chain, "dummyImage", subcategory, subcategory,
        // chain, BigDecimal.ZERO, 0, null, 0.0)).collect(Collectors.toList()));

        // Execute
        ResponseEntity<Object> result = controller.getOptionsBySubCat(subcategory);

        // Verify
        // Modified verification to check for correct mapping
        assertTrue(result.getBody() instanceof Map);
        Map<String, Object> responseBody = (Map<String, Object>) result.getBody();
        assertTrue(responseBody.containsKey("chains"));
        assertTrue(responseBody.get("chains") instanceof List);
    }
}
