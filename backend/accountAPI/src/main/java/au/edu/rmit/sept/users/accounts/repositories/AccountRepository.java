package au.edu.rmit.sept.users.accounts.repositories;

import au.edu.rmit.sept.users.accounts.models.AccountModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface AccountRepository {
    void create(AccountModel account);

    //Find book by ID
    Optional<AccountModel> findById(String email, String password);

    void update(AccountModel newDetails, String email, String password);

    ResponseEntity deleteById(String email, String password);
}
