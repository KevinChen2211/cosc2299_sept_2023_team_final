// package au.edu.rmit.sept.app.Product.repositories;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.web.client.RestTemplate;

// import au.edu.rmit.sept.app.Product.models.Store;

// import static org.mockito.ArgumentMatchers.*;
// import static org.mockito.Mockito.*;
// import static org.junit.jupiter.api.Assertions.*;

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;

// public class StoreRepositoryImplTest {

//     private TestableStoreRepository storeRepository;

//     private class TestableStoreRepository extends StoreRepositoryImpl {
        
//         public <T> T executeGetForObject(String url, Class<T> responseType) {
//             return new RestTemplate().getForObject(url, responseType);
//         }
//     }

//     @BeforeEach
//     public void setUp() {
//         storeRepository = spy(new TestableStoreRepository());
//     }

//     @Test
//     public void testFindAll_SuccessWithStores() {
//         // Setup
//         List<Store> expectedStores = Arrays.asList(
//                 new Store("Aldi Central", "121 Big St, Melbourne", "1000", "Aldi"),
//                 new Store("Woolworths Metro", "121 Swanston St, Melbourne", "1000", "Woolworths"),
//                 new Store("Coles South Yarra", "22 River St, South Yarra", "1021", "Coles"),
//                 new Store("Aldi South Yarra", "13 Yarra St, South Yarra", "1021", "Aldi"),
//                 new Store("Coles East Richmond", "16 Rich St, Melbourne", "1020", "Coles"),
//                 new Store("Woolworths Richmond", "12 Rich St, Melbourne", "1020", "Woolworths"));

//         doReturn(expectedStores).when(storeRepository).executeGetForObject(anyString(), eq(Store[].class));

//         // Execute
//         List<Store> result = storeRepository.findAll();

//         // Verify
//         assertEquals(expectedStores, result);
//     }



//     @Test
//     public void testFindStoresByPostcodesAndChains_SinglePostcode1000() {
//         // Setup
//         List<Store> expectedStores = Arrays.asList(
//                 new Store("Aldi Central", "121 Big St, Melbourne", "1000", "Aldi"),
//                 new Store("Woolworths Metro", "121 Swanston St, Melbourne", "1000", "Woolworths"));

//         doReturn(expectedStores).when(storeRepository).executeGetForObject(anyString(), eq(Store[].class));

//         // Execute
//         List<Store> result = storeRepository.findStoresByPostcodesAndChains(Arrays.asList("1000"), null);

//         // Verify
//         assertEquals(expectedStores, result);
//     }

//     @Test
//     public void testFindStoresByPostcodesAndChains_NoStoresForPostcode1001() {
//         // Setup
//         doReturn(new ArrayList<>()).when(storeRepository).executeGetForObject(anyString(), eq(Store[].class));

//         // Execute
//         List<Store> result = storeRepository.findStoresByPostcodesAndChains(Arrays.asList("1001"), null);

//         // Verify
//         assertTrue(result.isEmpty());
//     }

//         @Test
//     public void testFindStoresByPostcodesAndChains_Postcodes1000And1020() {
//         // Setup
//         List<Store> expectedStores = Arrays.asList(
//                 new Store("Aldi Central", "121 Big St, Melbourne", "1000", "Aldi"),
//                 new Store("Woolworths Metro", "121 Swanston St, Melbourne", "1000", "Woolworths"),
//                 new Store("Coles East Richmond", "16 Rich St, Melbourne", "1020", "Coles"),
//                 new Store("Woolworths Richmond", "12 Rich St, Melbourne", "1020", "Woolworths"));

//         // Mock
//         doReturn(expectedStores).when(storeRepository).executeGetForObject(anyString(), eq(Store[].class));

//         // Execute
//         List<Store> result = storeRepository.findStoresByPostcodesAndChains(Arrays.asList("1000", "1020"), null);

//         // Verify
//         assertEquals(expectedStores, result);
//     }

//     @Test
//     public void testFindStoresByPostcodesAndChains_Postcodes1000And1020ChainWoolworths() {
//         // Setup
//         List<Store> expectedStores = Arrays.asList(
//                 new Store("Woolworths Metro", "121 Swanston St, Melbourne", "1000", "Woolworths"),
//                 new Store("Woolworths Richmond", "12 Rich St, Melbourne", "1020", "Woolworths"));

//         // Mock
//         doReturn(expectedStores).when(storeRepository).executeGetForObject(anyString(), eq(Store[].class));

//         // Execute
//         List<Store> result = storeRepository.findStoresByPostcodesAndChains(Arrays.asList("1000", "1020"),
//                 Arrays.asList("Woolworths"));

//         // Verify
//         assertEquals(expectedStores, result);
//     }

//     @Test
//     public void testFindStoresByPostcodesAndChains_Postcodes1000And1020ChainsWoolworthsAndColes() {
//         // Setup
//         List<Store> expectedStores = Arrays.asList(
//                 new Store("Woolworths Metro", "121 Swanston St, Melbourne", "1000", "Woolworths"),
//                 new Store("Coles East Richmond", "16 Rich St, Melbourne", "1020", "Coles"),
//                 new Store("Woolworths Richmond", "12 Rich St, Melbourne", "1020", "Woolworths"));

//         // Mock
//         doReturn(expectedStores).when(storeRepository).executeGetForObject(anyString(), eq(Store[].class));

//         // Execute
//         List<Store> result = storeRepository.findStoresByPostcodesAndChains(Arrays.asList("1000", "1020"),
//                 Arrays.asList("Woolworths", "Coles"));

//         // Verify
//         assertEquals(expectedStores, result);
//     }

    

// }