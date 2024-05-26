package id.ac.ui.cs.advprog.functionality.controller;

import id.ac.ui.cs.advprog.functionality.model.User;
import id.ac.ui.cs.advprog.functionality.service.UserSearchService;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class UserSearchControllerTest {

    @Mock
    UserSearchService userSearchService;

    @InjectMocks
    UserSearchController userSearchController;


    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(userSearchController).build();
    }

    @Test
    public void testSearchUsers() throws Exception {
        // Prepare mock data
        User user1 = new User("User1", "01@gmail.com", "01", "12345");
        User user2 = new User("User2", "02@gmail.com", "02", "12345");
        List<User> userNameList = Arrays.asList(user1);
        List<User> userEmailList = Arrays.asList(user2);

        when(userSearchService.findByName("User")).thenReturn(userNameList);
        when(userSearchService.findByEmail("User")).thenReturn(userEmailList);

        mockMvc.perform(get("/users/search").param("nama", "User"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nama").value("User1"))
                .andExpect(jsonPath("$[0].email").value("01@gmail.com"))
                .andExpect(jsonPath("$[0].noTelp").value("01"))
                .andExpect(jsonPath("$[1].nama").value("User2"))
                .andExpect(jsonPath("$[1].email").value("02@gmail.com"))
                .andExpect(jsonPath("$[1].noTelp").value("02"));


    }

    @Test
    public void testSearchUsersIfNotFound() throws Exception {
        when(userSearchService.findByName("NonExistingUser")).thenReturn(Collections.emptyList());
        when(userSearchService.findByEmail("NonExistingUser")).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/users/search").param("nama", "NonExistingUser"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void testSearchUsersIfEmptyParam() throws Exception {
        when(userSearchService.findByName("")).thenReturn(Collections.emptyList());
        when(userSearchService.findByEmail("")).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/users/search").param("nama", ""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}
