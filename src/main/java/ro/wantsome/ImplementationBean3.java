package ro.wantsome;

import org.springframework.stereotype.Component;

@Component
public class ImplementationBean3 implements BeanInterface{

    @Override
    public void interfaceMethod() {
        System.out.println("ImplementationBean3 interfaceMethod() implementation of the third kind.");
    }
}
