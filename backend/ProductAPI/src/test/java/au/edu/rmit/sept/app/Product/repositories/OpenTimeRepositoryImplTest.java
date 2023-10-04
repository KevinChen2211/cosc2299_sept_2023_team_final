package au.edu.rmit.sept.app.Product.repositories;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import au.edu.rmit.sept.app.Product.models.OpeningTime;

public class OpenTimeRepositoryImplTest {

    private TestableOpenTimeRepository openTimeRepository;

    // A subclass of OpenTimeRepositoryImpl that allows us to mock RestTemplate's
    // behavior
    private class TestableOpenTimeRepository extends OpenTimeRepositoryImpl {

        public <T> T executeGetForObject(String url, Class<T> responseType) {
            return new RestTemplate().getForObject(url, responseType);
        }

        // Override any other methods that use RestTemplate as needed
    }

    @BeforeEach
    public void setUp() {
        openTimeRepository = spy(new TestableOpenTimeRepository());
    }

    @Test
    public void testGetByName_SuccessWithOpenTimes() {
        // // Setup
        doReturn(new ArrayList<>()).when(openTimeRepository).executeGetForObject(anyString(), eq(List.class));

        // Execute
        List<OpeningTime> result = openTimeRepository.getByName("Woolworths Metro");

        // Verify
        for (Object obj : result) {
            assertTrue(obj instanceof OpeningTime, "Every element in the list should be an instance of OpeningTime");
        }
    }

    @Test
    public void testGetByName_NullForWrongName() {
        // Setup
        doReturn(null).when(openTimeRepository).executeGetForObject(anyString(), eq(List.class));

        // Execute
        List<OpeningTime> result = openTimeRepository.getByName("WrongName");

        // Verify
        assertNull(result);
    }
}
