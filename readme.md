Common Errors
------------
1. Spring
   1. NoSuchBeanDefinition Found
      1. Problem with dependencies and its version compatibility with others dependencies 
      2. problem with `Spring Context` dependency - changing of version of this dependency worked.
      3. Problem with IDE --- Not sure
2. Git
   1. Detached Head
      1. Solved using this video
         1. https://www.youtube.com/watch?v=SwDF9mtI8Ek


Dependencies
----------
1. the `org.springframework:spring-context` dependency is a core module of the Spring framework that provides the fundamental functionalities for managing the application context. It includes the ApplicationContext interface, which represents the Spring IoC container, and various other classes and components that enable dependency injection, bean management, and more. Here are some key features and use cases of the spring-context module
   1. ApplicationContext
   2. Bean Definition
   3. Dependency Injection
   4. Bean Lifecycle Management
   5. Event Handling
2. the `javax.annotation` package provides annotations that are commonly used in Java applications, including those developed with the Spring framework. These annotations offer additional metadata and semantics to enhance the functionality and behavior of your code. Some notable annotations from the javax.annotation package are:
   1. Required whenever you using annotation based configuration -- few @annotations won't work


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
      4. Manages Beans Lifecycle
      5. Implements bean factory
         1. allows to access the beans stored
      6. Is a Resource Loader
      7. Has ability to push events to registered even listeners
      8. Exposes Environment which allows to resolve properties
         
   2. **Implementations**
      1. The choice of implementation depends on the specific requirements and configuration approach you prefer.
      2. few commonly used ApplicationContext implementations and their usage - 
      3. Non-Web Applications:
         1. `AnnotationConfigApplicationContext:`
            1. This implementation is used to create an application context from Java-based configuration classes.
            2. It scans the specified packages for classes annotated with Spring annotations like @Configuration, @Component, etc., and creates beans based on the configuration.
         2. `ClassPathXmlApplicationContext:`
            1. This implementation is used to create an application context from XML configuration files on the classpath.
      4. Other types
         1. Web Applications:
            1. Servlet 2 – web.xml, ContextLoaderListener, DispatcherServlet
            2. Servlet 3 – XmlWebApplicationContext
            3. Servlet 3 - AnnotationConfigWebApplicationContext
         2. Spring Boot:
            1. SpringBootConsoleApplication – CommandLineRunner
            2. SpringBootWebApplication – Embedded Tomcat


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
   4. What is container
      1.Spring provides a container for beans. It manages lifecycle of the beans and also provides additional services through usage of Application Context
      2. Spring Container Lifecycle:
         1. Application is started. -- When you run the app
         2. Spring container is created. -- by creating the instance of Application context
         3. Containers reads configuration - XML, JAVA Based, Annotation based configuration
         4. Beans definitions are created from configuration.
            1. A bean definition typically includes the following information:
               1. Bean Class: The class or interface that defines the type of the bean.
               2. Dependencies: The dependencies that the bean requires. These dependencies can be other beans or simple values.
               3. Scope: The scope of the bean, which determines its lifecycle and visibility. Common scopes include singleton (a single shared instance), prototype (a new instance created each time it is requested), request (scoped to an HTTP request), session (scoped to an HTTP session), etc.
               4. Configuration Settings: Additional configuration settings for the bean, such as initialization methods, destruction methods, post-processing, lazy initialization, etc
               5. Qualifiers and Aliases: Qualifiers help in resolving ambiguities when multiple beans of the same type are present in the container. Aliases provide alternative names for a bean, allowing it to be referenced by multiple identifiers.
         5. BeanFactoryPostProcessors are processing bean definitions.
            1. BeanFactoryPostProcessors are a powerful feature of the Spring framework that allow you to customize and modify bean definitions before they are instantiated by the container. They provide a way to perform advanced configuration and manipulation of bean definitions during the initialization phase of the Spring container.
            2. Use cases are Modify Bean Definitions,Conditional Bean Creation... etc
            3. Example
               1. we have an interface DataService and two implementations: DatabaseService and WebService. We want to customize the bean definitions of these implementations to automatically set a property called source with a specific value.
               2. ````java
                  @Component
                  public class CustomPropertyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {  
                  @Override
                  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
                  String[] beanNames = beanFactory.getBeanNamesForType(DataService.class);  
                  for (String beanName : beanNames) { 
                  BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
                  beanDefinition.getPropertyValues().add("source", "custom-source");
                  }}}
    
                  ````
            4. Instances of Spring Beans are created. -- Constrctor are called
            5. Spring Beans are configured and assembled – resolve property values and inject
               dependencies.
            6. BeanPostProcessors are called.
               1. postProcessBeforeInitialization method of BeanPostProcessor is called before intilization
            7. Bean @PostConstrcut method is called 
               1. When a bean is created and all its dependencies have been injected, the @PostConstruct annotated method is automatically invoked by the container. It allows you to perform any necessary initialization or setup operations on the bean before it is ready for use.
            8. eanPostProcessors are called.
               1. postProcessAfterInitialization method of BeanPostProcessor is called after intilization
            9. Application Runs.
            10. Application gets shutdown.
            11. Spring Context is closed.
            12. Destruction callbacks are invoked.
                1. @PreDestroy method of bean is called, the PreDestroy annotation is used on a method as a callback notification to signal that the instance is in the process of being removed by the container. The method annotated with PreDestroy is typically used to release resources that it has been holding.




   5. **Instantiating a Container**
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
   6. **Using the Container**
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
         3. closing application context
            1. Based on applications
               1. Standalone Non-Web Applications
                  1. Register Shutdown hook by calling ConfigurableApplicationContext#registerShutdownHook –
                     Recommended way
                  2. Call ConfigurableApplicationContext#close
               2. Web Application
                  1. ContextLoaderListener will automatically close context when web container will stop web
                     application
               3. Spring Boot
                  1. Application Context will be automatically closed
                  2. Shutdown hook will be automatically registered
                  3. ContextLoaderListener applies to Spring Boot Web Applications as well
            



   7. Bean Lifecycle
      1. Four Stages
         1. Context is Created:
            1. Beans Definitions are created based on Spring Bean Configuration.
            2. BeanFactoryPostPro******cessors are invoked.
         2. Bean is Created:
            1. Instance of Bean is Created.
            2. Properties and Dependencies are set.
            3. BeanPostProcessor::postProcessBeforeInitialization gets called.
            4. @PostConstruct method gets called.
            5. InitializingBean::afterPropertiesSet method gets called.
            6. @Bean(initMethod) method gets called
            7. BeanPostProcessor::postProcessAfterInitialization gets called.
         3. Ready to use
         4. Bean is Destroyed:
            1. @PreDestroy method gets called.
            2. DisposableBean::destroy method gets called.
            3. @Bean(destroyMethod) method gets called.
         5. Example
            1. ```java
               // Post processors
               @Component
               public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
               @Override
               public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
                 System.out.println(getClass().getSimpleName() + " postProcessBeanFactory");
                  }
                 }
               @Component
               public class CustomBeanPostProcessor implements BeanPostProcessor {
                @Override
                public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                     System.out.println(String.format("%s::postProcessBeforeInitialization %s %s", getClass().getSimpleName(), bean.getClass().getSimpleName(), beanName));
                     return bean;
                   }

                 @Override
                public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                    System.out.println(String.format("%s::postProcessAfterInitialization %s %s", getClass().getSimpleName(), bean.getClass().getSimpleName(), beanName));
                    return bean;
                    }
                }
               
               //Sample Bean
               public class SpringBean1 implements InitializingBean, DisposableBean {

               private SpringBean2 springBean2;

               public SpringBean1() {
                   System.out.println("\nCreating " + getClass().getSimpleName());
               }

               public void sayHello() {
                   System.out.println("\nHello\n");
               }

               @Autowired
               public void setSpringBean2(SpringBean2 springBean2) {
                   System.out.println("settingProperty on " + getClass().getSimpleName() + " to inject " + springBean2.getClass().getSimpleName());
                   this.springBean2 = springBean2;
               }

               @PostConstruct
               public void postConstruct() {
                   System.out.println("@PostConstruct " + getClass().getSimpleName());
               }

               @PreDestroy
               public void preDestroy() {
                   System.out.println("@PreDestroy " + getClass().getSimpleName());
               }

               @Override
               public void afterPropertiesSet() throws Exception {
                   System.out.println("InitializingBean::afterPropertiesSet " + getClass().getSimpleName());
               }

               @Override
               public void destroy() throws Exception {
                   System.out.println("DisposableBean::destroy " + getClass().getSimpleName());
                }

               private void initMethod() {
                   System.out.println("@Bean(initMethod) " + getClass().getSimpleName());
               }

               private void destroyMethod() {
                   System.out.println("@Bean(destroyMethod) " + getClass().getSimpleName());
               }
               }
               
               // comfiguration of Init and destroy methods
               @Configuration
               @ComponentScan
               public class ApplicationConfiguration {

                  @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
                  public SpringBean1 springBean1() {
                      return new SpringBean1();
                  }
               }

               ```   




   8. Bean Overview
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
                        1. ```java
                           @Component
                           public class AutowiredOnCollection { 
                           @Autowired
                           List<Vechicle> vechicleLis;
                        
                           public void printVechicle()
                           { 
                               vechicleLis.forEach(System.out::println);
                           }
                        
                           Note : order of list is dependent upon @order and @Priority annoations declared on depedencies

                             ```
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
                           1. Intution 
                              1. By injecting dependencies into the fields before initialization methods are invoked, you can access the dependencies during initialization and perform any necessary configuration or setup operations. This allows you to leverage the injected dependencies in your initialization logic, ensuring that the bean is properly initialized and ready for use.
                           2. Example
                              1. Suppose we have a UserService bean that requires a UserRepository dependency for data access. During the initialization of the UserService, we want to perform some configuration operations on the injected UserRepository, such as setting additional properties or calling initialization methods.
                                 1. ```java
                                    @Service
                                    public class UserService { 
                                    @Autowired
                                    private UserRepository userRepository;
                              
                                    @PostConstruct
                                    public void init() { 
                                    userRepository.setAdditionalProperty("some value");
                                    userRepository.initialize();
                                     }
                                    }
                                    ```
                        3. By default field is required, `however you can use Optional, @Nullable or  @Autowired(required = false) to indicate that field is not required.`
                           1. Whenever field is declared Optional, Nullable or @Autowired(required = false) is no bean is found then it will null
                        4. Example
                           ```java
                            @Component
                            public class FourWheeler implements Vechicle{
                             @Autowired
                             private Wheel wheel;
                             @Autowired
                             private Optional<Engine> engine;
                             @Autowired
                             @Nullable
                             private Seat seat;
                             @Autowired(required = false)
                             private Window window;
                            ```      
                        5. Disadvantages
                           1. Null-Safety
                              1. Field injection creates a risk of NullPointerException if dependencies aren’t correctly initialized.
                           2. Immutability
                              1. Using the field injection, we are unable to create immutable classes.
                              2. We need to instantiate the final fields when they're declared or through the constructor. Furthermore, Spring performs autowiring once the constructors have been called. Therefore, it’s impossible to autowire the final fields using field injection
                           3. Single Responsibility Violation
                              1. When we use field injection, we may end up violating the single responsibility principle. We can easily add more dependencies than necessary and create a class that's doing more than one job.
                           4. Testing
                              1. Unit testing reveals one of the major drawbacks of the field injection approach.
                              2. Suppose we’d like to write a unit test to check whether the process() method defined in the EmailService is working properly.
                              3. Firstly, we'd like to mock the EmailValidation object. However, since we inserted the EmailValidator using field injection, we can't directly replace it with a mocked version:
                              4. **SOURCE - read**
                                 1. https://www.baeldung.com/java-spring-field-injection-cons#null-safety
                        
                           5. **Encourages Fragile Code**: Field injection can make it easier to introduce coupling between classes, as the dependencies are directly accessed through fields. This can result in code that is more tightly coupled and less resilient to changes. It can also make refactoring more challenging as changes in one class can have unintended consequences on other classes.
                              1. Example:
                              2. Suppose we have a NotificationService that is responsible for sending notifications to users using different channels, such as email and SMS. We have two implementations of the NotificationChannel interface: EmailNotificationChannel and SMSNotificationChannel.
                              3. ```java
                                 @Service
                                 public class NotificationService {
                                 @Autowired
                                 private EmailNotificationChannel emailNotificationChannel;
                                 @Autowired
                                 private SMSNotificationChannel smsNotificationChannel;
                              
                                 public void sendNotification(String message) { 
                                 emailNotificationChannel.send(message);
                                  smsNotificationChannel.send(message);}
                                 }
                              
                                 @Component
                                 public interface NotificationChannel {    
                                  void send(String message);
                                 }
                              
                                 @Component
                                 public class EmailNotificationChannel implements NotificationChannel { 
                                  public void send(String message) { 
                                 }}
                              
                                 @Component
                                 public class SMSNotificationChannel implements NotificationChannel { 
                                 public void send(String message) { 
                                 }}
                                  ```
                              
                              4. In this example, the NotificationService depends on the EmailNotificationChannel and SMSNotificationChannel through field injection. It directly accesses the specific implementations of the NotificationChannel interface.
                              5. Now, let's say we need to introduce a new notification channel, PushNotificationChannel, and replace the existing EmailNotificationChannel with a new implementation, NewEmailNotificationChannel. We modify the code as follows:
                              6. ```java
                                 @Service
                                 public class NotificationService { 
                                 @Autowired
                                 private EmailNotificationChannel newEmailNotificationChannel;
                              
                                 @Autowired
                                 private SMSNotificationChannel smsNotificationChannel;
                              
                                 @Autowired
                                 private PushNotificationChannel pushNotificationChannel;
                              
                                 public void sendNotification(String message) { 
                                 newEmailNotificationChannel.send(message);
                                 smsNotificationChannel.send(message);
                                 pushNotificationChannel.send(message);
                                 }}
                              
                                 @Component
                                 public interface NotificationChannel { 
                                 void send(String message);
                                 }
                              
                                 @Component
                                 public class NewEmailNotificationChannel implements NotificationChannel {  
                                 public void send(String message) { 
                                 }
                                 }
                              
                                 @Component
                                 public class SMSNotificationChannel implements NotificationChannel { 
                                  public void send(String message) { 
                                 }}
                              
                                 @Component
                                 public class PushNotificationChannel implements NotificationChannel { 
                                 public void send(String message) {
                                 }}
                                 ```
                              7. In this modified code, we changed the EmailNotificationChannel to NewEmailNotificationChannel and added the PushNotificationChannel. However, these changes have introduced tight coupling between the NotificationService and the specific implementations of the notification channels.
                              8. As a result, replacing the EmailNotificationChannel with the NewEmailNotificationChannel requires modifying the NotificationService class, potentially impacting its behavior or functionality. Additionally, introducing the PushNotificationChannel has also affected the NotificationService, requiring it to handle the new channel.
                              9. This tight coupling can make the codebase fragile and difficult to maintain. Any changes to the interface or contract of the NotificationChannel can have unintended consequences on the NotificationService and other dependent classes.
                              10. To address these issues, it is recommended to use dependency injection approaches that promote loose coupling, such as constructor injection or setter injection. By explicitly declaring dependencies through constructors or setters, we can reduce the coupling between classes and make the code more resilient to changes.

                     7. Setter Injection
                        1. Constructor can have any access modifier (public, protected, private, package-private).
                        2. If there is only one constructor in class, there is no need to use @Autowired on top of it, Spring will use
                           this default constructor anyway and will inject dependencies into it
                           1. Example
                              1. ```java
                                 @Component
                                 public class ConsturctorFourWheeler { 
                                 private Engine engine;
                                 private Wheel wheel;
                                 private Seat seat;
                                 private Window window;
                                 public ConsturctorFourWheeler(Engine engine, Wheel wheel, Seat seat, Window window) { 
                                 this.engine = engine;
                                 this.wheel = wheel;
                                 this.seat = seat;
                                 this.window = window;
                                 }
                                 }
                                 ```
                        3. If class defines multiple constructor, then you are obligated to use @Autowired to tell Spring which
                           constructor should be used to create Spring Bean. If you will have a class with multiple constructor
                           without any of constructor marked as @Autowired then Spring will throw NoSuchMethodException.
                        4. By default all arguments in constructor are required, however you can use Optional, @Nullable or
                           @Autowired(required = false) to indicate that parameter is not required.
                        5. example
                           1. ```java
                                 @Component
                                 public class ConsturctorFourWheeler { 
                                 private Engine engine;
                                 private Optional<Wheel> wheel;
                                 private Seat seat;
                                 private Window window;
                                 public ConsturctorFourWheeler(Engine engine, Seat seat, Window window) { 
                                 this.engine = engine;
                                 this.wheel = wheel;
                                 this.seat = seat;
                                 this.window = window;
                                 }
                              @Autowired
                              public ConsturctorFourWheeler(Engine engine, Optional<Wheel> wheel, @Nullable Seat seat, @Autowired(required = false)  Window window) { 
                              this.engine = engine;
                              this.seat = seat;
                              this.window = window;
                                
                                 }
                              }
                                 ```
                           
                     8. Setter Injection
                        1. @Autowired can be used with method injection like this
                        2. @Autowired method can have any visibility level and also can contain multiple parameters. If method
                           contains multiple parameters, then by default it is assumed that in @Autowired method all parameters
                           are required. If Spring will be unable to resolve all dependencies for this method,
                           NoSuchBeanDefinitionException or NoUniqueBeanDefinitionException will be thrown.
                        3. When using @Autowired(required = false) with method, it will be invoked only if Spring can
                           resolve all parameters.
                        4. If you want Spring to invoke method only with arguments partially resolved, you need to use
                           @Autowired method with parameter marked as Optional, @Nullable or @Autowired(required
                           = false) to indicate that this parameter is not required.
                        5. Example
                           ```java
                           public class SetterFourWheeler { 
                           private Engine engine;
                           private Wheel wheel;
                           private Seat seat;
                           private Window window;
                        
                           @Autowired
                           public void setEngine(Engine engine) { 
                               this.engine = engine;
                           }
                        
                            @Autowired
                           public void setWheel(Wheel wheel) { 
                           this.seat = seat;
                           }
                           @Autowired(required = false)
                           public void setSeatAndWindow(Seat seat,Window window) { 
                           this.seat = seat;
                           this.window = window;
                           }
                           //Nullable 
                           @Autowired(required = false)
                           public void setSeatAndWindow(Seat seat,Optional<Window> window, @Nullable Engine engine, @Autowired(required =false ) Wheel wheel) { 
                           this.seat = seat;
                           this.window = window;
                           }
                        
                           }

                           ```

                     9. Constructor-based or setter-based DI
                        1. Since you can mix constructor-based and setter-based DI, it is a good rule of thumb to use
                           constructors for mandatory dependencies and setter methods or configuration methods for
                           optional dependencies. Note that use of the @Autowired annotation on a setter method can
                           be used to make the property be a required dependency; however, constructor injection with
                           programmatic validation of arguments is preferable.
                        2. he Spring team generally advocates constructor injection, as it lets you implement
                           application components as immutable objects and ensures that required dependencies are
                           not null. Furthermore, constructor-injected components are always returned to the client
                           (calling) code in a fully initialized state. As a side note, a large number of constructor
                           arguments is a bad code smell, implying that the class likely has too many responsibilities and
                           should be refactored to better address proper separation of concerns
                        3. Setter injection should primarily only be used for optional dependencies that can be assigned
                           reasonable default values within the class. Otherwise, not-null checks must be performed
                           everywhere the code uses the dependency. One benefit of setter injection is that setter
                           methods make objects of that class amenable to reconfiguration or re-injection later.
                           Management through JMX MBeans is therefore a compelling use case for setter injection.
                  



                        

                  
                                                          
   

               
   
            
            
