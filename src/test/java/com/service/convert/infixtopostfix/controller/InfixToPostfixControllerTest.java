package com.service.convert.infixtopostfix.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.convert.infixtopostfix.dto.InfixToPostfixRequest;
import com.service.convert.infixtopostfix.service.implementation.InfixToPostfixHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Junaid Shakeel
 * @project Open Source
 */
@SpringBootTest
@AutoConfigureMockMvc
public class InfixToPostfixControllerTest {

    private MockMvc mockMvc;
    @InjectMocks
    InfixToPostfixController infixToPostfixController;

    @Mock
    InfixToPostfixHandler infixToPostfixHandler;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    public void setUp() throws Exception {
        when(infixToPostfixHandler.convertInfixToPostfix("4*(5-(7+2))")).thenReturn("4 5 7 2 + - *");
        when(infixToPostfixHandler.calculatePostfixExpression("4 5 7 2 + - *")).thenReturn(-16);
        this.mockMvc= MockMvcBuilders.standaloneSetup(infixToPostfixController).build();
    }


    @Test
    public void getInfixToPostfixOperation() throws Exception {

        InfixToPostfixRequest req = new InfixToPostfixRequest("4*(5-(7+2))");
        System.out.println(asJsonString(req));
        mockMvc.perform(post("/v1/compute/")
                        .content(asJsonString(req))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(-16));
    }


    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
