package com.example.testingweb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HttpRequestTest {
    
    @LocalServerPort
    private int port;
    
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void greetingShouldReturnDefaultMessage() {
        String url = "http://localhost:" + port + "/";
        String response = this.restTemplate.getForObject(url, String.class);
        assertEquals("Hello, World!", response);
    }
}
