package au.edu.rmit.sept.app.Product.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import au.edu.rmit.sept.app.Product.models.Store;
import au.edu.rmit.sept.app.Product.services.StoreService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class StoreControllerTest {

    private StoreController storeController;
    private StoreService storeService;

    @BeforeEach
    public void setUp() {
        storeService = mock(StoreService.class);
        storeController = new StoreController(storeService);
    }

    @Test
    public void testFindAll_SuccessWithStores() {
        List<Store> mockStores = Arrays.asList(
            new Store("Aldi", "121 Big St, Melbourne", "1000", "Aldi Central"),
            new Store("Woolworths", "121 Swanston St, Melbourne", "1000", "Woolworths Metro")
            
        );

        when(storeService.getStores()).thenReturn(mockStores);

        ResponseEntity<Object> response = storeController.getAllStores();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        
        Object body = response.getBody();
        assertTrue(body instanceof List, "Body should be an instance of List");
        
        List<?> bodyList = (List<?>) body;
        assertFalse(bodyList.isEmpty(), "Body list should not be empty");
        for (Object obj : bodyList) {
            assertTrue(obj instanceof Store, "Every element in the list should be an instance of Store");
        }
    }

    @Test
    public void testFindStoresByPostcodesAndChains_SuccessWithStores() {
        // Given
        List<String> postcodes = Arrays.asList("1000", "1020");
        List<String> chains = Arrays.asList("Woolworths", "Coles");
        List<Store> mockStores = Arrays.asList(
                new Store("Woolworths", "121 Swanston St, Melbourne", "1000", "Woolworths Metro"),
                new Store("Coles", "16 Rich St, Melbourne", "1020", "Coles East Richmond"));

        when(storeService.getStoresByPostcodeAndChain(postcodes, chains)).thenReturn(mockStores);

        // When
        ResponseEntity<Object> response = storeController.getStoresByPostcodeAndChain(postcodes, chains);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Object body = response.getBody();
        assertTrue(body instanceof List, "Body should be an instance of List");

        List<?> bodyList = (List<?>) body;
        assertFalse(bodyList.isEmpty(), "Body list should not be empty");

        for (Object obj : bodyList) {
            assertTrue(obj instanceof Store, "Every element in the list should be an instance of Store");

            Store store = (Store) obj;
            assertTrue(postcodes.contains(store.getPostcode()), "Store's postcode should be either 1000 or 1020");
            assertTrue(chains.contains(store.getName()), "Store's chain should be either Woolworths or Coles");
        }
    }

}
