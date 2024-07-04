package ro.wantsome;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class SpringBean10 {

    private double index;

    public SpringBean10() {
        index = Math.random();
        System.out.println("SpringBean10 constructor " + index);
    }

    public double getIndex() {
        return index;
    }
}
