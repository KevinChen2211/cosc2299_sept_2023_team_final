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
//URL
@RequestMapping(value = "v1/account")
public class AccountController {

    private AccountService service;

    @Autowired
    // Assign service
    public AccountController(AccountService serv){
        this.service=serv;
    }

    // Create Account
    // v1/account/create
    // json body must have accountModel information
    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public void newAccount(@RequestBody AccountModel account) {
        this.service.createAccount(account);
    }

    // Get Account
    // v1/account/{email}/{password}
    @GetMapping("/{email}/{password}")
    public Optional<AccountModel> get(@PathVariable String email, @PathVariable String password) {
        Optional<AccountModel> currAcc = service.getAccount(email, password);
        if (currAcc.get().password().equals(password)){
            return currAcc;
        }
        return Optional.empty();
    }

    // Update Account
    // v1/account/update/{email}/{password}
    // json body must have new account details with all fields
    @PutMapping("/update/{email}/{password}")
    public ResponseEntity<String> update(@PathVariable String email, @PathVariable String password, @RequestBody AccountModel accountDetails) {
        Optional<AccountModel> result = get(email, password);
        // only update if account already exists
        if (result.isPresent()) {
            this.service.updateAccount(accountDetails, email, password);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete Account
    // v1/account/delete/{email}/{password}
    @DeleteMapping("delete/{email}/{password}")
    public ResponseEntity<String> delete (@PathVariable String email, @PathVariable String password) {
        ResponseEntity<String> deleteResponse = service.deleteAccount(email, password);
        return deleteResponse;
    }

}
