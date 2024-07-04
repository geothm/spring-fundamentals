package ro.wantsome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SpringBean8 {

    private  SpringBean7 springBean7;
    private final SpringBean10 springBean10;

    public SpringBean8(SpringBean10 springBean10) {
        this.springBean10 = springBean10;
        System.out.println("SpringBean8 constructor");
    }

    @Autowired(required = false)
    public void setSpringBean7(SpringBean7 springBean7) {
        System.out.println("SpringBean8.setSpringBean7()");
    }

    @PostConstruct
    void init(){
        System.out.println("SpringBean8 init method");
        System.out.println("SpringBean10 index: " + springBean10.getIndex());

        if (springBean7 != null) {
            springBean7.doSomething();
            System.out.println("SpringBean7 index: " + springBean7.getIndex());
        }
    }
}
