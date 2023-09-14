package au.edu.rmit.sept.users.accounts.repositories;

import au.edu.rmit.sept.users.accounts.models.AccountModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.util.Optional;

@Repository
public class AccountRepositoryImpl implements AccountRepository{
    private final DataSource source;

    @Autowired
    public AccountRepositoryImpl(DataSource source){
        this.source = source;
    }


    public Optional<AccountModel> findById(String email, String password) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://qb003608hb.execute-api.ap-southeast-2.amazonaws.com/test/customers/" + email + "/" + password;
        Optional<AccountModel> account = Optional.of(restTemplate.getForObject(url, AccountModel.class));
        try {
            if (account.get().email().equals(email)) {
                return account;
            }
        } catch (HttpClientErrorException.NotFound e) {

        }
        return null;
    }
    @Override
    public AccountModel create(AccountModel account) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://qb003608hb.execute-api.ap-southeast-2.amazonaws.com/test/customers";
        ResponseEntity<AccountModel> response = restTemplate.postForEntity(url, account, AccountModel.class);
        System.out.println(response.getStatusCode());
        return response.getBody();
    }

    @Override
    public void update(AccountModel newDetails, String email, String password) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://qb003608hb.execute-api.ap-southeast-2.amazonaws.com/test/customers/" + email + "/" + password + "?lastName= " + newDetails.lastName() + "&password=" + newDetails.password() + "&phone= " + newDetails.phone() + "&firstName=" + newDetails.firstName() +"&address=" + newDetails.address();
        restTemplate.exchange(
                url,
                HttpMethod.PUT,
                HttpEntity.EMPTY,
                Void.class);
    }


}
