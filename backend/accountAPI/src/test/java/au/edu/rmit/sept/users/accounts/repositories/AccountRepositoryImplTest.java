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
        //must change email every time until delete method has been created
        this.repo.create(new AccountModel("name1", "name2", "brisvegas", "name1@name2test2.com", "0987", "026153949"));
        AccountModel m2 = this.repo.findById("name1@name2test2.com", "0987").get();
        assertEquals("name1@name2test2.com", m2.email());

        //delete method here
    }

    @Test
    void update_should_updateAccountToDB(){
        //must change email every time until delete method has been created

        this.repo.create(new AccountModel("name3", "name4", "neuva york", "name3@name5.com", "4567", "016373628"));
        AccountModel m = new AccountModel("newName1", "newName2", "neuva york", "name3@name5.com", "4567", "016373628");
        this.repo.update(m, m.email(), m.password());
        AccountModel m2 = this.repo.findById("name3@name5.com", "4567").get();
        assertEquals(m2.firstName(), m.firstName());
        //delete method here
    }



}
