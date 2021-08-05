package com.ivansousa.calculatorservice.controller;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Base64;

import com.ivansousa.calculatorservice.calculator.Calculator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(CalculusController.class)
public class CalculusControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Calculator calculator;

    @Test
    public void calculate_NoQuery_BadRequest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/calculus")).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("query must not be null or blank"));
    }

    @Test
    public void calculate_BlankQuery_BadRequest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/calculus").queryParam("query", ""))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("query must not be null or blank"));
    }

    @Test
    public void calculate_QueryInvalidBase64_BadRequest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/calculus").queryParam("query", "__"))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("query must be a valid base64 string"));
    }

    @Test
    public void calculate_QueryValidBase64_Ok() throws Exception {
        String query = "MTAgKyAxMDAgKiAoMTAgKiAwKQ==";
        String input = new String(Base64.getDecoder().decode(query));

        when(calculator.evaluate(input)).thenReturn(BigDecimal.TEN);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/calculus").queryParam("query", query))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(BigDecimal.TEN));
    }
}
