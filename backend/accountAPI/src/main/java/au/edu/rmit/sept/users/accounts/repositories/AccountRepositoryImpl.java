package au.edu.rmit.sept.users.accounts.repositories;

import au.edu.rmit.sept.users.accounts.models.AccountModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

@Repository
public class AccountRepositoryImpl implements AccountRepository{
    private final DataSource source;

    @Autowired
    public AccountRepositoryImpl(DataSource source){
        this.source = source;
    }

    private AccountModel extractAccount(ResultSet rs) throws SQLException {
        return new AccountModel(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
    }

    @Override
    public AccountModel create(AccountModel account) {
        try {
            PreparedStatement stm = this.source.getConnection().prepareStatement(
                    "INSERT INTO accounts (firstName, lastName, address, email, password, phone) VALUES (?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            stm.setString(1, account.firstName());
            stm.setString(2, account.lastName());
            stm.setString(3, account.address());
            stm.setString(4, account.email());
            stm.setString(5, account.password());
            stm.setString(6, account.phone());
            int row = stm.executeUpdate();

            if (row == 0) {
                throw new SQLException("Failed to create Book = " + account.toString());
            }

            ResultSet generatedKeys = stm.getGeneratedKeys();
            if (generatedKeys.next()) {
                Long id = generatedKeys.getLong(1);
                return new AccountModel(account.firstName(), account.firstName(), account.address(), account.email(), account.password(), account.phone());
            } else {
                throw new SQLException("Creating book failed, no ID obtained.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error in create", e);
        }
    }

    public Optional<AccountModel> findById(String email, String password) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://qb003608hb.execute-api.ap-southeast-2.amazonaws.com/test/customers/" + email + "/" + password;
        AccountModel account = restTemplate.getForObject(url, AccountModel.class);

        try {
            if (account.email().equals(email)) { //if email == gotten email
                return Optional.of(account);
            }
            return Optional.empty();
        } catch (HttpClientErrorException.NotFound e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(AccountModel newDetails, String email, String password) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://qb003608hb.execute-api.ap-southeast-2.amazonaws.com/test/customers/" + email + "/" + password + "?lastName= " + newDetails.lastName() + "&password=" + newDetails.password() + "&phone= " + newDetails.phone() + "&firstName=" + newDetails.firstName() +"&address=" + newDetails.address() /*105%20Example%20St*/;

    }

}
