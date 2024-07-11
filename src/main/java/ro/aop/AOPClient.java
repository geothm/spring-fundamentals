package ro.aop;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AOPClient {

    private final BehaviourInterface behaviour;

    private final AopBean1 aopBean1;
    private final AopBean2 aopBean2;

    public AOPClient(BehaviourInterface behaviour, AopBean1 aopBean1, AopBean2 aopBean2) {
        this.behaviour = behaviour;
        this.aopBean1 = aopBean1;
        this.aopBean2 = aopBean2;
    }

    @PostConstruct
    void init() {
        System.out.println("AOPClient init");
        behaviour.doStuff();

        aopBean1.doSomething();
        aopBean1.someMethod();

        aopBean2.newMethod();
        aopBean2.someWork();
    }
}
