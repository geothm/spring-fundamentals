package ro.wantsome;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SpringBean8 {

    private final SpringBean7 springBean7;
    private final SpringBean10 springBean10;

    public SpringBean8(SpringBean7 springBean7, SpringBean10 springBean10) {
        this.springBean7 = springBean7;
        this.springBean10 = springBean10;
        System.out.println("SpringBean8 constructor");
    }

    @PostConstruct
    void init(){
        System.out.println("SpringBean8 init method");
        springBean7.doSomething();
        System.out.println("SpringBean7 index: " + springBean7.getIndex());
        System.out.println("SpringBean10 index: " + springBean10.getIndex());
    }
}
