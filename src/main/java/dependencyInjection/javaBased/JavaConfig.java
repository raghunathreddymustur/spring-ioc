package dependencyInjection.javaBased;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaConfig {
    public static void main(String[] args) {
        ApplicationContext context= new AnnotationConfigApplicationContext(ConfigurationDI.class);
        context.getBean("multiplePayment");
    }
}
