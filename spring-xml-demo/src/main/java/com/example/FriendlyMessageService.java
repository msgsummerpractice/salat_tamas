package com.example;
import org.springframework.stereotype.Component;

@Component("friendlyMessageService")
public class FriendlyMessageService implements MessageService {
    @Override
    public String getMessage() {
        return "Hey, nice to see you using Spring!";
    }
}
