// package au.edu.rmit.sept.app.Product.repositories;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.web.client.RestTemplate;

// import au.edu.rmit.sept.app.Product.models.OpeningTime;

// import static org.mockito.ArgumentMatchers.*;
// import static org.mockito.Mockito.*;
// import static org.junit.jupiter.api.Assertions.*;

// import java.util.Arrays;
// import java.util.List;

// public class OpenTimeRepositoryImplTest {

//     private TestableOpenTimeRepository openTimeRepository;

//     // A subclass of OpenTimeRepositoryImpl that allows us to mock RestTemplate's behavior
//     private class TestableOpenTimeRepository extends OpenTimeRepositoryImpl {

//         public <T> T executeGetForObject(String url, Class<T> responseType) {
//             return new RestTemplate().getForObject(url, responseType);
//         }

//         // Override any other methods that use RestTemplate as needed
//     }

//     @BeforeEach
//     public void setUp() {
//         openTimeRepository = spy(new TestableOpenTimeRepository());
//     }

//     @Test
//     public void testGetByName_SuccessWithOpenTimes() {
//         // Setup
//         List<OpeningTime> expectedOpenTimes = Arrays.asList(
//             new OpeningTime("Woolworths Metro", "Monday", false, "900.0", "1800.0"),
//             new OpeningTime("Woolworths Metro", "Tuesday", false, "1000.0", "1600.0")
//         );
//         doReturn(expectedOpenTimes).when(openTimeRepository).executeGetForObject(anyString(), eq(List.class));

//         // Execute
//         List<OpeningTime> result = openTimeRepository.getByName("Woolworths Metro");

//         // Verify
//         assertEquals(expectedOpenTimes, result);
//     }

//     @Test
//     public void testGetByName_NullForWrongName() {
//         // Setup
//         doReturn(null).when(openTimeRepository).executeGetForObject(anyString(), eq(List.class));

//         // Execute
//         List<OpeningTime> result = openTimeRepository.getByName("WrongName");

//         // Verify
//         assertNull(result);
//     }
// }
