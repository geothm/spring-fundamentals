package ro.wantsome;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Configuration
@ComponentScan(basePackages = "ro.wantsome")
@PropertySource("classpath:application.properties")
@ImportResource("classpath:applicationContext.xml")
public class AppConfig {

    @Value("${database.url}")
    private String databaseUrl;

    @Value("${database.user}")
    private String databaseUser;

    @Value("${database.password}")
    private String databasePassword;

    public AppConfig() {
        System.out.println("App config constructor: " + databaseUrl + " " + databaseUser + " " + databasePassword);
    }

    @PostConstruct
    void init(){
        System.out.println("App config post construct: " + databaseUrl + " " + databaseUser + " " + databasePassword);
    }

    @Bean
    public SpringBean2 springBean2() {
        return new SpringBean2();
    }

    @PreDestroy
    void destroy(){
        System.out.println("App config destroy: ");
    }
}
