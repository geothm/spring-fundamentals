package ro.aop;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AOPClient {

    private final BehaviourInterface behaviour;

    public AOPClient(BehaviourInterface behaviour) {
        this.behaviour = behaviour;
    }

    @PostConstruct
    void init() {
        System.out.println("AOPClient init");
        behaviour.doStuff();
    }
}
