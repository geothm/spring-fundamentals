package ro.aop;

import org.springframework.stereotype.Component;

@Component
public class AopBean2 {

    public void newMethod() {
        System.out.println("AopBean2.newMethod()");
    }

    public void someWork() {
        System.out.println("AopBean2.someWork()");
    }
}
