package au.edu.rmit.sept.users.accounts.services;

import au.edu.rmit.sept.users.accounts.models.AccountModel;
import au.edu.rmit.sept.users.accounts.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{
    private AccountRepository repository;

    // Assign repo to repository
    @Autowired
    public AccountServiceImpl(AccountRepository repo){
        this.repository=repo;
    }

    @Override
    public void createAccount(AccountModel account){repository.create(account);}

    @Override
    public Optional<AccountModel> getAccount(String email, String password) {
        return repository.findById(email, password);
    }

    @Override
    public ResponseEntity<HttpStatus> updateAccount(AccountModel updatedDetails, String email, String password) {
        return repository.update(updatedDetails, email, password);
    }

    @Override
    public ResponseEntity<String> deleteAccount(String email, String password) {
        return repository.deleteById(email, password);
    }

}
