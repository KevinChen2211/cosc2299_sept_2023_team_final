// package au.edu.rmit.sept.app.Product.controllers;

// import au.edu.rmit.sept.app.Product.services.OpenTimeService;
// import au.edu.rmit.sept.app.Product.models.OpeningTime;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.springframework.http.ResponseEntity;

// import java.util.Arrays;
// import java.util.List;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.Mockito.*;

// public class OpenTimeControllerTest {

//     @InjectMocks
//     private OpenTimeController openTimeController;

//     @Mock
//     private OpenTimeService openTimeService;

//     @BeforeEach
//     public void setUp() {
//         MockitoAnnotations.initMocks(this);
//     }


//     @Test
//     public void testGetOpenTimesByName() {
//         String storeName = "Woolworths Metro";
//         List<OpeningTime> expectedOpenTimes = Arrays.asList(
//            new OpeningTime("Woolworths Metro", "Monday", false, "900.0", "1800.0"),
//             new OpeningTime("Woolworths Metro", "Tuesday", false, "1000.0", "1600.0")
//         );
//         when(openTimeService.getByName(storeName)).thenReturn(expectedOpenTimes);

//         ResponseEntity<Object> response = openTimeController.getChainByName(storeName);

//         assertEquals(200, response.getStatusCodeValue());
//         assertEquals(expectedOpenTimes, response.getBody());
//     }


// }
