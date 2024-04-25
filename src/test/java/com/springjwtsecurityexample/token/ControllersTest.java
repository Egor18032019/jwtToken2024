package com.springjwtsecurityexample.token;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springjwtsecurityexample.token.model.SignUpRequest;
import com.springjwtsecurityexample.token.utils.EndPoint;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllersTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void greatNewUser() throws Exception {

        SignUpRequest request = new SignUpRequest("new", "new@mail.ru", "123");
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(
                        MockMvcRequestBuilders.post(EndPoint.api + EndPoint.auth + EndPoint.register)
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(jsonPath("$.token").exists())
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void getWithoutToken() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get(EndPoint.example)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    void auth() throws Exception {

        SignUpRequest request = new SignUpRequest("new", "new@mail.ru", "123");
        ObjectMapper objectMapper = new ObjectMapper();
        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders.post(EndPoint.api + EndPoint.auth + EndPoint.login)
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(jsonPath("$.token").exists())
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        String response = result.getResponse().getContentAsString();
        response = response.replace("{\"token\":\"", "");
        String token = response.replace("\"}", "");

        mockMvc.perform(
                        MockMvcRequestBuilders.get(EndPoint.example)
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    //todo негативный тест + удаление после кейса + на обновления теста

}
