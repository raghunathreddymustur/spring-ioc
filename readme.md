Common Errors
------------
1. Spring
   1. NoSuchBeanDefinition Found
      1. Problem with dependencies and its version compatibility with others dependencies 
      2. problem with `Spring Context` dependency - changing of version of this dependency worked.
      3. Problem with IDE Cache--- Not sure
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
         5. Comparision
            1. Advantages of Java Config over XML Config:
               1. Compile-Time Feedback due to Type-checking
               2. Refactoring Tools for Java without special support/plugins work out of the box with Java
                  Config (special support needed for XML Config).
            2. Advantages of Java Config over Annotation Based Config:
               1. Separation of concerns – beans configuration is separated from beans implementation
               2. Technology agnostic – beans may not depend on concrete IoC/DI implementation – makes it
                  easier to switch technology
               3. Ability to integrate Spring with external libraries
               4. More centralized location of bean list
            3. Limitations of Java Config:
               1. Configuration class cannot be final
               2. Configuration class methods cannot be final
               3. All Beans have to be listed, for big applications, it might be a challenge compared to
                   Component Scanning
               
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
               3. Scope: The scope of the bean, which determines its lifecycle and visibility. Common scopes include singleton (a single shared instance), prototype (a new instance created each time                   it is requested), request (scoped to an HTTP request), session (scoped to an HTTP session), etc.
               4. Configuration Settings: Additional configuration settings for the bean, such as initialization methods, destruction methods, post-processing, lazy initialization, etc
               5. Qualifiers and Aliases: Qualifiers help in resolving ambiguities when multiple beans of the same type are present in the container. Aliases provide alternative names for a bean,                      allowing it to be referenced by multiple identifiers.
         5. BeanFactoryPostProcessors are processing bean definitions.
            1. BeanFactoryPostProcessors are a powerful feature of the Spring framework that allow you to customize and modify bean definitions before they are instantiated by the container. They                      provide a way to perform advanced configuration and manipulation of bean definitions during the initialization phase of the Spring container.
            2. Use cases are Modify Bean Definitions,Conditional Bean Creation... etc
            3. Example
               1. we have an interface DataService and two implementations: DatabaseService and WebService. We want to customize the bean definitions of these implementations to automatically set a                      property called source with a specific value.
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
               1. When a bean is created and all its dependencies have been injected, the @PostConstruct annotated method is automatically invoked by the container. It allows you to perform any                         necessary initialization or setup operations on the bean before it is ready for use.
            8. eanPostProcessors are called.
               1. postProcessAfterInitialization method of BeanPostProcessor is called after intilization
            9. Application Runs.
            10. Application gets shutdown.
            11. Spring Context is closed.
            12. Destruction callbacks are invoked.
                1. @PreDestroy method of bean is called, the PreDestroy annotation is used on a method as a callback notification to signal that the instance is in the process of being removed by                      the container. The method annotated with PreDestroy is typically used to release resources that it has been holding.
                
   5. BeanFactoryPostProcessor
      1. BeanFactoryPostProcessor is an interface that contains single method postProcessBeanFactory,
         implementing it allows you to create logic that will modify Spring Bean Metadata before any Bean is
         created. BeanFactoryPostProcessor does not create any beans, however it can access and alter Metadata
         that is used later to create Beans.
      2. BeanFactoryPostProcessor is invoked after Spring will read or discover Bean Definitions, but before any
         Spring Bean is created.
      3. Because BeanFactoryPostProcessor is also a Spring Bean, but a special kind of Bean that should be
         invoked before other types of beans get created, Spring needs to have ability to create it before any
         other beans. This is why BeanFactoryPostProcessors needs to be registered from static method level.
      4. PropertySourcesPlaceholderConfigurer is a BeanFactoryPostProcessor that is used to resolve properties
         placeholder in Spring Beans on fields annotated with @Value("${property_name}").
      5. Example
         1. ```java
            // creating custom implemenatation
            public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
                @Override
                public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
                    stream(beanFactory.getBeanDefinitionNames())
                            .map(beanFactory::getBeanDefinition)
                            .filter(beanDefinition -> beanClassNameContains(beanDefinition, "module01.question13.beans"))
                            .map(BeanDefinition::getBeanClassName)
                            .forEach(System.out::println);
                 }}
            
            // Registering the custom bean
            @ComponentScan
            @PropertySource("classpath:/app-defaults.properties")
            public class ApplicationConfiguration {
            @Bean
            public static CustomBeanFactoryPostProcessor customerBeanFactoryPostProcessor() {
            return new CustomBeanFactoryPostProcessor();
             }
            }
            
            //Reading properties
            @Component
            public class PropertyReadingBean {

                @Value("${app.env}")
                private String appEnv;
                @Value("${app.envid}")
                private String appEnvId;

                public void printProperties() {
                    System.out.println("app.env = " + appEnv);
                    System.out.println("app.envid = " + appEnvId);
                }
            }

            ```
   6. BeanPostProcessor
      1. BeanPostProcessor is an interface that allows you to create extensions to Spring Framework that will modify
         Spring Beans objects during initialization. This interface contains two methods:
         1. postProcessBeforeInitialization
         2. postProcessAfterInitialization
      2. Implementing those methods allows you to modify created and assembled bean objects or even switch object
         that will represent the bean.
      3. Main difference compared to BeanFactoryPostProcessor is that BeanFactoryPostProcessor works with Bean
         Definitions while BeanPostProcessor works with Bean Objects.
      4. BeanFactoryPostProcessor and BeanPostProcessor in Spring Container Lifecycle
         1. Beans Definitions are created based on Spring Bean Configuration.
         2. BeanFactoryPostProcessors are invoked.
         3. Instance of Bean is Created.
         4. Properties and Dependencies are set.
         5. BeanPostProcessor::postProcessBeforeInitialization gets called.
         6. @PostConstruct method gets called.
         7. InitializingBean::afterPropertiesSet method gets called.
         8. @Bean(initMethod) method gets called
         9. BeanPostProcessor::postProcessAfterInitialization gets called.
      5. Recommended way to define BeanPostProcessor
         1. through static @Bean method in Application
            Configuration. This is because BeanPostProcessor should be created early, before other Beans Objects
            are ready.
         2. It is also possible to create BeanPostProcessor through regular registration in Application Configuration
            or through Component Scanning and @Component annotation, however because in that case bean can be
            created late in processes, recommended way is options provided above.
      6. Initialization methods
         1. method that you can write for Spring Bean if you need
            to perform some initialization code that depends on properties and/or
            dependencies injected into Spring Bean
         2. You can declare Initialization method in three ways:
            1. Create method in Spring Bean annotated with @PostConstruct
            2. Implement InitializingBean::afterPropertiesSet
            3. Create Bean in Configuration class with @Bean method and use
               @Bean(initMethod)
         3. You can declare Destroy method in three ways
            1. Destroy method is a method in Spring Bean that you can use to implement any
               cleanup logic for resources used by the Bean. Method will be called when Spring
               Bean will be taken out of use, this is usually happening when Spring Context is
               closed.
               1. You can declare destroy method in following ways:
                  1. Create method annotated with @PreDestroy annotation
                  2. Implement DisposableBean::destroy
                  3. Create Bean in Configuration class with @Bean method and use
                     @Bean(destroyMethod)
      7. Process
         1. Context is Created:
            1. . Beans Definitions are created based on Spring Bean Configuration.
            2. BeanFactoryPostProcessors are invoked.
         2. Bean is Created:
            1. Instance of Bean is Created
            2. Properties and Dependencies are set
            3. BeanPostProcessor::postProcessBeforeInitialization gets called
            4. @PostConstruct method gets called.
            5. InitializingBean::afterPropertiesSet method gets called.
            6. @Bean(initMethod) method gets called
            7. BeanPostProcessor::postProcessAfterInitialization gets called.
         3. Bean is Ready to use.
         4. Bean is Destroyed (usually when context is closed):
            1. @PreDestroy method gets called.
            2. DisposableBean::destroy method gets called.
            3. @Bean(destroyMethod) method gets called.
            
   7. **Instantiating a Container**
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
   8. **Using the Container**
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
            
   8. Bean Scopes
      1. Singleton
         1. Single Bean per Spring Container - `Default`
            1. ```java
               @Component
               public class SpringBean1 {
               }
               ```
         2. Use case
            1. DatabaseConnectionManager: A singleton bean that manages database connections. It ensures that there is only one instance of the connection manager throughout the application, allowing multiple components to share the same database connections.
         3. Changed by restarting the container
      2. Prototype
         1. New Instance each time Bean is Requested
            1. ```java
               @Component
               @Scope("prototype")
               public class SpringBean2 {
               }

               ```
         2. Use case
            1. ReportGenerator: A prototype-scoped bean that generates reports. Each report request results in a new instance of the report generator, ensuring that different reports are generated independently without interfering with each other.
      3. Request
         1. New Instance per each HTTP Request
            1. ```java
               @Component
               @RequestScope
               public class SpringBean3 {
               }

               ```
         2. Use Case
            1. UserAuthentication: A request-scoped bean that represents the authenticated user. It holds user-specific information and is available throughout the duration of a request, ensuring that the user's authentication context is isolated within their request.
      4. Session
         1. New Instance per each HTTP Session
            1. ```java
               @Component
               @SessionScope
               public class SpringBean4 {
               }

               ```
         2. Use Case
            1. ShoppingSession: A session-scoped bean that represents a user's shopping session. It keeps track of the items added to the cart, the progress of the checkout process, and other session-specific data.
      5. Application
         1. One Instance per each ServletContext
            1. ```java
               @Component
               @ApplicationScope
               public class SpringBean5 {
               }
               ```
         2. Changed if you re-deloyment of application
      6. Websocket
         1. One Instance per each WebSocket
   9. Instantiation behaviour of beans
      1. Lazy and Eager Instance Creation vs Scope Type:
         1. Singleton Beans are eagerly instantiated by default
         2. Prototype Beans are lazily instantiated by default (instance is created when bean is requested)
            1. however, if Singleton Bean has dependency on Prototype Bean, then Prototype Bean Instance will be created eagerly to
               satisfy dependencies for Singleton Bean
      2. Altering Behavior:
         1. You can change default behavior for all beans by @ComponentScan annotation -`@ComponentScan(lazyInit = true)`
            1. Setting lazyInit to true, will make all beans lazy, even Singleton Beans
            2. Setting lazyInit to false (default), will create Singleton Beans Eagerly and Prototype Beans Lazily
      3. You can also change default behavior by using @Lazy annotation:
         1. @Lazy annotation takes one parameter - Whether lazy initialization should occur
         2. By default @Lazy is used to mark bean as lazily instantiated
         3. You can use @Lazy(false) to force Eager Instantiation – use case for @ComponentScan(lazyInit = true) when some beans
            always needs to be instantiated eagerly
      4. @Lazy can be applied to:
         1. @Component – makes bean Lazy or as specified by @Lazy parameter
         2. Classes annotated with @Configuration annotation – make all beans provided by configuration lazy or as specified by
            @Lazy parameter
         3. Method annotated with @Bean annotation – makes bean created by method Lazy or as specified by @Lazy parameter

   10. Bean Overview
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


Component Scanning - @ComponentScan
------------------
1. Process in which Spring is scanning Classpath in search for classes annotated with stereotypes
   annotations (@Component, @Repository, @Service, @Controller, …) and based on those creates beans
   definitions.
2. Example:
   1. ```java
        //simple - scanning within Configuration package and all subpackages
        @ComponentScan
        public class ApplicationConfiguration {
        }
         
        // Advanced
        @ComponentScan(
        basePackages = "com.spring.professional.exam.tutorial.module01.question10.annotations.beans",  Based on package
        //basePackageClasses = SpringBean1.class, // based on classs
        includeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*Bean.*"),
        excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*Bean1.*")
        )
        public class ApplicationConfigurationAdvanced {
        }        

         ```
3. Stereotypes
   1. Stereotypes are annotations applied to classes to describe role which will be performed by this class.
      Spring discovered classes annotated by stereotypes and creates bean definitions based on those types
   2. Types of stereotypes
      1. Component – generic component in the system, root stereotype, candidate for autoscanning
      2. Service – class will contain business logic
      3. Repository – class is a data repository (used for data access objects, persistence)
      4. Controller – class is a controller, usually a web controller (used with @RequestMapping)
4. Meta-Annotations
   1. Meta-annotations are annotations that can be used to create new annotations.
   2. @RestController annotation is using @Controller and @ResponseBody to define its behavior
5. Property Source
   1. PropertySource is Spring Abstraction on Environment Key-Value pairs, which can come from:
      1.  JVM Properties
          System Environmental Variables
          JNDI Properties
          Servlet Parameters
          Properties File Located on Filesystem
          Properties File Located on Classpath
      2. You read properties with usage of @PropertySource or @PropertySources annotation:
         1. ```java
            @ComponentScan
            @PropertySources({
            @PropertySource("file:${app-home}/app-db.properties"),
            @PropertySource("classpath:/app-defaults.properties")
            })
            public class ApplicationConfiguration {
            }

             ```
      3. You access properties with usage of @Value annotation:
         1. @Value("${db.host}")
            private String dbHost;
6. Testing Private Fields 
   1. Injection of dependency into private field can be done with @Autowired annotation:
   2. Injection of property into private field can be done with @Value annotation:
      1. ```java
         @Component
         public class ReportService {

             @Autowired
             private ReportWriter reportWriter;
             @Value("${report.global.name}")
             private String reportGlobalName;

             public void execute() {
                 Report report = new Report();

                 // ...

                 reportWriter.write(report, reportGlobalName);
             }
         }

          ```
   3. Private Field cannot be accessed from outside of the class, to resolve this when writing Unit Test you can
      use following solutions:
      1. Use SpringRunner with `ContextConfiguration` and `@MockBean`
         1. ```java
            @RunWith(SpringRunner.class)
            @ContextConfiguration(classes = ApplicationConfig.class)
            public class ReportServiceTest01 {
            @Autowired
            private ReportService reportService;
            @MockBean
            private ReportWriter reportWriter;

                @Test
                public void shouldPassReportToWriter() {
                    reportService.execute();

                    verify(reportWriter).write(any(Report.class), any());
                }
            }
            
            ```
         2. Use ReflectionTestUtils to modify private fields
            1. ```java
               public class ReportServiceTest03 {
                  private ReportService reportService;

                  @Before
                  public void setUp() {
                  reportService = new ReportService();
                  }

                  @Test
                  public void shouldPassReportToWriter() {
                  ReportWriter reportWriter = Mockito.mock(ReportWriter.class);
                  ReflectionTestUtils.setField(reportService, ReportService.class, "reportWriter", reportWriter, ReportWriter.class);

                       reportService.execute();

                       verify(reportWriter).write(any(Report.class), any());
                  }
                  }
            
               ```
         3. Use MockitoJUnitRunner to inject mocks
            1. ```java
               @RunWith(MockitoJUnitRunner.class)
               public class ReportServiceTest02 {
               @InjectMocks
               private ReportService reportService;
               @Mock
               private ReportWriter reportWriter;

                   @Test
                   public void shouldPassReportToWriter() {
                       reportService.execute();

                       verify(reportWriter).write(any(Report.class), any());
                   }
               }

               ```
         4. Use @TestPropertySource to inject test properties into private fields
            1. ```java
               @RunWith(SpringRunner.class)
               @ContextConfiguration(classes = ApplicationConfig.class)
               @TestPropertySource(properties = "report.global.name=" + REPORT_NAME)
               public class ReportServiceTest04 {
               static final String REPORT_NAME = "Mock_Report";

                   @Autowired
                   private ReportService reportService;
                   @MockBean
                   private ReportWriter reportWriter;

                   @Test
                   public void shouldPassReportToWriter() {
                       reportService.execute();

                       verify(reportWriter).write(any(Report.class), eq               (REPORT_NAME));
                   }
               }
               ```
               
@Qualifier
----------
1. annotation gives you additional control on which bean will be injected, when
   multiple beans of the same type are found. By adding additional information on which bean you
   want to inject, @Qualifier resolves issues with NoUniqueBeanDefinitionException.
2. You can use @Qualifier in three ways:
   1. At injection point with bean name as value
   2. At injection and bean definition point
   3. Custom Qualifier Annotation Definition

@Bean
-----
1. @Bean annotation is used in @Configuration class to inform Spring that instance of class
   returned by method annotated with @Bean will return bean that will be managed by Spring.
2. @Bean also allows you to:
   Specify init method – will be called after instance is created and assembled
    Specify destroy method – will be called when bean is discarded (usually when context is
   getting closed)
    Specify name for the bean – by default bean has name autogenerated based on method
   name, however this can be overridden
    Specify alias/aliases for the bean
    Specify if Bean should be used as candidate for injection into other beans – default true
    Configure Autowiring mode – by name or type (Deprecated since Spring 5.1)

Why  @Configuration class and @Bean methods final
--------------------------------------------------------
1. Class annotated with @Configuration cannot be final because Spring will use CGLIB to create a
   proxy for @Configuration class. CGLIB creates subclass for each class that is supposed to be proxied,
   however since final class cannot have subclass CGLIB will fail. This is also a reason why methods cannot
   be final, Spring needs to override methods from parent class for proxy to work correctly, however final
   method cannot be overridden, having such a method will make CGLIB fail.
2. If @Configuration class will be final or will have final method, Spring will throw
   BeanDefinitionParsingException.
3. Spring supports Singleton beans in @Configuration class by creating CGLIB proxy that intercepts
   calls to the method. Before method is executed from the proxied class, proxy intercept a call and
   checks if instance of the bean already exists, if instance of the bean exists, then call to method is not
   allowed and already existing instance is returned, if instance does not exists, then call is allowed, bean
   is created and instance is returned and saved for future reuse. To make method call interception CGLIB
   proxy needs to create subclass and also needs to override methods.

@profile
---------
1. Spring Profiles are configured by:
   1. Specifying which beans are part of which profile
   2. Specifying which profiles are active
2. You can specify beans being part of profile in following ways:
   1. Use @Profile annotation at @Component class level – bean will be part of profile/profiles
   specified in annotation 
      1. ```java
         @Component
         @Profile("database")
         class DatabaseStoreFinancialDataDao implements FinancialDataDao { 
         }
            ```
   2. Use @Profile annotation at @Configuration class level – all beans from this configuration will
   be part of profile/profiles specified in annotation
      1. ```java
         @Configuration
         @Profile("file")
         public class FileApplicationConfiguration {
         @Bean
         public FinancialDataDao fileStoreFinancialDataDao() {
         return new FileStoreFinancialDataDao();
         }

             @Bean
             public FinancialReportWriter fileStoreFinancialReportWriter() {
                 return new FileStoreFinancialReportWriter();
             }
         }

            ```
   3. Use @Profile annotation at @Bean method of @Configuration class – instance of bean
   returned by this method will be part of profile/profiles specified in annotation
      1. ```java
         @Configuration
         public class ApplicationConfiguration {
             @Bean
             @Profile("database")
             public FinancialDataDao databaseStoreFinancialDataDao() {
                 return new DatabaseStoreFinancialDataDao();
             }

             @Bean
             @Profile("database")
             public FinancialReportWriter databaseStoreFinancialReportWriter() {
                 return new DatabaseStoreFinancialReportWriter();
             }

             @Bean
             @Profile("file")
             public FinancialDataDao fileStoreFinancialDataDao() {
                 return new FileStoreFinancialDataDao();
             }

             @Bean
             @Profile("file")
             public FinancialReportWriter fileStoreFinancialReportWriter() {
                 return new FileStoreFinancialReportWriter();
             }
         }

            ```
   4. Use @Profile annotation to define custom annotation - @Component / @Configuration /
   @Bean method annotated with custom annotation will be part of profile/profiles specified in
   annotation
      1. ```java
         @Profile("database")
         public @interface DatabaseProfile {
         }
         
         @Component
         @DatabaseProfile
         class DatabaseStoreFinancialDataDao implements FinancialDataDao {
         @Override
         public FinancialYearSummary findFinancialYearSummary(int year) {
         System.out.println("Database Dao => findFinancialYearSummary");
         return new FinancialYearSummary();
         }
         ```
3. If Bean does not have profile specified in any way, it will be created in every profile.
   You can use ‘!’ to specify in which profile bean should not be created.
   1. ```java
      @Configuration
      public class ApplicationConfiguration {
      @Bean
      @Profile("!prod")
      public DataWriter devDataWriter() {
      return new DevDataWriter();
      }
      ```
4. You can activate profiles in following way:
   1. Programmatically with usage of ConfigurableEnvironment
      1. ```java
         public class Runner {
            public static void main(String[] args) {
                AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
                context.registerShutdownHook();

                 // Activate profile
                 context.getEnvironment().setActiveProfiles("file");
                 context.register(ApplicationConfig.class);
                 context.refresh();
            }
           ```
   2. By using spring.profiles.active property - by specifying under VM options
      1. -Dspring.profiles.active = file
   3. On JUnit Test level by using @ActiveProfiles annotation
      1. ```java
         @RunWith(SpringJUnit4ClassRunner.class)
         @ContextConfiguration(classes = ApplicationConfig.class)
         @ActiveProfiles("database")
         public class ApplicationConfigTest {
         @Autowired
         private FinancialDataDao financialDataDao;

             @Test
             public void shouldTestFinancialDataDao() {
                 System.out.println("Will perform test on " + financialDataDao.getClass().getSimpleName());
             }
         }
          ```
   4. In Spring Boot Programmatically by usage of SpringApplicationBuilder
      1. ```java
         @SpringBootApplication
         public class Runner implements CommandLineRunner {

             @Autowired
             private FinancialDataDao financialDataDao;

             public static void main(String[] args) {
                 new SpringApplicationBuilder(Runner.class)
                         .profiles("database")
                         .run(args);
             }
         }

         ```
   5. In Spring Boot by application.properties or on yml level by addding property
      1. spring.profiles.active = database
5. Spring Profiles are useful in following cases:
    Changing Behavior of the system in Different Environments by changing set of Beans that are
   part of specific environments, for example prod, cert, dev
    Changing Behavior of the system for different customers
    Changing set of Beans used in Development Environment and also during Testing Execution
    Changing set of Beans in the system when monitoring or additional debugging capabilities
   should be turned on
6. How many profiles can we have
   1. Spring Framework does not specify any explicit limit on number of profiles, however since some
      of the classes in Framework, like ActiveProfilesUtils used by default implementation of
      ActiveProfilesResolver are using array to iterate over profiles, this enforces inexplicit
      limit that is equal to maximum number of elements in array that you can have in Java, which is
      Integer.MAX_VALUE - 2,147,483,647 (231 − 1).


@Import 
-------
1. In Spring, the @Import annotation is used to import and combine multiple configuration files or classes into a single configuration class. This allows you to modularize your configuration and reuse existing configurations across multiple parts of your application.
2. ```java
   @Configuration
   public class AppConfig1 {
   // Configuration 1 code...
   }

   @Configuration
   public class AppConfig2 {
   // Configuration 2 code...
   }
   @Configuration
   @Import({AppConfig1.class, AppConfig2.class})
   public class MainConfig {
   // Main configuration code...
   }

    ```
@Value
------
1. @Value annotation has one field value that accepts:
   1. Simple value
   2. Property reference
   3. VM arguments
   4. SpEL String
2. @Value annotation can be used on top of:
   1. Field
   2. Constructor Parameter
   3. Method – all fields will have injected the same value
   4. Method parameter – Injection will not be performed automatically if @Value is not present
      on method level or if @Autowired is not present at method level
   5. Annotation type
3. Inside @Value you can specify:
   1. Simple value - @Value("John"), @Value("true")
   2. Reference a property - @Value("${app.department.id}")
   3. Perform SpEL inline computation
      1. @Value("#{'Wall Street'.toUpperCase()}")
      2. @Value("#{5000 * 0.9}")
      3. @Value("#{'${app.department.id}'.toUpperCase()}")
   4. Inject values into array, list, set, map - have to register ConversionService bean
      1. ```java
         @Configuration
         @ComponentScan
         @PropertySource("application.properties")
         public class ApplicationConfiguration {
         @Bean
         public ConversionService conversionService() {
         return new DefaultConversionService();
         }
          }

         ```
4. Use cases
   1. Setting simple values of Spring Bean Fields, Method Parameters, Constructor Parameters
   2. Injecting property/environment values into Spring Bean Fields, Method Parameters,
      Constructor Parameters
   3. Injecting results of SpEL expressions into Spring Bean Fields, Method Parameters, Constructor
      Parameters
   4. Injecting values from other Spring Beans into Spring Bean Fields, Method Parameters,
      Constructor Parameters
   5. Injecting values into collections (arrays, lists, sets, maps) from literals,
      property/environment values, other Spring Beans
   6. Setting default values of Spring Bean Fields, Method Parameters, Constructor Parameters
      when referenced value is missing
      1. ```java
         @Value("${app.tax.department.alt.name:no_name}")
         private String taxDepartmentAlternateName;
            ```
5. Example
   1. ```java
      @Component
      public class SpringBean {
      @Value("John")
      private String name;

          @Value("#{'Wall Street'.toUpperCase()}")
          private String streetName;

          @Value("true")
          private boolean accountExists;

          @Value("100")
          private int idNumber;

          @Value("#{5000 * 0.9}")
          private float accountBalance;

          @Value("${app.department.id}")
          private int departmentId;

          @Value("#{'${app.department.id}'.toUpperCase()}")
          private String departmentName;

          private String managerName;

          private String supportContactMail;

          private String supportPhone;

          private String supportAddress;

          @Value("${app.dependent.departments}")
          private String[] dependentDepartments;

          @Value("${app.cases.id}")
          private List<Integer> casesIds;

          @Value("${app.cases.set}")
          private Set<String> casesSet;

          @Value("#{${app.cases.map}}")
          private Map<String, Integer> casesMap;

          public SpringBean(@Value("#{'${app.manager.name}'.toUpperCase()}") String managerName) {
              this.managerName = managerName;
          }

          @Value("${app.support.contact}")
          public void setSupportContactMail(String supportContactMail) {
              this.supportContactMail = supportContactMail;
          }

          @Autowired
          public void setSupportPhoneAndAddress(@Value("${app.support.phone}") String supportPhone,    @Value("${app.support.address}") String supportAddress) {
              this.supportPhone = supportPhone;
              this.supportAddress = supportAddress;
          }
      }
    
   
      // APPLICATION.properties
       app.department.id=5
       app.department.name=accounting
       app.manager.name=Mike Stevens
       app.support.contact=support@company.com
       app.support.phone=+19999999999
       app.support.address=Michigan Avenue
       app.dependent.departments=DEPA, DEPB, DEPC
       app.cases.id=1, 2, 3
       app.cases.set=caseA, caseB, caseC
       app.cases.map={caseA: 1, caseB: 2, caseC: 3}
       ```


SpEL - Spring Expression Language
--------------------------------
1. Spring Expression Language (SpEL) is an expression language that allows you to query and
   manipulate objects graphs during the runtime. SpEL is used in different products across Spring
   portfolio.
2. SpEL can be used independently with usage of ExpressionParser and EvaluationContext
   or can be used on top of fields, method parameters, constructor arguments via @Value
   annotation @Value("#{ … }").
3. SpEL supported features:
   1.  Literal expressions
       Boolean and relational
      operators
       Regular expressions
       Class expressions
       Accessing properties,
      arrays, lists, and maps
       Method invocation
       Relational operators
       Assignment
       Calling constructors
       Bean references
       Array construction
       Inline lists
       Inline maps
       Ternary operator
       Variables
       User-defined functions
       Collection projection
       Collection selection
       Templated expressions
   2. Reference
      1. https://docs.spring.io/spring-framework/reference/core/expressions/language-ref.html
   3. Example
      1. ```java
         public class Runner1 {
            public static void main(String[] args) {
            ExpressionParser parser = new SpelExpressionParser();

                 System.out.println(parser.parseExpression("'Hello'.concat(' world!')").getValue());
                 System.out.println(parser.parseExpression("'2 + 2 equals = '.concat(2 + 2)").getValue());
                 System.out.println(parser.parseExpression("new String('Wall Street').toUpperCase()").getValue()         );
                 System.out.println(parser.parseExpression("24 * 60").getValue());
                 System.out.println(parser.parseExpression("{1, 2, 3}").getValue());
                 System.out.println(parser.parseExpression("{a: 1, b: 2, c: 3}").getValue());
                 System.out.println(Arrays.toString((int[]) parser.parseExpression("new int[]{1, 2, 3}").         getValue()));
                 System.out.println(parser.parseExpression("5 < 10").getValue());
            }
            }

         Note : Not fully Explored 
         ```
   4. You can reference following using SpEL:
       Static Fields from class - T(com.example.Person).DEFAULT_NAME
       Static Methods from class - T(com.example.Person).getDefaultName()
       Spring Bean Property - @person.name
       Spring Bean Method - @person.getName()
       SpEL Variables - #personName
       Object property on reference assigned to SpEL variables - #person.name
       Object method on reference assigned to SpEL variables - #person.getName()
       Spring Application Environment Properties - environment['app.file.property’]
       System Properties - systemProperties['app.vm.property']
       System Environment Properties - systemEnvironment['JAVA_HOME']
   5. Example
      ```java
      public class Runner {
      public static void main(String[] args) {
      AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
       context.registerShutdownHook();

        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext evaluationContext = new StandardEvaluationContext();
        evaluationContext.setBeanResolver(new BeanFactoryResolver(context));

        SpringBean1 springBean1 = context.getBean(SpringBean1.class);

        System.out.println("getStaticMethodExecutionResult(): " + springBean1.getStaticMethodExecutionResult());
        System.out.println("getStaticValueFetchResult(): " + springBean1.getStaticValueFetchResult());
        System.out.println("getPropertyValue(): " + springBean1.getPropertyValue());
        System.out.println("getMethodValue(): " + springBean1.getMethodValue());
        System.out.println("getAppFileProperty(): " + springBean1.getAppFileProperty());
        System.out.println("getAppVmProperty(): " + springBean1.getAppVmProperty());
        System.out.println("getJavaHome(): " + springBean1.getJavaHome());
        
        // setting spel reference variables
        evaluationContext.setVariable("firstName", "John");
        evaluationContext.setVariable("objectRef", context.getBean(SpringBean2.class));

        //acessing spel reference variables
        System.out.println("#firstName: " + parser.parseExpression("#firstName").getValue(evaluationContext));
        System.out.println("#objectRef.property: " + parser.parseExpression("#objectRef.property").getValue(evaluationContext));
        System.out.println("#objectRef.method(): " + parser.parseExpression("#objectRef.method()").getValue(evaluationContext));
       }
        }
      @Component
      public class SpringBean1 {

          @Value("#{T(com.spring.professional.exam.tutorial.module01.question33.beans.SpringBean2).staticMethod()}")
          private String staticMethodExecutionResult;

          @Value("#{T(com.spring.professional.exam.tutorial.module01.question33.beans.SpringBean2).STATIC_VALUE}")
          private String staticValueFetchResult;

          @Value("#{@springBean2.property}")
          private String propertyValue;

          @Value("#{@springBean2.method()}")
          private String methodValue;

          @Value("#{environment['app.file.property']}")
          private String appFileProperty;

          @Value("#{systemProperties['app.vm.property']}")
          private String appVmProperty;

          @Value("#{systemEnvironment['JAVA_HOME']}")
          private String javaHome;

          public String getStaticMethodExecutionResult() {
              return staticMethodExecutionResult;
          }

          public String getStaticValueFetchResult() {
        return staticValueFetchResult;
          }

          public String getPropertyValue() {
        return propertyValue;
          }

          public String getMethodValue() {
              return methodValue;
          }

          public String getAppFileProperty() {
              return appFileProperty;
          }

          public String getAppVmProperty() {
              return appVmProperty;
          }

          public String getJavaHome() {
              return javaHome;
          }
      }
      
      @Configuration
      @ComponentScan
      @PropertySource("classpath:application.properties")
      public class ApplicationConfiguration {
      }

       ```
Environment abstraction
-----------------------
1. Environment Abstraction is part of Spring Container that models two key aspect of application environment:
    Profiles
    Properties
2. Environment Abstraction is represent on code level by classes that implements Environment interface. This
   interface allows you to resolve properties and also to list profiles. You can receive reference to class that
   implements Environment by calling EnvironmentCapable class, implemented by ApplicationContext.
   Properties can also be retrieved by using @Value("${…}") annotation.
3. Environment Abstraction role in context of profiles is to determine which profiles are currently active, and
   which are activated by default.
4. Environment Abstraction role in context of properties is to provide convenient, standarized and generic service
   that allows to resolve properties and also to configure property sources. 
5. Properties may come from following
   sources:
    Properties Files
    JVM system properties - vm options
    System Environment Variables - 
    JNDI
    Servlet Config
    Servlet Context Parameters
6. Default property sources for standalone applications are configured in StandardEnvironment, which
   includes JVM system properties and System Environment Variables. 
7. When running Spring Application in Servlet
   Environment, property sources will be configured based on StandardServletEnvironment, which
   additionally includes Servlet Config and Servlet Context Parameters, optionally it might include
   JndiPropertySource.
8. Too add additional properties files as property sources you can use @PropertySource annotation.
9. Example
   ```java
   public class Runner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.registerShutdownHook();

        //context.getEnvironment().setActiveProfiles("profile1", "profile2", "profile3");
        // -Dspring.profiles.active="profile1,profile2,profile3"

        context.register(ApplicationConfiguration.class);
        context.refresh();

        System.out.println(Arrays.toString(context.getEnvironment().getActiveProfiles()));
        System.out.println(context.getEnvironment().getProperty("app.file.property"));
        System.out.println(context.getEnvironment().getProperty("app.vm.property"));
        System.out.println(context.getEnvironment().getProperty("app.env.property"));
        System.out.println(context.getEnvironment().getProperty("user.home"));
        System.out.println(context.getEnvironment().getProperty("JAVA_HOME"));
        System.out.println(context.getEnvironment().getProperty("MAVEN_HOME"));

     ```
10. Property Sources in Spring Application vary based on type of applications that is being executed:
    1. Standalone Application
        Properties Files
        JVM system properties
        System Environment Variables
    2. Servlet Container Application
        Properties Files
        JVM system properties
        System Environment Variables
        JNDI
        ServletConfig init parameters
        ServletContext init parameters
    3. Spring Boot Application
        Devtools properties from ~/.spring-boot-devtools.properties (when devtools is active)
        @TestPropertySource annotations on tests
        Properties attribute in @SpringBootTest tests
        Command line arguments
        Properties from SPRING_APPLICATION_JSON property
        ServletConfig init parameters
        ServletContext init parameters
        JNDI attributes from java:comp/env
        JVM system properties
        System Environment Variables
        RandomValuePropertySource - ${random.*}
        application-{profile}.properties and YAML variants - outside of jar
        application-{profile}.properties and YAML variants – inside jar
        application.properties and YAML variants - outside of jar
        application.properties and YAML variants - inside jar
        @PropertySource annotations on @Configuration classes
        Default properties - SpringApplication.setDefaultProperties



   

                  



                        

                  
                                                          
   

               
   
            
            
