package au.edu.rmit.sept.users.accounts.controllers;

import au.edu.rmit.sept.users.accounts.repositories.AccountRepository;
import au.edu.rmit.sept.users.accounts.repositories.AccountRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import au.edu.rmit.sept.users.accounts.models.AccountModel;
import au.edu.rmit.sept.users.accounts.services.AccountService;

import java.sql.SQLException;
import java.util.Optional;

@RestController
@RequestMapping(value = "v1/account")
public class AccountController {

    private AccountService service;

    @Autowired
    public AccountController(AccountService serv){
        this.service=serv;
    }

    @PostMapping
    public ResponseEntity<AccountModel> newAccount(@RequestBody AccountModel account) {
        AccountModel m = service.createAccount(account);
        return new ResponseEntity<AccountModel>(m, HttpStatus.CREATED);
    }

    @GetMapping("/{email}/{password}")
    public Optional<AccountModel> get(@PathVariable String email, @PathVariable String password) {
        Optional<AccountModel> currAcc = service.getAccount(email, password);
        if (currAcc.get().password().equals(password)){
            return currAcc;
        }
        else {
            return Optional.empty();
        }
    }


    @PutMapping("/update/{email}/{password}")
    public ResponseEntity<HttpStatus> update(@PathVariable String email, @PathVariable String password, @RequestBody AccountModel accountDetails) {
        Optional<AccountModel> result = get(email, password);
        if (result.isPresent()) {
            this.service.updateAccount(accountDetails, email, password);
            return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        }
    }

}
