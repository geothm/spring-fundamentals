package ro.aop;

import org.springframework.stereotype.Component;

@Component
public class AopBean1 {

    public void someMethod() {
        System.out.println("AopBean1.someMethod()");
    }

    @MyAnnotation
    public void doSomething() {
        System.out.println("AopBean1.doSomething()");
    }
}
