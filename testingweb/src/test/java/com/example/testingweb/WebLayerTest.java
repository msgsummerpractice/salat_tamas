package com.example.testingweb;

import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HomeController.class)
class WebLayerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    void greetingShouldReturnDefaultMessage() throws Exception{
        mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Hello, World")));
    }
}
