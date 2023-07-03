package annotationBasedConfiguration;

import annotationBasedConfiguration.config.ConfigurationOne;
import annotationBasedConfiguration.config.ConfigurationTwo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationConfig {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationOne.class, ConfigurationTwo.class);
        context.registerShutdownHook();
        context.getBean("card");
        context.close();
    }
}
