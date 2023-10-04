package au.edu.rmit.sept.users.accounts.repositories;

import au.edu.rmit.sept.users.accounts.models.AccountModel;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;

import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AccountRepositoryImplTest {

    @Autowired
    private Flyway flyway;

    @Autowired
    DataSource source;

    private RestTemplate restTemplate = mock(RestTemplate.class);

    AccountRepository repo;

    @BeforeEach
    private void setUp() {
        flyway.migrate();
        repo = new AccountRepositoryImpl(source);
    }

    @AfterEach
    private void tearDown() {
        flyway.clean();
    }

    @Test
    void create_should_addNewAccountToDB() {
        this.repo.create(new AccountModel("name1", "name2", "brisvegas", "testCreate@db.com", "0987", "026153949", false));
        AccountModel m2 = this.repo.findById("testCreate@db.com", "0987").get();
        assertTrue(m2 instanceof AccountModel);
        assertEquals("testCreate@db.com", m2.email());
        assertEquals(false, m2.isNotified());
        this.repo.deleteById("testCreate@db.com", "0987");
    }

    @Test
    void update_should_updateAccountToDB(){
        this.repo.create(new AccountModel("name3", "name4", "neuva york", "testUpdate@db.com", "4567", "016373628", true));
        AccountModel m = new AccountModel("newName1", "newName2", "neuva york", "testUpdate@db.com", "4567", "016373628", true);
        this.repo.update(m, m.email(), m.password());
        AccountModel m2 = this.repo.findById("testUpdate@db.com", "4567").get();
        assertEquals(m2.firstName(), m.firstName());
        assertTrue(m2 instanceof AccountModel);
        this.repo.deleteById("testUpdate@db.com", "4567");
    }

    @Test
    void findById_should_returnEmpty_when_notFound() {
        when(restTemplate.getForObject(anyString(), eq(AccountModel.class)))
            .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            repo.findById("missing@test.com", "nopass");
        });
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

}
