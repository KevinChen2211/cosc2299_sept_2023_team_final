package au.edu.rmit.sept.users.accounts.services;

import java.util.Optional;

import au.edu.rmit.sept.users.accounts.models.AccountModel;
import org.springframework.http.ResponseEntity;

public interface AccountService {

    //Declare createBook
    public AccountModel createAccount(AccountModel account);

    //Declare getBook
    public Optional<AccountModel> getAccount(String email, String password);

    public void updateAccount(AccountModel updatedDetails, String email, String password);
}
