package ro.wantsome;

import org.springframework.stereotype.Component;

@Component
public class SpringBean7 {

    public SpringBean7() {
        System.out.println("SpringBean7 constructor");
    }

    public void doSomething() {
        System.out.println("SpringBean7.doSomething()");
    }
}
