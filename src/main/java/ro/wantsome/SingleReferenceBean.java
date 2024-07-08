package ro.wantsome;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SingleReferenceBean {

    private final BeanInterface beanInterface;

    private final BeanInterface beanInterface2;

    private final ImplementationBean4 implementationBean4;

    public SingleReferenceBean(BeanInterface beanInterface,
                               @Qualifier(value = "implementationBean3") BeanInterface beanInterface2,
                               ImplementationBean4 implementationBean4) {
        this.beanInterface = beanInterface;
        this.beanInterface2 = beanInterface2;
        this.implementationBean4 = implementationBean4;
    }

    @PostConstruct
    void init(){
        System.out.println("SingleReferenceBean init method");
        beanInterface.interfaceMethod();
        beanInterface2.interfaceMethod();
        implementationBean4.interfaceMethod();
    }
}
