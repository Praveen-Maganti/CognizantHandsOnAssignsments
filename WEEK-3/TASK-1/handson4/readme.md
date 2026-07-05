
<bean> -> This means "Spring, please create an object of this class." instead of manual creation.
id -> It is the bean name later we retrieve it using context.getBean("id").
class -> The fully qualified class name.
property -> This performs the setter injection
Attributes in property tag:
    name → Name of the property (mapped to the setter method).
    value → Value assigned to that property.

ApplicationContext -> ApplicationContext is a Spring IoC container interface that manages 
    the lifecycle of beans. It reads the configuration (XML, annotations, or Java configuration), 
    creates the beans, wires their dependencies, and provides them to the application.
ClassPathXmlApplicationContext -> ClassPathXmlApplicationContext is a concrete implementation of ApplicationContext that loads the Spring configuration 
                        XML file from the application's classpath (typically src/main/resources).


getBean()-> context.getBean() asks the Spring IoC container for a bean. Spring looks up the bean by its ID or type and returns it. For singleton beans, it returns the existing instance; 
                        for prototype beans, it creates and returns a new instance each time.