package ro.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodNameAspect {

    // Pointcut to match any method in any class in any package that ends with "method"
    @Pointcut("execution(* *..*.*Method(..))")
    public void methodNamePointcut() {
        // Pointcut definition
    }

    // Advice that runs before the matched method execution
    @After("methodNamePointcut()")
    public void beforeMethodNameAdvice() {
        System.out.println("A method ending with 'Method' was executed.");
    }
}
