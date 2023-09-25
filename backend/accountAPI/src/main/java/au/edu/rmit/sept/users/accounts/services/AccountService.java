package au.edu.rmit.sept.users.accounts.services;

import java.util.Optional;

import au.edu.rmit.sept.users.accounts.models.AccountModel;
import org.springframework.http.ResponseEntity;

public interface AccountService {

    //Declare createBook
    void createAccount(AccountModel account);

    //Declare getBook
    Optional<AccountModel> getAccount(String email, String password);

    void updateAccount(AccountModel updatedDetails, String email, String password);

    ResponseEntity deleteAccount(String email, String password);
}
