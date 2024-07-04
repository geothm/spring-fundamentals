package ro.wantsome;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class SpringBean10 {

    public SpringBean10() {
        System.out.println("SpringBean10 constructor");
    }
}
