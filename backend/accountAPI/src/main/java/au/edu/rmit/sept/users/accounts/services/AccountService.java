package au.edu.rmit.sept.users.accounts.services;

import java.net.http.HttpResponse;
import java.util.Optional;

import au.edu.rmit.sept.users.accounts.models.AccountModel;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public interface AccountService {

    //Declare createBook
    ResponseEntity<String> createAccount(AccountModel account);

    //Declare getBook
    Optional<AccountModel> getAccount(String email, String password);

    ResponseEntity<String> updateAccount(AccountModel updatedDetails, String email, String password);

    ResponseEntity<String> deleteAccount(String email, String password);
}
