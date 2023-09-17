//package au.edu.rmit.sept.users.accounts.controllers;
//
//import au.edu.rmit.sept.users.accounts.AccountsApplication;
//import au.edu.rmit.sept.users.accounts.models.AccountModel;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.flywaydb.core.Flyway;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultMatcher;
//
//import static net.bytebuddy.matcher.ElementMatchers.is;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = AccountsApplication.class)
//@AutoConfigureMockMvc
//public class AccountControllerIntegrationTest {
//
//    @Autowired
//    MockMvc mvc;
//
//    @Test
//    void get_account_email_jane() throws Exception {
//        mvc.perform(get("/v1/account/jane.doe@gmail.com/5678").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(
//                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
////                .andExpect(jsonPath("$.firstName", is("Jane")));
//    }
//
//    @Test
//    void get_account_not_found() throws Exception {
//        mvc.perform(get("/v1/account/nonexist@no.com/0000").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound())
//                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON));
////                .andExpect(jsonPath("$.title", is("Not Found")));
//    }
//
//    public static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//}
