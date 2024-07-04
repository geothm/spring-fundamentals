package ro.wantsome;

import org.springframework.stereotype.Component;

/*
@Component
*/
public class SpringBean7 {

    private double index;

    public SpringBean7() {
        index = Math.random();
        System.out.println("SpringBean7 constructor " + index);
    }

    public void doSomething() {
        System.out.println("SpringBean7.doSomething()");
    }

    public double getIndex() {
        return index;
    }
}
