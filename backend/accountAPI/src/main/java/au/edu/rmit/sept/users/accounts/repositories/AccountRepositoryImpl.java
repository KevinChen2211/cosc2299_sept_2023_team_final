package au.edu.rmit.sept.users.accounts.repositories;

import au.edu.rmit.sept.users.accounts.models.AccountModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.net.http.HttpResponse;
import java.util.Optional;

@Repository
public class AccountRepositoryImpl implements AccountRepository {
    private final DataSource source;

    @Autowired
    // Assign datasource
    public AccountRepositoryImpl(DataSource source) {
        this.source = source;
    }

    // Find account by email and password
    public Optional<AccountModel> findById(String email, String password) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            // Database URL
            String url = "https://qb003608hb.execute-api.ap-southeast-2.amazonaws.com/test/customers/" + email + "/"
                    + password;
            Optional<AccountModel> account = Optional.of(restTemplate.getForObject(url, AccountModel.class));
            return account;
            // error handling
        } catch (HttpClientErrorException e) {
            throw new ResponseStatusException(e.getStatusCode());
        }
    }

    @Override
    // create account
    public ResponseEntity<String> create(AccountModel account) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            // database URL
            String url = "https://qb003608hb.execute-api.ap-southeast-2.amazonaws.com/test/customers";
            restTemplate.postForObject(url, account, String.class);
            // successful creation
            return new ResponseEntity<>(HttpStatus.CREATED);
            // error handling
        } catch (HttpClientErrorException e) {
            throw new ResponseStatusException(e.getStatusCode());
        }
    }

    @Override
    // update account by email and password
    public ResponseEntity<String> update(AccountModel newDetails, String email, String password) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            // database URL
            String url = "https://qb003608hb.execute-api.ap-southeast-2.amazonaws.com/test/customers/" + email + "/"
                    + password + "?lastName=" + newDetails.lastName() + "&password=" + newDetails.password()
                    + "&phone=" + newDetails.phone() + "&firstName=" + newDetails.firstName() + "&address="
                    + newDetails.address() + "&isNotified=" + newDetails.isNotified();
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.PUT,
                    HttpEntity.EMPTY,
                    String.class);
            return response;
            // error handling
        } catch (HttpClientErrorException e) {
            throw new ResponseStatusException(e.getStatusCode());
        }
    }

    @Override
    // delete account by email and password
    public ResponseEntity<String> deleteById(String email, String password) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            // database URL
            String url = "https://qb003608hb.execute-api.ap-southeast-2.amazonaws.com/test/customers/" + email + "/"
                    + password;
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.DELETE,
                    HttpEntity.EMPTY,
                    String.class);
            return response;
            // error handling
        } catch (HttpClientErrorException e) {
            throw new ResponseStatusException(e.getStatusCode());
        }
    }

}
