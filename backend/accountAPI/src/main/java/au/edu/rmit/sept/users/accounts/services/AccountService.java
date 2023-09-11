package au.edu.rmit.sept.users.accounts.services;

import java.util.Collection;
import java.util.Optional;

import au.edu.rmit.sept.users.accounts.models.AccountModel;

public interface AccountService {

    //Declare createBook
    public AccountModel createAccount(AccountModel account);

    //Declare getBook
    public Optional<AccountModel> getAccount(String email);
}
