package ro.wantsome.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class MainTests {

    @Autowired
    private ApplicationContext context; // Can use injected components

    @Test
    void contextLoads() {
        assertNotNull(context);

    }

}
