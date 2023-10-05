package au.edu.rmit.sept.users.accounts.controllers;

import au.edu.rmit.sept.users.accounts.models.AccountModel;
import au.edu.rmit.sept.users.accounts.services.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
        AccountModel m = new AccountModel("Billy", "Howard", "11 Albert Parade", "billy.howard@jim.com", "1234", "0498765", true);
        this.controller.newAccount(m);
        verify(this.service, times(1)).createAccount(m);
    }

    @Test
    void get_should_returnAccountDetails_When_available() {
        when(this.service.getAccount("howard@duck.com", "password"))
                .thenReturn(Optional.of(new AccountModel("howard", "duck", "space", "howard@duck.com", "password", "2034", false)));
        Optional<AccountModel> m = this.controller.get("howard@duck.com", "password");
        assertNotNull(m);
        assertTrue(m.get() instanceof AccountModel);
        assertEquals("howard@duck.com", m.get().email());
        assertEquals(false, m.get().isNotified());
    }

    @Test
    void get_should_throwException_when_NotFound() {
        assertThrows(RuntimeException.class,
                () -> {
                    this.controller.get("lilly@nonexist.com", "apassword");
                });
    }

}
