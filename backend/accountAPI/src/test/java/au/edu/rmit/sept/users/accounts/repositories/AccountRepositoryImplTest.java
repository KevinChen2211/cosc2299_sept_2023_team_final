package au.edu.rmit.sept.users.accounts.repositories;

import au.edu.rmit.sept.users.accounts.models.AccountModel;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

import java.util.Random;

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
        this.repo.create(new AccountModel("name1", "name2", "brisvegas", "testCreate@db.com", "0987", "026153949"));
        AccountModel m2 = this.repo.findById("testCreate@db.com", "0987").get();
        assertEquals("testCreate@db.com", m2.email());
        this.repo.deleteById("testCreate@db.com", "0987");
    }

    @Test
    void update_should_updateAccountToDB(){
        this.repo.create(new AccountModel("name3", "name4", "neuva york", "testUpdate@db.com", "4567", "016373628"));
        AccountModel m = new AccountModel("newName1", "newName2", "neuva york", "testUpdate@db.com", "4567", "016373628");
        this.repo.update(m, m.email(), m.password());
        AccountModel m2 = this.repo.findById("testUpdate@db.com", "4567").get();
        assertEquals(m2.firstName(), m.firstName());
        this.repo.deleteById("testUpdate@db.com", "4567");
    }



}
