// package au.edu.rmit.sept.app.Product.repositories;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.web.client.RestTemplate;
// import au.edu.rmit.sept.app.Product.models.Chain;

// import static org.mockito.Mockito.*;
// import static org.junit.jupiter.api.Assertions.*;

// import java.util.Arrays;
// import java.util.List;

// public class ChainRepositoryImplTest {

//     private TestableChainRepository chainRepository;

//     // A subclass of ChainRepositoryImpl that allows us to mock RestTemplate's behavior
//     private class TestableChainRepository extends ChainRepositoryImpl {

//         public <T> T executeGetForObject(String url, Class<T> responseType) {
//             return new RestTemplate().getForObject(url, responseType);
//         }

//         // Override any other methods that use RestTemplate as needed
//     }

//     @BeforeEach
//     public void setUp() {
//         chainRepository = spy(new TestableChainRepository());
//     }

//     @Test
//     public void testFindAll_SuccessWithChains() {
//         // Setup
//         List<Chain> expectedChains = Arrays.asList(
//             new Chain("Coles", 4.33),
//             new Chain("Aldi", 4.52),
//             new Chain("Woolworths", 4.23)
//         );
//         doReturn(expectedChains).when(chainRepository).executeGetForObject(anyString(), eq(List.class));

//         // Execute
//         List<Chain> result = chainRepository.findAll();

//         // Verify
//         assertEquals(expectedChains, result);
//     }

//     @Test
//     public void testGetByName_SuccessWithChain() {
//         // Setup
//         Chain expectedChain = new Chain("Coles", 4.33);
//         doReturn(expectedChain).when(chainRepository).executeGetForObject(anyString(), eq(Chain.class));

//         // Execute
//         Chain result = chainRepository.getByName("Coles");

//         // Verify
//         assertEquals(expectedChain, result);
//     }

//     @Test
//     public void testGetByName_NullForWrongChainName() {
//         // Setup
//         doReturn(null).when(chainRepository).executeGetForObject(anyString(), eq(Chain.class));

//         // Execute
//         Chain result = chainRepository.getByName("Coless");

//         // Verify
//         assertNull(result);
//     }
// }
