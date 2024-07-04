package ro.wantsome;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SpringBean8 {

    private final SpringBean7 springBean7;

    public SpringBean8(SpringBean7 springBean7) {
        this.springBean7 = springBean7;
        System.out.println("SpringBean8 constructor");
    }

    @PostConstruct
    void init(){
        System.out.println("SpringBean8 init method");
        springBean7.doSomething();
    }
}
