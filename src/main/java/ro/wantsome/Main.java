package ro.wantsome;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        // Initialize the Spring context
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class))
        {
            // The context is now initialized and beans are created
            System.out.println("Spring context initialized");
        }
    }

}