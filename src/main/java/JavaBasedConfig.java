import config.JavaConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaBasedConfig {
    public static void main(String[] args) {
        ApplicationContext context=new AnnotationConfigApplicationContext(JavaConfiguration.class);
        context.getBean("card");
    }
}
