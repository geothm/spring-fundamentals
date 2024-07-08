package ro.wantsome.springboot;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertyInjectionBean {

    @Value("${database.url}")
    private String databaseUrl;

    @Value("${database.user}")
    private String databaseUser;

    @Value("${database.password}")
    private String databasePassword;

    @PostConstruct
    void init(){
        System.out.println("PropertyInjectionBean init method");
        System.out.println("Database URL: " + databaseUrl);
        System.out.println("Database User: " + databaseUser);
        System.out.println("Database Password: " + databasePassword);
    }

}
