package au.edu.rmit.sept.app.Product.controllers;

import au.edu.rmit.sept.app.Product.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CateAndSubCategoryControllerTest {

    @InjectMocks
    private CateAndSubCategoryController controller;

    @Mock
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCate_Success() {
        ResponseEntity<Object> result = controller.getAllCate();
        List<String> expected = Arrays.asList( "bakery" ,"dairy-and-eggs", "fruit-and-veg","meat-and-seafood");
        assertEquals(expected, result.getBody());
    }

    @Test
    public void testGetAllSubCate_Success() {
        ResponseEntity<Object> result = controller.getAllSubCate();
        List<String> expected = Arrays.asList("apples", "avocados", "bananas", "beef", "bread", "cheese", "chicken", "croissant", "eggs",
                "fish", "grapes", "milk", "peaches", "pineapples", "pork", "potatos", "rolls", "watermelon");
        assertEquals(expected, result.getBody());
    }

    @Test
    public void testGetAllSubCateByCate_Success() {
        String category = "fruit-and-veg";
        List<String> mockSubCategories = Arrays.asList("apples", "avocados", "bananas", "grapes", "peaches", "pineapples", "potatos",
                "watermelon");
       

        ResponseEntity<Object> result = controller.getAllSubCateByCate(category);
        assertEquals(mockSubCategories, result.getBody());
    }

    @Test
    public void testGetAllSubCateByCate_NotFound() {
        String category = "non-existent";
        
        ResponseEntity<Object> result = controller.getAllSubCateByCate(category);
        assertEquals(new ResponseEntity<>("No subcategories found for the given category.", HttpStatus.NOT_FOUND), result);

    }
}