package id.ac.ui.cs.advprog.functionality.repository;

import id.ac.ui.cs.advprog.functionality.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest(showSql = true)
public class UserSearchByNameRepositoryTest {

    @Autowired
    private UserSearchByNameRepository userSearchByNameRepository;

    @Test
    public void testFindByNamaStartingWithIfNameValid() {
        User user1 = new User("John", "John@gmail.com", "0812", "Keren");
        User user2 = new User("Jo", "Jo@gmail.com", "081212", "Keren");

        userSearchByNameRepository.save(user1);
        userSearchByNameRepository.save(user2);

        List<User> users = userSearchByNameRepository.findByNamaContainingIgnoreCase("J");

        assertEquals(2, users.size());
    }

    @Test
    public void testFindByNamaStartingWithIfNameNotValid() {
        User user1 = new User("John", "John@gmail.com", "0812", "Keren");
        User user2 = new User("Jo", "Jo@gmail.com", "081212", "Keren");

        userSearchByNameRepository.save(user1);
        userSearchByNameRepository.save(user2);

        List<User> users = userSearchByNameRepository.findByNamaContainingIgnoreCase("Z");

        assertEquals(0, users.size());
    }

    @Test
    public void testFindByNamaStartingWithIfOnlyMathOneName() {
        User user1 = new User("John", "John@gmail.com", "0812", "Keren");
        User user2 = new User("Arthur", "Arthur@gmail.com", "081212", "Keren");

        userSearchByNameRepository.save(user1);
        userSearchByNameRepository.save(user2);

        List<User> users = userSearchByNameRepository.findByNamaContainingIgnoreCase("Ar");

        assertEquals(1, users.size());
    }

}