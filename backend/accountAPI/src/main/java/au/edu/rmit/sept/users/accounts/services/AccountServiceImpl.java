package au.edu.rmit.sept.users.accounts.services;

import au.edu.rmit.sept.users.accounts.models.AccountModel;
import au.edu.rmit.sept.users.accounts.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public AccountModel createAccount(AccountModel account) {
        return repository.create(account);
    }

    @Override
    public Optional<AccountModel> getAccount(String email) {
        return repository.findById(email);
    }

}
