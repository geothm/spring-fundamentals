package ro.aop;

import org.springframework.stereotype.Component;

@Component
public class BeanBehaviourImpl implements BehaviourInterface {
    private MyBehaviourImpl myBehaviour;

    public BeanBehaviourImpl() {
        myBehaviour = new MyBehaviourImpl();
    }

    @Override
    public void doStuff() {
        proxyMethod1();

        myBehaviour.doStuff();

        proxyMethod2();
    }

    private void proxyMethod1() {
        System.out.println("Stuff to be done before calling the actual method");
    }

    private void proxyMethod2() {
        System.out.println("Stuff to be done after calling the actual method");
    }
}
