package au.edu.rmit.sept.app.Product.controllers;

import au.edu.rmit.sept.app.Product.services.OpenTimeService;
import au.edu.rmit.sept.app.Product.models.OpeningTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OpenTimeControllerTest {

    @InjectMocks
    private OpenTimeController openTimeController;

    @Mock
    private OpenTimeService openTimeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetOpenTimesByName() {
        // Given
        String storeName = "Woolworths Metro";
        List<OpeningTime> expectedOpenTimes = Arrays.asList(
                new OpeningTime("Woolworths Metro", "Monday", false, "900.0", "1800.0"),
                new OpeningTime("Woolworths Metro", "Tuesday", false, "1000.0", "1600.0"));
        when(openTimeService.getByName(storeName)).thenReturn(expectedOpenTimes);

        // When
        ResponseEntity<Object> response = openTimeController.getChainByName(storeName);

        // Then
        assertEquals(200, response.getStatusCodeValue());

        Object body = response.getBody();
        assertTrue(body instanceof List, "Body should be an instance of List");

        List<?> bodyList = (List<?>) body;
        assertFalse(bodyList.isEmpty(), "Body list should not be empty");

        for (Object obj : bodyList) {
            assertTrue(obj instanceof OpeningTime, "Every element in the list should be an instance of OpeningTime");

            OpeningTime openingTime = (OpeningTime) obj;

            // Check the type of individual fields
            assertTrue(openingTime.getStoreName() instanceof String, "storeName should be a String");
            assertTrue(openingTime.getDayOfWeek() instanceof String, "dayOfWeek should be a String");
            // assertTrue(openingTime.get() instanceof Boolean,
            //         "isClosed should be a boolean");
            assertTrue(openingTime.getFrom() instanceof String, "from should be a String");
            assertTrue(openingTime.getTo() instanceof String, "to should be a String");
        }
    }

}
