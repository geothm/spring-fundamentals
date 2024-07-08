package ro.wantsome.springboot;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DevBean {

    public DevBean() {
        System.out.println("DevBean constructor");
    }

}
