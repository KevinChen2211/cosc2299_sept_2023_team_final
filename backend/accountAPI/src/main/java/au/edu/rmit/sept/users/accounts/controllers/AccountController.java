package au.edu.rmit.sept.users.accounts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<AccountModel> newBook(@RequestBody AccountModel account) {
        AccountModel m = service.createAccount(account);
        return new ResponseEntity<AccountModel>(m, HttpStatus.CREATED);
    }

    @GetMapping("/{email}/{password}")
    public Optional<AccountModel> get(@PathVariable String email, @PathVariable String password) {
        Optional<AccountModel> currAcc = service.getAccount(email);
        System.out.println(currAcc);
        System.out.println(currAcc.get().password());
        System.out.println(password);
        if (currAcc.get().password().equals(password)){
            return currAcc;
        }
        else {
            return Optional.empty();
        }
    }

}
