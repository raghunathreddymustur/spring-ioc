Dependencies
----------
1. the `org.springframework:spring-context` dependency is a core module of the Spring framework that provides the fundamental functionalities for managing the application context. It includes the ApplicationContext interface, which represents the Spring IoC container, and various other classes and components that enable dependency injection, bean management, and more. Here are some key features and use cases of the spring-context module
   1. ApplicationContext
   2. Bean Definition
   3. Dependency Injection
   4. Bean Lifecycle Management
   5. Event Handling
2. the `javax.annotation` package provides annotations that are commonly used in Java applications, including those developed with the Spring framework. These annotations offer additional metadata and semantics to enhance the functionality and behavior of your code. Some notable annotations from the javax.annotation package are:
   1. Required whenever you using annotation based configuration -- other @annotations won't work
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
      1. Java Based Configuration : Code for reference 
         1. Single configuration file
            1. Main class:
               1. link: [JavaBased](src/main/java/JavaBasedConfig.java)
            2. Configuration file:
               1. link :[configuration](src/main/java/config/JavaConfiguration.java)
         2. Working with multiple Configuration file
            1. [Main class:](src/main/java/multipleConfigurationFiles/JavaBasedMultiConfiguration.java)
            2. Configuration classes
               1. [config-one](src/main/java/config/JavaConfiguration.java)
               2. [config-two](src/main/java/config/JavaConfiurationFileTwo.java)
   5. **Using the Container**
      1. The ApplicationContext lets you read bean definitions and access them
      2. Some ways to read bean
         1. Using getBean() method:
            1. by I'd (name)
               1. ```   
                  UserService userService = (UserService) context.getBean("userService");
                  ```
            2. by Type
               1. f there is only one bean of that type in the ApplicationContext, it will be returned.
               ```
                  AdminUserService userService = context.getBean(AdminUserService.class); 
                  ```
            3. byName and Type -- to avoid typecasting
               1. The getBean() method of the ApplicationContext is used to retrieve a bean by its name or type.
               ``` 
               UserService userService = context.getBean("userService", UserService.class);
               ```
            4. by getBeansOfType()
               1. The getBeansOfType() method retrieves all beans of a specific type as a Map, where the bean names are the keys and the bean instances are the values.
               ``` 
                  Map<String, UserService> userServiceMap = context.getBeansOfType(UserService.class);
               ```
            5. by getBeanNamesForType()
               1. The getBeanNamesForType() method retrieves the names of all beans of a specific type as an array of strings.
               ``` 
               String[] beanNames = context.getBeanNamesForType(UserService.class);
                ```
            
         2. Note
            1. The ApplicationContext interface has a
               few other methods for retrieving beans, **`but, ideally, your application code should never use them.`**
               Indeed, your application code should have no calls to the getBean() method at all and thus have no
               dependency on Spring APIs at all. For example, Spring’s integration with web frameworks provides
               dependency injection for various web framework components such as controllers and JSF-managed
               beans, letting you declare a dependency on a specific bean through metadata (such as an
               autowiring annotation).
      
2. Bean Overview
   1. Within the container itself, these bean definitions are represented as `BeanDefinition` objects
   2. The BeanDefinition encapsulates all this metadata about a bean, allowing the container to understand and manage the bean's behavior, dependencies, and configuration. It serves as a blueprint for creating and configuring bean instances within the container.
   3. Naming Beans
      1. Every bean has one or more identifiers. These identifiers must be unique within the container that
         hosts the bean
      2. if it requires more than one, the
         extra ones can be considered aliases
      3. Convention:
         1. . That is, bean names start with a lowercase letter and are `camel-cased` from there.
            Examples of such names include accountManager, accountService, userDao, loginController, and
            so forth.
      4. Ways to name Beans
      5. `Java Based`
         1. By default, the method name annotated with @Bean serves as the unique identifier for a bean within the container.
         2. In this example, the method userService() serves as the unique identifier for the UserService bean within the container. When referring to this bean, you would use its method name: userService.
         ```java
          @Configuration
          public class AppConfig {
            @Bean
            public UserService userService() {
                  return new UserService();
            }
          }
         ```
         3. If you need additional identifiers for a bean, you can specify them using the name attribute of the @Bean annotation. These additional identifiers are considered aliases for the bean.
         ```java
            @Configuration
            public class AppConfig {
              @Bean(name = {"userService", "userManagementService"})
              public UserService userService() {
                return new UserService();
              }
            }
         ```
      6. `Annotation Based`
         1. **Unique Identifier:**
            1. By default, the identifier for a bean in Annotation-based configuration is the name of the bean class with the first letter in lowercase.
            2. In this example, the UserService class is annotated with @Component. The default identifier for this bean within the container is "userService". You can refer to this bean using its default identifier.
            ```java
            @Component
            public class UserService {
            }
            ```
         2. **Custom Identifier (Alias):**
            1. Single Alias Name
               1. you can define them using the value attribute of the annotation or use the name attribute explicitly. These additional identifiers serve as aliases for the bean.
               2. In this example, the UserService bean has a custom identifier or alias "userManager" defined using the @Component annotation. You can refer to this bean using either the default identifier "userService" or the alias "userManager", and both will point to the same bean instance.
               ``` java
               @Component("userManager")
               public class UserService {}
               ```
      2.Dependencies
         1. This section explains how you go from defining a number of
            bean definitions that stand alone to a fully realized application where objects collaborate to achieve
            a goal.
         2. Dependency Injection
            1. Dependency Injection (DI) is a design pattern and a fundamental concept in software engineering that allows objects to be provided with their dependencies from external sources, rather than creating or managing dependencies within the objects themselves.
            2. It promotes loose coupling between classes and enhances the re-usability, maintainability, and testability of code.
               1. , your classes become easier to test, particularly
                  when the dependencies are on interfaces or abstract base classes, which allow for stub or mock
                  implementations to be used in unit tests
            3. **Dependency Injection Process:**
               1. The DI process involves the following steps:
                  1. Bean Definition: A bean definition declares the dependencies of a bean and how they should be injected.
                  2. Bean Instantiation: The Spring container instantiates beans and manages their lifecycle.
                  3. Dependency Resolution: The container identifies the dependencies required by a bean.
                  4. Dependency Injection: The container injects the dependencies into the bean using the configured injection type (constructor or setter).
                  5. Dependency Injection: The container injects the dependencies into the bean using the configured injection type (constructor or setter).
            4. **Benefits of Dependency Injection:**
               1. Loose Coupling: Objects are decoupled from their dependencies, allowing for easier maintenance, testing, and reusability.
               2. Configurability: Dependencies can be easily configured and switched, promoting flexible application behavior.
               3. Configurability: Dependencies can be easily configured and switched, promoting flexible application behavior.
               4. Modular Development: Classes can be developed independently and integrated through dependency injection.
            5. Java Based Dependency Injection:
               1. Constructor Injection
                  1. Without using Autowired - Example
                  ```java
                  @Configuration
                  public class AppConfig {
                  @Bean
                  public Foo foo() {
                     return new Foo();
                  }
                  
                  @Bean
                  public Bar bar() {
                      return new Bar(foo());
                  }
                  }
                  
                     ```
               2. Using @Autowired in configuration class - Example
                  ```java
                  @Bean
                  public UPI getDirectUpiPayment()
                  {
                     return new UPI();
                  }
                     
                  @Bean
                  public Card getDirectCardPayment(){
                  return new Card();
                  }
                     
                  @Bean
                  @Autowired
                  public  Payment multiPaymentTwo(UPI upi, Card card){
                     return new MultiplePaymentSupport(upi,card);
                  }
                  ```
            6. Annotation Based Dependency injection
               1. Using @Autowired
                  1. @Autowired is an annotation that is processed by AutowiredAnnotationBeanPostProcessor,
                     which can be put onto class constructor, field, setter method or config method. Using this annotation
                     enables automatic Spring Dependency Resolution that is primary based on types.
                  2. @Autowired has a property required which can be used to tell Spring if dependency is required or
                     optional. By default dependency is required. If @Autowired with required dependency is used on top
                     of constructor or method that contains multiple arguments, then all arguments are considered required
                     dependency unless argument is of type Optional, is marked as @Nullable, or is marked as
                     @Autowired(required = false).
                  3. If @Autowired is used on top of Collection or Map then Spring will inject all beans matching the
                     type into Collection and key-value pairs as BeanName-Bean into Map. Order of elements depends on
                     usage of @Order, @Priority annotations and implementation of Ordered interface.
                  4. @Autowired uses following steps when resolving dependency:
                     1. Match exactly by type, if only one found, finish.
                     2. If multiple beans of same type found, check if any contains @Primary annotation, if yes, inject
                        @Primary bean and finish.
                     3. If no exactly one match exists, check if @Qualifier exists for field, if yes use @Qualifier to
                        find matching bean.
                     4. If still no exactly one bean found, narrow the search by using bean name.
                     5. If still no exactly one bean found, throw exception (NoSuchBeanDefinitionException,
                        NoUniqueBeanDefinitionException, …). 
                  5. Types of Annoatation based Injection
                     1. Feild Injection
                     2. Constructor Injection
                     3. Setter Injection
                  6. Field Injection
                     1. Autowired fields can have any visibility level
                     2. Injection is happening after Bean is created but before any init method (@PostConstruct, InitializingBean, @Bean(initMethod)) is called
                     3. By default field is required, however you can use Optional, @Nullable or  @Autowired(required = false) to indicate that field is not required.

                  
                                                          
   

               
   
            
            
