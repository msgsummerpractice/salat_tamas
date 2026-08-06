package com.example.testingweb;



import static org.mockito.Mockito.when;

 import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
 import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
 import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
@WebMvcTest(GreetingController.class)
class WebMockTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GreetingService service;

    @Test
    void greetingShouldReturnMessageFromService() throws Exception {
        when(service.greet()).thenReturn("Hello, Mock");
        mockMvc.perform(get("/greet"))
            .andExpect(status().isOk())
            .andExpect(content().string("Hello, Mock"));
    }
}
