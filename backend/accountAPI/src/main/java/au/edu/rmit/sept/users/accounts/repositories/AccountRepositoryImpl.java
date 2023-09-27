package au.edu.rmit.sept.users.accounts.repositories;

import au.edu.rmit.sept.users.accounts.models.AccountModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.net.http.HttpResponse;
import java.util.Optional;

@Repository
public class AccountRepositoryImpl implements AccountRepository{
    private final DataSource source;

    @Autowired
    public AccountRepositoryImpl(DataSource source){
        this.source = source;
    }


    public Optional<AccountModel> findById(String email, String password) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://qb003608hb.execute-api.ap-southeast-2.amazonaws.com/test/customers/" + email + "/" + password;
            Optional<AccountModel> account = Optional.of(restTemplate.getForObject(url, AccountModel.class));
            return account;
        } catch (HttpClientErrorException e) {
            throw new HttpClientErrorException(e.getStatusCode());
        }
    }
    @Override
    public ResponseEntity<String> create(AccountModel account) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://qb003608hb.execute-api.ap-southeast-2.amazonaws.com/test/customers";
            restTemplate.postForObject(url, account, String.class);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    @Override
    public ResponseEntity<String> update(AccountModel newDetails, String email, String password) {
        try{
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://qb003608hb.execute-api.ap-southeast-2.amazonaws.com/test/customers/" + email + "/" + password + "?lastName= " + newDetails.lastName() + "&password=" + newDetails.password() + "&phone= " + newDetails.phone() + "&firstName=" + newDetails.firstName() +"&address=" + newDetails.address();
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.PUT,
                    HttpEntity.EMPTY,
                    String.class);
            return response;
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    @Override
    public ResponseEntity<String> deleteById(String email, String password) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://qb003608hb.execute-api.ap-southeast-2.amazonaws.com/test/customers/" + email + "/" + password;
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.DELETE,
                    HttpEntity.EMPTY,
                    String.class);
            return response;
        } catch (HttpClientErrorException e) {
            throw new HttpClientErrorException(e.getStatusCode());
        }
    }


}
