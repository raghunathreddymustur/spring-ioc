package dependencyInjection.AnnotationBased.SetterInjection;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SetterRunner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(SetterConfig.class);
    }
}
