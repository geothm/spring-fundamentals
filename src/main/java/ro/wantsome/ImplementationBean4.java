package ro.wantsome;

import org.springframework.stereotype.Component;

@Component
public class ImplementationBean4 implements BeanInterface {
    @Override
    public void interfaceMethod() {
        System.out.println("ImplementationBean4 interfaceMethod() brand new implementation.");
    }
}
