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

AOP 2. Create an aspect that logs the method calls of all methods in ro.aop package

AOP 3. Create an aspect that logs the method calls of all methods that end with the word "Method".

AOP 4. Create an aspect that logs the method calls of all methods inside a certain class.

AOP 5. Create an aspect that logs the method calls of all methods that have a custom annotation.

MVC1. Create a simple HTML document with the following structure:

A title "My First HTML Page"
A heading (h1) with the text "Welcome to My Page"
A paragraph with the text "This is my first HTML page. I'm learning how to create web pages using HTML."

MVC2. Create an HTML document that includes:

A heading (h2) with the text "My Favorite Fruits"
An unordered list of your three favorite fruits

MVC3. A heading (h2) with the text "Useful Links"
Three hyperlinks to your favorite websites. Use descriptive text for the links.

MVC4. A heading (h2) with the text "My Favorite Animal"
An image of your favorite animal. Use the alt attribute to provide alternative text for the image.

MVC5. A heading (h2) with the text "My Daily Routine"
An ordered list with at least three items representing your daily routine.
Within one of the list items, include a nested unordered list with sub-tasks or activities.
