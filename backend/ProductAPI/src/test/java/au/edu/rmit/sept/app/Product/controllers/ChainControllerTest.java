
// package au.edu.rmit.sept.app.Product.controllers;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;

// import au.edu.rmit.sept.app.Product.models.Chain;
// import au.edu.rmit.sept.app.Product.services.ChainService;

// import static org.mockito.Mockito.*;
// import static org.junit.jupiter.api.Assertions.*;

// import java.util.Arrays;
// import java.util.List;

// public class ChainControllerTest {

//     private ChainController chainController;
//     private ChainService chainService;

//     @BeforeEach
//     public void setUp() {
//         chainService = mock(ChainService.class);
//         chainController = new ChainController(chainService);
//     }

//     @Test
//     public void testGetAllChains_SuccessWithChains() {
//         List<Chain> mockChains = Arrays.asList(
//             new Chain("Coles", 4.33),
//             new Chain("Aldi", 4.52),
//             new Chain("Woolworths", 4.23)
//         );

//         when(chainService.getChains()).thenReturn(mockChains);

//         ResponseEntity<Object> response = chainController.getAllChains();
//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertEquals(mockChains, response.getBody());
//     }

//     @Test
//     public void testGetChainByName_SuccessWithChain() {
//         Chain mockChain = new Chain("Coles", 4.33);
//         when(chainService.getByName("Coles")).thenReturn(mockChain);

//         ResponseEntity<Object> response = chainController.getChainByName("Coles");
//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertEquals(mockChain, response.getBody());
//     }

//     @Test
//     public void testGetChainByName_NullForWrongChainName() {
//         when(chainService.getByName("Coless")).thenReturn(null);

//         ResponseEntity<Object> response = chainController.getChainByName("Coless");
//         assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//         assertEquals("not found", response.getBody());
//     }
// }
