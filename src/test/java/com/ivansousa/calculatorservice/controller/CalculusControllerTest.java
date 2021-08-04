package com.ivansousa.calculatorservice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(CalculusController.class)
public class CalculusControllerTest {
    @Autowired
    private MockMvc mockMvc;

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
    public void calculate_ValidQuery_Ok() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/calculus").queryParam("query",
                        "MiAqICgyMy8oMyozKSktIDIzICogKDIqMyk="))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").exists());
    }
}
