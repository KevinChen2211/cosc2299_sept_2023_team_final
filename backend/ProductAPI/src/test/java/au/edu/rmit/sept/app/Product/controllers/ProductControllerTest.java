
package au.edu.rmit.sept.app.Product.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import au.edu.rmit.sept.app.Product.models.Product;
import au.edu.rmit.sept.app.Product.services.ProductService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ProductControllerTest {

    private ProductService mockService;
    private ProductController controller;

    @BeforeEach
    public void setUp() {
        mockService = mock(ProductService.class);
        controller = new ProductController(mockService);
    }

    @Test
    public void testGetAllProducts() {
        when(mockService.getProducts()).thenReturn(Arrays.asList(new Product(), new Product()));

        ResponseEntity<Object> response = controller.getAllProducts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof List);
    }

    @Test
    public void testGetProductById_NotFound() {
        when(mockService.getById(anyString())).thenReturn(null);

        ResponseEntity<Object> response = controller.getProductById("123");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetProductById_Found() {
        when(mockService.getById(anyString())).thenReturn(new Product());

        ResponseEntity<Object> response = controller.getProductById("81083226");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof Product);
    }


    @Test
    public void testGetProductByName_Found() {
        when(mockService.getByName(anyString())).thenReturn(Arrays.asList(new Product()));

        ResponseEntity<Object> response = controller.getProductByName("apple");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof List);
    }


    @Test
    public void testGetProductByCategory_Found() {
        when(mockService.getByCategory(anyString())).thenReturn(Arrays.asList(new Product()));

        ResponseEntity<Object> response = controller.getProductByCategory("fruit-and-veg");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof List);
    }


    @Test
    public void testGetProductBySubCate_Found() {
        when(mockService.getBySubCategory(anyString())).thenReturn(Arrays.asList(new Product()));

        ResponseEntity<Object> response = controller.getProductBySubCate("apple");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof List);
    }

    @Test
    public void testSearch_NoProductsFound() {
        when(mockService.getSearchProducts(any(), any(), any(), any(), any())).thenReturn(Collections.emptyList());

        ResponseEntity<Object> response = controller.Search("name", null, null, null, null);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testSearch_ProductsFound() {
        when(mockService.getSearchProducts(any(), any(), any(), any(), any())).thenReturn(Arrays.asList(new Product()));

        ResponseEntity<Object> response = controller.Search("name", null, null, null, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof List);
    }


    @Test
    public void testGetProductOnPromotion_Found() {
        when(mockService.getByPromotion()).thenReturn(Arrays.asList(new Product()));

        ResponseEntity<Object> response = controller.getProductOnPromotion();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof List);
    }
}
