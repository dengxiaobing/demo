package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author: deng
 * @date: 2020/1/10
 * @time: 11:39
 * @desc：
 */
@AutoConfigureMockMvc
@SpringBootTest
class ExceptionControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void should_return_400_if_param_not_valid() throws Exception {
        mockMvc.perform(get("/api/illegalArgumentException"))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.message").value("参数错误！"))
                .andExpect(jsonPath("$.errorTypeName").value("java.lang.IllegalArgumentException"));
    }

    @Test
    void should_return_404_if_resourse_not_found() throws Exception {
        mockMvc.perform(get("/api/resourceNotFoundException"))
                .andExpect(status().is(404))
                .andExpect(jsonPath("$.message").value("Sorry, the resourse not found!"))
                .andExpect(jsonPath("$.errorTypeName").value("com.example.demo.exception.ResourceNotFoundException"));
    }

    @Test
    void should_return_404_if_resourse_not_found2() throws Exception {
        mockMvc.perform(get("/api/resourceNotFoundException2"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Resourse Not Found"));
    }
}