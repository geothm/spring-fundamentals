package ro.wantsome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SpringBean9 {

    private SpringBean7 springBean7;

    public SpringBean9() {
        System.out.println("SpringBean9 constructor");
    }

    @Autowired
    public void setSpringBean7(SpringBean7 springBean7) {
        this.springBean7 = springBean7;
    }

    @PostConstruct
    void init(){
        System.out.println("SpringBean9 init method");
        springBean7.doSomething();
    }
}
