package ro.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CustomAspect {

    @Before("execution(* ro.aop.*.*(..))")
    public void logBeforeAllMethods() {
        System.out.println("Before all methods");
    }

}
