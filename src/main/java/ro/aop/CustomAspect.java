package ro.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CustomAspect {

    @Before("execution(* ro.aop.*.*(..))")
    public void logBeforeAllMethods(JoinPoint joinPoint) {
        System.out.println("Before method " + joinPoint.getSignature().toShortString());
    }

    @Pointcut("@within(org.springframework.stereotype.Component)")
    public void componentMethods() {
    }

    @Before("componentMethods()")
    public void logComponentMethods(JoinPoint joinPoint) {
        System.out.println("Before method in @Component " + joinPoint.getSignature().toShortString());
    }

    @Pointcut("@annotation(ro.aop.MyAnnotation)")
    public void annotatedMethods() {
    }

    @After("annotatedMethods()")
    public void logAnnotatedMethods(JoinPoint joinPoint) {
        System.out.println("After method annotated with @MyAnnotation " + joinPoint.getSignature().toShortString());
    }
}
