package id.ac.ui.cs.advprog.functionality.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UserTest {

    User user1;

    @BeforeEach
    public void setUp(){
        this.user1 = new User("Arthur", "Arthur@gmail.com", "0812", "Keren");
    }

    @Test
    void testGetUserIfValid(){
        assertEquals("Arthur", user1.getNama());
        assertEquals("Arthur@gmail.com", user1.getEmail());
        assertEquals("0812", user1.getNoTelp());
        assertEquals("Keren", user1.getPassword());
    }

    @Test
    void testGetUserIfInvalid(){
        assertNotEquals("Thur", user1.getNama());
        assertNotEquals("Zee", user1.getEmail());
        assertNotEquals("0811", user1.getNoTelp());
        assertNotEquals("cool", user1.getPassword());
    }

}

