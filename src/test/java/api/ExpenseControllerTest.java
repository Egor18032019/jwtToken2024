package api;

import com.springjwtsecurityexample.token.TokenApplication;
import com.springjwtsecurityexample.token.model.Category;
import com.springjwtsecurityexample.token.model.ExpenseResponse;
import com.springjwtsecurityexample.token.service.ExpenseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import util.JsonConverter;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TokenApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")

public class ExpenseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExpenseService expenseService;

    private ExpenseResponse expenseResponse;

    @BeforeEach
    void setUp() {
        expenseResponse = new ExpenseResponse();
        expenseResponse.setCategories(List.of(new Category("Name", 100L, "Desc", "")));
    }

    @Test
    @WithMockUser(roles = "USER")
    void whenGiveMeAllInfoUsingUserThenResponseIsOk() throws Exception {
        when(expenseService.giveMeAllInfo()).thenReturn(expenseResponse);

        mockMvc.perform(get("/api/expense"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", hasSize(1)))
                .andExpect(jsonPath("$.categories[0].name", is("Name")))
                .andExpect(jsonPath("$.categories[0].money", is(100)))
                .andExpect(jsonPath("$.categories[0].description", is("Desc")))
                .andExpect(jsonPath("$.categories[0].limit", is("")));
    }

    @Test
    void whenGiveMeAllInfoWithoutUserThenResponseIsForbidden() throws Exception {
        mockMvc.perform(get("/api/expense"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "USER")
    void whenAddOneCategoryUsingUserThenResponseIsOk() throws Exception {
        Category category = new Category("NewName", 200L, "NewDesc", "");

        when(expenseService.addOneCategoryAndGiveMeCategory(any()))
                .thenReturn(category);

        mockMvc.perform(post("/api/expense")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonConverter.asJsonString(category)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("NewName"))
                .andExpect(jsonPath("$.money").value(200))
                .andExpect(jsonPath("$.description").value("NewDesc"))
                .andExpect(jsonPath("$.limit").value(""));
    }

    @Test
    @WithMockUser(roles = "USER")
    void whenChangeOneCategoryUsingUserThenResponseIsOk() throws Exception {
        Category category = new Category("NewName", 200L, "NewDesc", "");

        when(expenseService.changeOneCategoryAndGiveMeAllInfo(any()))
                .thenReturn(category);

        mockMvc.perform(put("/api/expense")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonConverter.asJsonString(category)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("NewName"))
                .andExpect(jsonPath("$.money").value(200))
                .andExpect(jsonPath("$.description").value("NewDesc"))
                .andExpect(jsonPath("$.limit").value(""));
    }
}
