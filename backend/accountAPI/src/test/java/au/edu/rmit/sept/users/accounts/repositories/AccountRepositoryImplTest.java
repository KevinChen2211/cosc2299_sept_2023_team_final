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
        Random rand = new Random();
        String testEmail = "myemail1" + rand.nextInt(100) + "@gmail" + rand.nextInt(100) + ".com";
        this.repo.create(new AccountModel("name1", "name2", "brisvegas", testEmail, "0987", "026153949"));
        AccountModel m2 = this.repo.findById(testEmail, "0987").get();
        assertEquals(testEmail, m2.email());
    }

    @Test
    void update_should_updateAccountToDB(){
        Random rand = new Random();
        String testEmail = "myemail2" + rand.nextInt(100) + "@gmail" + rand.nextInt(100) + ".com";
        this.repo.create(new AccountModel("name3", "name4", "neuva york", testEmail, "4567", "016373628"));
        AccountModel m = new AccountModel("newName1", "newName2", "neuva york", testEmail, "4567", "016373628");
        this.repo.update(m, m.email(), m.password());
        AccountModel m2 = this.repo.findById(testEmail, "4567").get();
        assertEquals(m2.firstName(), m.firstName());
    }



}
