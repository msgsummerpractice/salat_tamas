package spring_example;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class Main {

    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml")) {
            HelloWorld obj = context.getBean("HelloWorld", HelloWorld.class);
            obj.printMessage();
        }
    }
    
}
