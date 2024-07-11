1. Add spring dependencies
Add application context to main method
Implement AppConfig class
Define SpringBean1 with @Component annotation
Define SpringBean2 with @Bean annotation
Define SpringBean3 with @Repository annotation
Define SpringBean4 with @Service annotation

2. Create SpringBean7 and SpringBean8 where SpringBean7 will pe injected into SpringBean8 via Constructor Injection
Create SpringBean9 where SpringBean7 will be injected via Setter Injection

3. Create SpringBean10 and print a message inside the constructor, with scope prototype and inject into SpringBean8 And SpringBean9

4. Remove annotation from SpringBean7 and inject dependency with required=true

5. Create BeanInterface interface with a method interfaceMethod() which will be implemented 
by ImplementationBean1, ImplementationBean2, ImplementationBean3. - implement interfaceMethod() to print a different message
Create DelegatorBean which will inject a List of BeanInterface and call interfaceMethod() on each of them.

---

AOP 1. Create a java class with a method. Add an interface for that method. Add a spring bean that implements the interface.
The bean should delegate to the plain java class method.
Add a client spring bean that uses the interface.