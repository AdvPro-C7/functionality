package id.ac.ui.cs.advprog.functionality.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @InjectMocks
    private BookController bookController;
    private MockMvc mockMvc;

    @Test
    public void testGetBookListPage() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/book-list"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.view().name("BookListPage"));
    }
}
