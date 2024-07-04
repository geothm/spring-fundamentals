package ro.wantsome;

import org.springframework.stereotype.Component;

@Component
public class ImplementationBean2 implements BeanInterface{
    @Override
    public void interfaceMethod() {
        System.out.println("ImplementationBean2 interfaceMethod() different implementation.");
    }
}
