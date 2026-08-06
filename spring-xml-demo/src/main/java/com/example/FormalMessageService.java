package com.example;

import org.springframework.stereotype.Component;

@Component("formalMessageService")
public class FormalMessageService implements MessageService {
    @Override
    public String getMessage() {
        return "Good day. Welcome to Spring.";
    }
}
