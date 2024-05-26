package id.ac.ui.cs.advprog.functionality.config;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@SpringBootTest
@AutoConfigureMockMvc
@Configuration
class CorsConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void corsConfigurationsAreApplied() throws Exception {
        mockMvc.perform(get("/any-endpoint")
                        .header("Origin", "http://localhost:5500"))
                .andExpect(header().string("Access-Control-Allow-Origin", "http://localhost:5500"))
                .andExpect(header().string("Access-Control-Allow-Credentials", "true"));
    }
}