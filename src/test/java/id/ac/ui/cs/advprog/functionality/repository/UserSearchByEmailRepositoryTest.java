package id.ac.ui.cs.advprog.functionality.repository;

import id.ac.ui.cs.advprog.functionality.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest(showSql = true)
public class UserSearchByEmailRepositoryTest {

    @Autowired
    UserSearchByEmailRepository userSearchByEmailRepository;

    @Test
    public void testFindByEmailStartingWithIfEmailValid() {
        User user1 = new User("Kim", "John@gmail.com", "0812", "Keren");
        User user2 = new User("Han", "Jo@gmail.com", "0812", "Keren");

        userSearchByEmailRepository.save(user1);
        userSearchByEmailRepository.save(user2);

        List<User> users = userSearchByEmailRepository.findByEmailStartingWith("J");

        assertEquals(2, users.size());
    }

    @Test
    public void testFindByEmailStartingWithIfEmailNotValid() {
        User user1 = new User("John", "John@gmail.com", "0812", "Keren");
        User user2 = new User("Jo", "Jo@gmail.com", "0812", "Keren");

        userSearchByEmailRepository.save(user1);
        userSearchByEmailRepository.save(user2);

        List<User> users = userSearchByEmailRepository.findByEmailStartingWith("A");

        assertEquals(0, users.size());
    }

    @Test
    public void testFindByEmailStartingWithIfOnlyMatchOneEmail() {
        User user1 = new User("Rey", "John@gmail.com", "0812", "Keren");
        User user2 = new User("Lex", "Arthur@gmail.com", "0812", "Keren");

        userSearchByEmailRepository.save(user1);
        userSearchByEmailRepository.save(user2);

        List<User> users = userSearchByEmailRepository.findByEmailStartingWith("Ar");

        assertEquals(1, users.size());
    }

}
