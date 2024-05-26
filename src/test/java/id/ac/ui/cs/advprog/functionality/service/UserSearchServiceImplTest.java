//package id.ac.ui.cs.advprog.functionality.service;
//
//import id.ac.ui.cs.advprog.functionality.model.User;
//import id.ac.ui.cs.advprog.functionality.repository.UserSearchByEmailRepository;
//import id.ac.ui.cs.advprog.functionality.repository.UserSearchByNameRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.mockito.Mockito.doReturn;
//
//@ExtendWith(MockitoExtension.class)
//public class UserSearchServiceImplTest {
//    @InjectMocks
//    UserSearchServiceImpl userSearchService;
//
//    @Mock
//    UserSearchByNameRepository userSearchByNameRepository;
//
//    @Mock
//    UserSearchByEmailRepository userSearchByEmailRepository;
//
//    @Test
//    void testSearchByNameIfValidWithOneData(){
//        User user1 = new User("Rey", "John@gmail.com", "0812", "Keren");
//        User user2 = new User("Lex", "Arthur@gmail.com", "0812", "Keren");
//
//        List<User> userData = new ArrayList<>();
//        userData.add(user1);
//        userData.add(user2);
//
//        List<User> expectedUsers = new ArrayList<>();
//        expectedUsers.add(userData.getFirst());
//
//        doReturn(expectedUsers).when(userSearchByNameRepository).findByNamaStartingWith("Re");
//
//        List<User> actualUsers = userSearchService.findByName("Re");
//
//        assertEquals(expectedUsers.size(), actualUsers.size());
//    }
//
//    @Test
//    void testSearchByEmailIfValidWIthOneData(){
//        User user1 = new User("Rey", "John@gmail.com", "0812", "Keren");
//        User user2 = new User("Lex", "Arthur@gmail.com", "0812", "Keren");
//
//        List<User> userData = new ArrayList<>();
//        userData.add(user1);
//        userData.add(user2);
//
//        List<User> expectedUsers = new ArrayList<>();
//        expectedUsers.add(userData.getFirst());
//
//        doReturn(expectedUsers).when(userSearchByEmailRepository).findByEmailStartingWith("Jo");
//
//        List<User> actualUsers = userSearchService.findByEmail("Jo");
//
//        assertEquals(actualUsers.size(), expectedUsers.size());
//
//    }
//
//    @Test
//    void testSearchByNameIfValidWithALlData(){
//        User user1 = new User("Rey", "John@gmail.com", "0812", "Keren");
//        User user2 = new User("Reyhan", "Arthur@gmail.com", "0812", "Keren");
//
//        List<User> userData = new ArrayList<>();
//        userData.add(user1);
//        userData.add(user2);
//
//        List<User> expectedUsers = new ArrayList<>();
//        expectedUsers.add(userData.getFirst());
//        expectedUsers.add(userData.getLast());
//
//        doReturn(expectedUsers).when(userSearchByNameRepository).findByNamaStartingWith("Re");
//
//        List<User> actualUsers = userSearchService.findByName("Re");
//
//        assertEquals(expectedUsers.size(), actualUsers.size());
//    }
//
//    @Test
//    void testSearchByEmailIfValidWithALlData(){
//        User user1 = new User("Rey", "John@gmail.com", "0812", "Keren");
//        User user2 = new User("Lex", "Jonathan@gmail.com", "0812", "Keren");
//
//        List<User> userData = new ArrayList<>();
//        userData.add(user1);
//        userData.add(user2);
//
//        List<User> expectedUsers = new ArrayList<>();
//        expectedUsers.add(userData.getFirst());
//        expectedUsers.add(userData.getLast());
//
//        doReturn(expectedUsers).when(userSearchByEmailRepository).findByEmailStartingWith("Jo");
//
//        List<User> actualUsers = userSearchService.findByEmail("Jo");
//
//        assertEquals(actualUsers.size(), expectedUsers.size());
//
//    }
//
//    @Test
//    void testSearchByNameIfInvalidWithALlData(){
//        User user1 = new User("Rey", "John@gmail.com", "0812", "Keren");
//        User user2 = new User("Reyhan", "Arthur@gmail.com", "0812", "Keren");
//
//        List<User> userData = new ArrayList<>();
//        userData.add(user1);
//        userData.add(user2);
//
//        List<User> expectedUsers = new ArrayList<>();
//
//        doReturn(expectedUsers).when(userSearchByNameRepository).findByNamaStartingWith("Na");
//
//        List<User> actualUsers = userSearchService.findByName("Na");
//
//        assertEquals(expectedUsers.size(), actualUsers.size());
//    }
//
//    @Test
//    void testSearchByEmailIfInvalidWithALlData(){
//        User user1 = new User("Rey", "John@gmail.com", "0812", "Keren");
//        User user2 = new User("Lex", "Jonathan@gmail.com", "0812", "Keren");
//
//        List<User> userData = new ArrayList<>();
//        userData.add(user1);
//        userData.add(user2);
//
//        List<User> expectedUsers = new ArrayList<>();
//
//        doReturn(expectedUsers).when(userSearchByEmailRepository).findByEmailStartingWith("Ze");
//
//        List<User> actualUsers = userSearchService.findByEmail("Ze");
//
//        assertEquals(actualUsers.size(), expectedUsers.size());
//
//    }
//}
