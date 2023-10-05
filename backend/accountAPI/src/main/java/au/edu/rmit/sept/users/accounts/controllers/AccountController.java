package au.edu.rmit.sept.users.accounts.controllers;

import au.edu.rmit.sept.users.accounts.repositories.AccountRepository;
import au.edu.rmit.sept.users.accounts.repositories.AccountRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import au.edu.rmit.sept.users.accounts.models.AccountModel;
import au.edu.rmit.sept.users.accounts.services.AccountService;
import org.springframework.web.client.RestTemplate;

import javax.security.auth.login.AccountNotFoundException;
import java.net.http.HttpResponse;
import java.util.Optional;

@RestController
@RequestMapping(value = "v1/account")
public class AccountController {

    private AccountService service;

    @Autowired
    public AccountController(AccountService serv){
        this.service=serv;
    }

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public void newAccount(@RequestBody AccountModel account) {
        this.service.createAccount(account);
    }

    @GetMapping("/{email}/{password}")
    public Optional<AccountModel> get(@PathVariable String email, @PathVariable String password) {
        Optional<AccountModel> currAcc = service.getAccount(email, password);
        if (currAcc.get().password().equals(password)){
            return currAcc;
        }
        return Optional.empty();
    }

    @PutMapping("/update/{email}/{password}")
    public ResponseEntity<String> update(@PathVariable String email, @PathVariable String password, @RequestBody AccountModel accountDetails) {
        Optional<AccountModel> result = get(email, password);
        if (result.isPresent()) {
            this.service.updateAccount(accountDetails, email, password);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("delete/{email}/{password}")
    public ResponseEntity<String> delete (@PathVariable String email, @PathVariable String password) {
        ResponseEntity<String> deleteResponse = service.deleteAccount(email, password);
        return deleteResponse;
    }

}
