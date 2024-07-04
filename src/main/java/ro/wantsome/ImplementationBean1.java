package ro.wantsome;

import org.springframework.stereotype.Component;

@Component
public class ImplementationBean1 implements BeanInterface{

    @Override
    public void interfaceMethod() {
        System.out.println("ImplementationBean1 interfaceMethod() implementation.");
    }
}
