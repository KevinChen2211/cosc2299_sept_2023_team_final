package au.edu.rmit.sept.users.accounts.repositories;

import au.edu.rmit.sept.users.accounts.models.AccountModel;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AccountRepositoryImplTest {

    @Autowired
    private Flyway flyway;

    @Autowired
    DataSource source;

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
        AccountModel m = this.repo.create(new AccountModel(-1L, "name1", "name2", "brisvegas", "name1@name2.com", "0987", "026153949"));

        assertEquals(3, m.customerID());
        assertEquals("name1@name2.com", m.email());

        AccountModel m2 = this.repo.findById("name1@name2.com").get();
        assertEquals("name1@name2.com", m.email());
    }

}
