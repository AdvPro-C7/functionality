package id.ac.ui.cs.advprog.functionality.service;

import id.ac.ui.cs.advprog.functionality.model.User;
import id.ac.ui.cs.advprog.functionality.repository.UserSearchByEmailRepository;
import id.ac.ui.cs.advprog.functionality.repository.UserSearchByNameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class UserSearchServiceImplTest {
    @InjectMocks
    UserSearchServiceImpl userSearchService;

    @Mock
    UserSearchByNameRepository userSearchByNameRepository;

    @Mock
    UserSearchByEmailRepository userSearchByEmailRepository;

    List<User> userData;
    @BeforeEach
    void setUp(){
        userData = new ArrayList<>();

        User user1 = new User("Rey", "John@gmail.com", "0812", "Keren");
        User user2 = new User("Lex", "Arthur@gmail.com", "081212", "Keren");

        userData.add(user1);
        userData.add(user2);
    }

    @Test
    void testSearchByNameIfValidWithOneData(){

        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(userData.getFirst());

        doReturn(expectedUsers).when(userSearchByNameRepository).findByNamaContainingIgnoreCase("Re");

        List<User> actualUsers = userSearchService.findByName("Re");

        assertEquals(expectedUsers.size(), actualUsers.size());
    }

    @Test
    void testSearchByEmailIfValidWIthOneData(){
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(userData.getFirst());

        doReturn(expectedUsers).when(userSearchByEmailRepository).findByEmailContainingIgnoreCase("Jo");

        List<User> actualUsers = userSearchService.findByEmail("Jo");

        assertEquals(actualUsers.size(), expectedUsers.size());

    }

    @Test
    void testSearchByNameIfValidWithALlData(){

        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(userData.getFirst());
        expectedUsers.add(userData.getLast());

        doReturn(expectedUsers).when(userSearchByNameRepository).findByNamaContainingIgnoreCase("Re");

        List<User> actualUsers = userSearchService.findByName("Re");

        assertEquals(expectedUsers.size(), actualUsers.size());
    }

    @Test
    void testSearchByEmailIfValidWithALlData(){

        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(userData.getFirst());
        expectedUsers.add(userData.getLast());

        doReturn(expectedUsers).when(userSearchByEmailRepository).findByEmailContainingIgnoreCase("Jo");

        List<User> actualUsers = userSearchService.findByEmail("Jo");

        assertEquals(actualUsers.size(), expectedUsers.size());

    }

    @Test
    void testSearchByNameIfInvalidWithALlData(){

        List<User> expectedUsers = new ArrayList<>();

        doReturn(expectedUsers).when(userSearchByNameRepository).findByNamaContainingIgnoreCase("Na");

        List<User> actualUsers = userSearchService.findByName("Na");

        assertEquals(expectedUsers.size(), actualUsers.size());
    }

    @Test
    void testSearchByEmailIfInvalidWithALlData(){

        List<User> expectedUsers = new ArrayList<>();

        doReturn(expectedUsers).when(userSearchByEmailRepository).findByEmailContainingIgnoreCase("Ze");

        List<User> actualUsers = userSearchService.findByEmail("Ze");

        assertEquals(actualUsers.size(), expectedUsers.size());

    }

    @Test
    void testFindById(){
        User user1 = userData.getFirst();
        doReturn(Optional.of(user1)).when(userSearchByNameRepository).findById(user1.getId());

        Optional<User> result = userSearchByNameRepository.findById(user1.getId());
        assertTrue(result.isPresent());
        assertEquals(user1.getId(), result.get().getId());
    }

    @Test
    void testFindByIdIfNotFound(){
        doReturn(Optional.empty()).when(userSearchByNameRepository).findById(10L);

        Optional<User> result = userSearchByNameRepository.findById(10L);
        assertTrue(result.isEmpty());
    }
}
