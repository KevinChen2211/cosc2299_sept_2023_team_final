package au.edu.rmit.sept.users.accounts.controllers;

import au.edu.rmit.sept.users.accounts.models.AccountModel;
import au.edu.rmit.sept.users.accounts.services.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AccountControllerTest {

    AccountController controller;
    AccountService service;

    @BeforeEach
    void setup() {
        this.service = mock(AccountService.class);
        this.controller = new AccountController(this.service);
    }

    @Test
    void newAccount_should_callCreateService() {
        AccountModel m = new AccountModel(null, "Billy", "Howard", "11 Albert Parade", "billy.howard@jim.com", "1234", "0498765");
        this.controller.newBook(m);
        verify(this.service, times(1)).createAccount(m);
    }

    @Test
    void get_should_returnBookDetails_When_available() {
        when(this.service.getAccount("howard@duck.com"))
                .thenReturn(Optional.of(new AccountModel(1L, "howard", "duck", "space", "howard@duck.com", "password", "2034")));
        Optional<AccountModel> m = this.controller.get("howard@duck.com", "password");
        assertNotNull(m);
        assertEquals("howard@duck.com", m.get().email());
    }

}
