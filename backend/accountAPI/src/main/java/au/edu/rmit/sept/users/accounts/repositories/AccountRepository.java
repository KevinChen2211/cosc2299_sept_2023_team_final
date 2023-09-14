package au.edu.rmit.sept.users.accounts.repositories;

import au.edu.rmit.sept.users.accounts.models.AccountModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface AccountRepository {
    public AccountModel create(AccountModel account);

    //Find book by ID
    public Optional<AccountModel> findById(String email, String password);

    public void update(AccountModel newDetails, String email, String password);
}
