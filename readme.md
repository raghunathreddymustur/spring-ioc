Container - Application Context
---------
1. `ApplicationContext` 
   1. **Functions :** interface represents the Spring IoC container and is responsible for **instantiating, configuring, and assembling** the beans.
      1. `Instantiating:` The ApplicationContext is responsible 
         for creating instances of beans defined in the application context configuration. 
         When the application starts, the ApplicationContext scans the configuration files or annotations 
         to identify the beans that need to be created. It then instantiates these beans by creating 
         their corresponding Java objects.
      2. `Configuring:` After instantiating the beans, the ApplicationContext applies the configurations specified in  the application context configuration. These configurations can include setting property values, injecting   dependencies, and applying any other required settings. The ApplicationContext ensures that the beans are     properly configured according to the defined rules and dependencies.
      3. `Assembling:` The ApplicationContext identifies the dependencies between beans and resolves them by injecting the required dependencies. This wiring is typically done through dependency injection
   2. **Implementations**
      1. The choice of implementation depends on the specific requirements and configuration approach you prefer.
      2. few commonly used ApplicationContext implementations and their usage
         1. `AnnotationConfigApplicationContext:`
            1. This implementation is used to create an application context from Java-based configuration classes.
            2. It scans the specified packages for classes annotated with Spring annotations like @Configuration, @Component, etc., and creates beans based on the configuration.
         2. `ClassPathXmlApplicationContext:`
            1. This implementation is used to create an application context from XML configuration files on the classpath.
   3. **Configuration :** The container gets its instructions on what objects to instantiate, configure, and assemble by reading configuration metadata.
      1. Commonly used configuration methods
         1. **Java Configuration**
            1. Java-based configuration allows you to define and configure beans using Java classes and annotations.
            2. Beans and their dependencies are defined using @Configuration and @Bean annotations.
         2. **Annotation-based Configuration:**
            1. Annotation-based configuration uses annotations like @Component, @Autowired, etc., to define and configure beans.Beans are automatically scanned and registered based on the specified annotations.
         3. **XML Configuration:**
            1. Beans, their dependencies, and configuration details are specified in XML files.
         4. `JavaConfig + AnnotationConfig:` you can use mix of these configurations based on project requirements
   4. **Instantiating a Container**
      1. Java Based Configuration : See Code for example
      2. Annotation Based Configuration : See Code for example
    
         
   
   