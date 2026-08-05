package com.example.testingweb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

 import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
 import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
 import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 
@SpringBootTest
class TestingwebApplicationTests {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;
     @BeforeEach
     void setUp() {
         this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
     }
     @Test
     void greetingShouldReturnDefaultMessage() throws Exception {
         mockMvc.perform(get("/"))
             .andExpect(status().isOk())
             .andExpect(content().string("Hello, World!"));
     }
}
