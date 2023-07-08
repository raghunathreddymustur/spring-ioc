package profiles;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(Config.class);
        context.getEnvironment().setActiveProfiles("dev");
        context.registerShutdownHook();
    }

}
