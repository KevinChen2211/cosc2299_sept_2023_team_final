package au.edu.rmit.sept.users.accounts.repositories;

import au.edu.rmit.sept.users.accounts.models.AccountModel;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.net.http.HttpResponse;
import java.util.Optional;

public interface AccountRepository {
    ResponseEntity<String> create(AccountModel account);

    //Find book by ID
    Optional<AccountModel> findById(String email, String password);

    ResponseEntity<String> update(AccountModel newDetails, String email, String password);

    ResponseEntity<String> deleteById(String email, String password);
}
