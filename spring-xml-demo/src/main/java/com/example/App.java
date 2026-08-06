package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class)) {
            ApplicationContext applicationContext = context;
            GreetingService greetingService = applicationContext.getBean("greetingService",GreetingService.class);
            System.out.println(greetingService.getMessage());
        }
    }
}
